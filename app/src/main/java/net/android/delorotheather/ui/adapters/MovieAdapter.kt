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
import net.android.delorotheather.ui.ContainerFragment
import net.android.delorotheather.ui.fragments.HomeFragment
import net.android.delorotheather.ui.fragments.MoviInfoFragment.Companion.movieID

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var moviewList: List<Result> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.onBind(moviewList[position])
    }

    override fun getItemCount(): Int {
        return moviewList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageMovie = itemView.imageMovie
        val rateStars = itemView.ratingBar
        fun onBind(videoList: Result) {
            // nameMoview.text = videoList.title
            //rateNumber.text = videoList.popularity.toString()
            var img = "https://image.tmdb.org/t/p/w500/" + videoList.poster_path
            rateStars.rating =
                HomeFragment.rangePopularity.last().toFloat() / videoList.popularity.toInt()

            Glide.with(itemView)
                .load(img)
                .into(imageMovie)
            itemView.setOnClickListener {
                ContainerFragment.mainFragment = true
                movieID = videoList.id
                Navigation.findNavController(itemView).navigate(R.id.go_info_movie)
            }
        }
    }
}