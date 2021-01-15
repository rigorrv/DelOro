package net.android.delorotheather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.address_item.view.*
import net.android.delorotheather.R
import net.android.delorotheather.model.address.AddressJson
import net.android.delorotheather.model.address.AddressJsonItem
import net.android.delorotheather.ui.fragments.FindUsFragment


class AddressAdapter : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    var addressList: List<AddressJsonItem> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.address_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(addressList[position])
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val addressText = itemView.addressText
        val stateText = itemView.stateText
        fun onBind(addresItems: AddressJsonItem) {
            addressText.text = addresItems.address
            stateText.text = addresItems.state

            itemView.setOnClickListener {
                FindUsFragment.getCamera(
                    addresItems.latitude.toDouble(),
                    addresItems.longitude.toDouble()
                )


            }
        }
    }

}