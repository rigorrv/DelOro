package net.android.delorotheather.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import net.android.delorotheather.R
import net.android.delorotheather.databinding.FindusFragmentBinding
import net.android.delorotheather.model.address.AddressJson
import net.android.delorotheather.model.info.JsonInfo
import net.android.delorotheather.ui.adapters.AddressAdapter
import net.android.delorotheather.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FindUsFragment : Fragment(), OnMapReadyCallback {

    lateinit var findusFragmentBinding: FindusFragmentBinding
    val myViewModel: MyViewModel by viewModel()
    val addressAdapter = AddressAdapter()
    var searching = ""

    companion object {
        lateinit var mMap: GoogleMap
        var jsonInfo: List<JsonInfo> = mutableListOf()
        fun getCamera(lat: Double, long: Double) {
            val usa = LatLng(lat, long)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(usa, 8f))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        findusFragmentBinding = FindusFragmentBinding.inflate(inflater, container, false)
        return findusFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        for (json in jsonInfo) {
            Glide.with(this)
                .load(json.findus.titleBG)
                .into(findusFragmentBinding.findusTitleBackground)
            findusFragmentBinding.findus = json.findus
        }

        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.google) as SupportMapFragment
        mapFragment.getMapAsync(this)
        myViewModel.getAddress("")
        myViewModel.addressLiveData.observe(viewLifecycleOwner, Observer {
            getMap(it)
            addressAdapter.addressList = it
            findusFragmentBinding.recyclerFindUs.adapter = addressAdapter
        })


        findusFragmentBinding.addressTxt.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searching = findusFragmentBinding.addressTxt.text.toString()
                myViewModel.getAddress(searching)
                myViewModel.addressLiveData.observe(viewLifecycleOwner, Observer {
                    addressAdapter.addressList = it
                    findusFragmentBinding.recyclerFindUs.adapter = addressAdapter

                })
                findusFragmentBinding.addressTxt.setText("")

                findusFragmentBinding.addressTxt.clearFocus()
                val `in`: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                `in`.hideSoftInputFromWindow(
                    findusFragmentBinding.addressTxt.getWindowToken(),
                    0
                )

                true
            } else false
        })

    }

    fun getMap(items: AddressJson) {
        mMap.clear()
        for (i in items) {
            val newMarkers = LatLng(i.latitude.toDouble(), i.longitude.toDouble())
            mMap.addMarker(MarkerOptions().position(newMarkers).title("USA"))
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        val usa = LatLng(38.749831, -102.096594)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(usa, 3f))
    }


}