package net.android.delorotheather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_info_fragment.view.*
import kotlinx.android.synthetic.main.movie_items.view.*
import net.android.delorotheather.R
import net.android.delorotheather.model.address.AddressJson

class MapAdapter : RecyclerView.Adapter<MapAdapter.ViewHolder>() {

    var addressData: List<AddressJson> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MapAdapter.ViewHolder, position: Int) {
        holder.onBind(addressData[position])
    }

    override fun getItemCount(): Int {
        return addressData.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageMovie = itemView.imageMovie
        val rateStars = itemView.ratingBar
        fun onBind(addressData: AddressJson) {

            itemView.setOnClickListener {

            }
        }
    }
}