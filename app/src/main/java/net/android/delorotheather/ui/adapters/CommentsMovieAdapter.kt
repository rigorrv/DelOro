package net.android.delorotheather.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.comments_video_item.view.*
import kotlinx.android.synthetic.main.movie_info_fragment.view.*
import kotlinx.android.synthetic.main.movie_items.view.*
import net.android.delorotheather.R
import net.android.delorotheather.model.movies.Result
import net.android.delorotheather.model.movies.ResultX
import net.android.delorotheather.ui.fragments.HomeFragment
import net.android.delorotheather.ui.fragments.MoviInfoFragment.Companion.movieID

class CommentsMovieAdapter : RecyclerView.Adapter<CommentsMovieAdapter.ViewHolder>() {

    var commentMovieList: List<ResultX> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentsMovieAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.comments_video_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentsMovieAdapter.ViewHolder, position: Int) {
        holder.onBind(commentMovieList[position])
    }

    override fun getItemCount(): Int {
        return commentMovieList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var userText = itemView.userComment
        var commentVideo = itemView.commentVideo
        var dateComment = itemView.dateComment
        fun onBind(commentMovieList: ResultX) {

            userText.text = commentMovieList.author
            commentVideo.text = commentMovieList.content
            dateComment.text = commentMovieList.updated_at
        }

    }
}