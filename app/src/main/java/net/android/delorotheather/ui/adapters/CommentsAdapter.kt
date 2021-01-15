package net.android.delorotheather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comments_item.view.*
import net.android.delorotheather.R
import net.android.delorotheather.model.comments.Comment
import net.android.delorotheather.model.info.Comments

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    var commentInfo: List<Comment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.comments_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentsAdapter.ViewHolder, position: Int) {
        holder.onBind(commentInfo[position])
    }

    override fun getItemCount(): Int {
        return commentInfo.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val commentText = itemView.commentText
        val dateText = itemView.dateText
        fun onBind(commentsInfo: Comment) {
            commentText.text = commentsInfo.comments
            dateText.text = commentsInfo.dates
        }
    }
}