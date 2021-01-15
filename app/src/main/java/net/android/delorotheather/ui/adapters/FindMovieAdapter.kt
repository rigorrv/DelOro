package net.android.delorotheather.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_info_fragment.view.*
import kotlinx.android.synthetic.main.movie_items.view.*
import net.android.delorotheather.R
import net.android.delorotheather.model.movies.Result
import net.android.delorotheather.model.movies.ResultXX
import net.android.delorotheather.networking.imgMovies
import net.android.delorotheather.ui.ContainerFragment
import net.android.delorotheather.ui.fragments.HomeFragment
import net.android.delorotheather.ui.fragments.MoviInfoFragment.Companion.movieID
import net.android.delorotheather.ui.fragments.MovieFragment

class FindMovieAdapter : RecyclerView.Adapter<FindMovieAdapter.ViewHolder>() {

    var moviewList: List<ResultXX> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindMovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FindMovieAdapter.ViewHolder, position: Int) {
        holder?.onBind(moviewList[position])
    }

    override fun getItemCount(): Int {
        return moviewList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageMovie = itemView.imageMovie
        val rateStars = itemView.ratingBar
        fun onBind(movieList: ResultXX) {
            rateStars.rating =
                500f / movieList.popularity.toInt()

            Glide.with(itemView)
                .load(imgMovies + movieList.poster_path)
                .into(imageMovie)
            itemView.setOnClickListener {
                ContainerFragment.mainFragment = true
                movieID = movieList.id
                Navigation.findNavController(itemView).navigate(R.id.go_info_movie)
                Log.d("TAG", "onBind: ${movieList.popularity}")
            }
        }
    }
}