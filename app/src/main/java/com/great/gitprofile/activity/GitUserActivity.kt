package com.great.gitprofile.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.great.gitprofile.R
import com.great.gitprofile.adapter.RepoAdapter
import com.great.gitprofile.data.GitUser
import com.great.gitprofile.util.GitUserActivityUtil
import com.great.gitprofile.viewmodel.GitUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_git_user.*

@AndroidEntryPoint
class GitUserActivity : AppCompatActivity() {

    private val repoAdapter = RepoAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_user)

        rvRepos.adapter = repoAdapter
        rvRepos.layoutManager = LinearLayoutManager(this)

        val viewModel: GitUserViewModel by viewModels()
        viewModel.getSearchResultObserver().observe(this, Observer<GitUser> { it ->
            it.let {

                tvName.text = it.name
                clNameAvatar.visibility = View.VISIBLE

                clNameAvatar.alpha = 0.0f
                clNameAvatar.translationY = 30f
                clNameAvatar.animate()
                    .translationY(0f)
                    .setDuration(500)
                    .alpha(1.0f)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            rvRepos.visibility = View.VISIBLE
                            rvRepos.alpha = 0.0f
                            rvRepos.translationY = 30f

                            rvRepos.animate()
                                .translationY(0f)
                                .setDuration(600)
                                .alpha(1.0f)
                                .setListener(null)
                        }
                    })
            }
            Glide
                .with(this)
                .load(it.avatar_url)
                .centerCrop()
                .into(ivAvatar)
        })
        viewModel.getErrorMessageObserver().observe(this, { it ->
            it.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                clNameAvatar.visibility = View.GONE
                rvRepos.visibility = View.GONE
            }
        })
        viewModel.getReposResultObserver().observe(this, { it ->
            it.let {
                repoAdapter.setMovieList(it)
            }
        })
        btnSearch.setOnClickListener {
            if (GitUserActivityUtil.validateGitId(edtName.text.toString())) {
                edtName.text.let {
                    viewModel.getGitUser(edtName.text.toString())
                    clNameAvatar.visibility = View.GONE
                    rvRepos.visibility = View.GONE
                    hideKeyboard(currentFocus ?: View(this))
                }
            } else {
                Toast.makeText(this, "Invalid user id", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}