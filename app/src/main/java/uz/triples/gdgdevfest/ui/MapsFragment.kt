package uz.triples.gdgdevfest.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_maps.*
import kotlinx.android.synthetic.main.fragment_maps.backButton
import kotlinx.android.synthetic.main.fragment_maps.shareBtn
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

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        changeTheme.setOnClickListener {
            Snackbar.make(
                requireView(),
                "We will add this function soon",
                Snackbar.LENGTH_INDEFINITE
            ).setDuration(2000).setActionTextColor(Color.WHITE).show()
        }

        shareBtn.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=uz.triples.gdgdevfest")
            startActivity(Intent.createChooser(sharingIntent, "Share"))
        }
    }
}