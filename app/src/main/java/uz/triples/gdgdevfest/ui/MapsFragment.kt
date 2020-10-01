package uz.triples.gdgdevfest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import uz.triples.gdgdevfest.R

class MapsFragment : Fragment(R.layout.fragment_maps) {

    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(41.3391599, 69.335)
        googleMap.addMarker(MarkerOptions().position(sydney).title("GDG DevFest"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16f))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}