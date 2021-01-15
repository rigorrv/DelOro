package net.android.delorotheather.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import net.android.delorotheather.databinding.HomeFragmentBinding
import net.android.delorotheather.model.info.JsonInfo
import net.android.delorotheather.model.movies.MoviewTopList
import net.android.delorotheather.model.movies.Result
import net.android.delorotheather.ui.adapters.MovieAdapter

class HomeFragment : Fragment() {

    lateinit var homeFragmentBinding: HomeFragmentBinding
    var moviAdapter = MovieAdapter()

    companion object {
        var jsonInfo: List<JsonInfo> = mutableListOf()
        var movieJson: List<MoviewTopList> = mutableListOf()
        var listMovies: List<Result> = listOf()
        var rangePopularity = arrayListOf<Int>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)
        return homeFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        for (json in jsonInfo) {
            Glide.with(this)
                .load(json.home.whoBG)
                .into(homeFragmentBinding.imageWeARe)
            homeFragmentBinding.home = json.home

            for (movieList in movieJson) {
                listMovies = movieList.results.sortedByDescending { it.popularity }
                moviAdapter.moviewList = listMovies
                for (range in movieList.results) {
                    rangePopularity.add(range.popularity.toInt())
                }

                homeFragmentBinding.homeRecyclerView.layoutManager = GridLayoutManager(context, 2)
                homeFragmentBinding.homeRecyclerView.adapter = moviAdapter
            }

        }

    }


}