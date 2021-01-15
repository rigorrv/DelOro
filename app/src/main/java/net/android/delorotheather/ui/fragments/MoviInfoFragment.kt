package net.android.delorotheather.ui.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import net.android.delorotheather.R
import net.android.delorotheather.databinding.MovieInfoFragmentBinding
import net.android.delorotheather.networking.imgMovies
import net.android.delorotheather.ui.adapters.CommentsMovieAdapter
import net.android.delorotheather.ui.fragments.HomeFragment.Companion.listMovies
import net.android.delorotheather.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MoviInfoFragment : Fragment() {

    val myViewModel: MyViewModel by viewModel()
    lateinit var movieInfoFragmentBinding: MovieInfoFragmentBinding
    var commentMovieAdapter = CommentsMovieAdapter()


    companion object {
        var movieID = 0
        var searchWord = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieInfoFragmentBinding = MovieInfoFragmentBinding.inflate(inflater, container, false)
        return movieInfoFragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        myViewModel.getMovieInfo(movieID)
        myViewModel.movieInfoLiveData.observe(viewLifecycleOwner, Observer {
            movieInfoFragmentBinding.movieInfo = it
            movieInfoFragmentBinding.ratingBar2.rating =
                500f / it.popularity.toInt()

            Glide.with(this)
                .load(imgMovies + it.poster_path)
                .into(movieInfoFragmentBinding.moviewImageInfo)
        })
        myViewModel.getCommentsMovie(movieID)
        myViewModel.commentsMovieLiveData.observe(viewLifecycleOwner, Observer {
            commentMovieAdapter.commentMovieList = it.results
            movieInfoFragmentBinding.commentsRecycler.adapter = commentMovieAdapter
        })
    }
}