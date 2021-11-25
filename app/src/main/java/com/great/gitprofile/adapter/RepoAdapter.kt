package com.great.gitprofile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.great.gitprofile.R
import com.great.gitprofile.data.UserRepos
import kotlinx.android.synthetic.main.item_repo.view.*
import kotlinx.android.synthetic.main.repo_description.view.*

class RepoAdapter(val context: Context) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    private var userRepos = mutableListOf<UserRepos>()

    fun setMovieList(userRepos: List<UserRepos>) {
        this.userRepos = userRepos.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepoAdapter.ViewHolder, position: Int) {
        val userRepo = userRepos[position]
        holder.bind(userRepo)
    }

    override fun getItemCount(): Int = userRepos.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userRepo: UserRepos) {
            itemView.apply {
                tvRepoName.text = userRepo.name
                tvRepoDes.text = userRepo.description
                cvRepo.setOnClickListener {
                    val dialog = BottomSheetDialog(context, R.style.TransparentDialog)
                    val view = LayoutInflater.from(context).inflate(R.layout.repo_description, null)
                    view.tvDataLastUpdate.text = userRepo.updated_at
                    view.tvDataStars.text = userRepo.stargazers_count.toString()
                    view.tvDataForks.text = userRepo.forks.toString()
                    dialog.setContentView(view)
                    dialog.show()
                }
            }
        }
    }
}