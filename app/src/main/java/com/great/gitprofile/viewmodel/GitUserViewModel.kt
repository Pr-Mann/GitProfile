package com.great.gitprofile.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.great.gitprofile.data.GitRepository
import com.great.gitprofile.data.GitUser
import com.great.gitprofile.data.UserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class GitUserViewModel @Inject constructor(
    private val repository: GitRepository
) : ViewModel() {

    private val userResultLiveData: MutableLiveData<GitUser> = MutableLiveData()
    private val reposResultLiveData: MutableLiveData<List<UserRepos>> = MutableLiveData()
    private val errorMessage = MutableLiveData<String>()

    fun getSearchResultObserver(): MutableLiveData<GitUser> {
        return userResultLiveData
    }

    fun getReposResultObserver(): MutableLiveData<List<UserRepos>> {
        return reposResultLiveData
    }

    fun getErrorMessageObserver(): MutableLiveData<String> {
        return errorMessage
    }

    fun getGitUser(userId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val responseUser = repository.getSearchResult(userId)
                val responseRepos = repository.getPublicRepos(userId)
                userResultLiveData.postValue(responseUser)
                reposResultLiveData.postValue(responseRepos)
            } catch (e: HttpException) {
                Log.e("error", e.message())
                errorMessage.postValue(
                    "Invalid user id"
                )
            }
        }
    }
}