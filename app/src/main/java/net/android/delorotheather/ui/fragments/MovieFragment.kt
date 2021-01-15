package net.android.delorotheather.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.movies_fragment.*
import net.android.delorotheather.R
import net.android.delorotheather.databinding.MoviesFragmentBinding
import net.android.delorotheather.model.info.JsonInfo
import net.android.delorotheather.ui.adapters.FindMovieAdapter
import net.android.delorotheather.ui.fragments.MoviInfoFragment.Companion.searchWord
import net.android.delorotheather.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    lateinit var moviesFragmentBinding: MoviesFragmentBinding
    val myViewMode: MyViewModel by viewModel()
    var findMovieAdapter = FindMovieAdapter()
    var searching = "ana"

    companion object {
        var jsonInfo: List<JsonInfo> = mutableListOf()
        var rangePopularity = arrayListOf<Int>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesFragmentBinding = MoviesFragmentBinding.inflate(inflater, container, false)
        return moviesFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        for (json in jsonInfo) {
            Glide.with(this)
                .load(json.movies.titleBG)
                .into(moviesFragmentBinding.movieTitleBackground)
            moviesFragmentBinding.movie = json.movies
        }
        loadMovieInfo()
        moviesFragmentBinding.searchMovieTxt.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searching = moviesFragmentBinding.searchMovieTxt.text.toString()
                myViewMode.findnMovie(searching)
                myViewMode.findMovieLiveData.observe(viewLifecycleOwner, Observer {
                    findMovieAdapter.moviewList = it.results
                    moviesFragmentBinding.movieRecyclerView.layoutManager =
                        GridLayoutManager(context, 2)
                    moviesFragmentBinding.movieRecyclerView.adapter = findMovieAdapter

                    for (movieInfo in it.results) {
                        rangePopularity.add(movieInfo.popularity.toInt())
                    }

                })
                moviesFragmentBinding.searchMovieTxt.setText("")

                moviesFragmentBinding.searchMovieTxt.clearFocus()
                val `in`: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                `in`.hideSoftInputFromWindow(
                    moviesFragmentBinding.searchMovieTxt.getWindowToken(),
                    0
                )

                true
            } else false
        })

    }

    private fun loadMovieInfo() {
        myViewMode.findnMovie(searching)
        myViewMode.findMovieLiveData.observe(viewLifecycleOwner, Observer {
            findMovieAdapter.moviewList = it.results
            moviesFragmentBinding.movieRecyclerView.layoutManager =
                GridLayoutManager(context, 2)
            moviesFragmentBinding.movieRecyclerView.adapter = findMovieAdapter
            for (movieInfo in it.results) {
                rangePopularity.add(movieInfo.popularity.toInt())
            }
        })
    }
}