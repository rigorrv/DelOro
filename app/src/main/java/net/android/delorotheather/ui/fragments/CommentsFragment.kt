package net.android.delorotheather.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import net.android.delorotheather.databinding.CommentsFragmentBinding
import net.android.delorotheather.model.info.JsonInfo
import net.android.delorotheather.ui.adapters.CommentsAdapter
import net.android.delorotheather.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CommentsFragment : Fragment() {

    lateinit var commentsFragmentBinding: CommentsFragmentBinding
    val commentsAdapter = CommentsAdapter()
    val myViewMode: MyViewModel by viewModel()

    companion object {
        var jsonInfo: List<JsonInfo> = mutableListOf()
        var username: String = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        commentsFragmentBinding = CommentsFragmentBinding.inflate(inflater, container, false)
        return commentsFragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (json in jsonInfo) {
            Glide.with(this)
                .load(json.comments.titleBG)
                .into(commentsFragmentBinding.commentsTitleBackground)
            commentsFragmentBinding.comments = json.comments
        }
        myViewMode.getComment()
        myViewMode.commentsLiveData.observe(viewLifecycleOwner, Observer {
            commentsAdapter.commentInfo = it.comment
            commentsFragmentBinding.commentRecyclerView.adapter = commentsAdapter
        })



    }

}