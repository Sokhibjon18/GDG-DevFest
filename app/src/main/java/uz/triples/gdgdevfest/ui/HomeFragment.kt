package uz.triples.gdgdevfest.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*
import uz.triples.gdgdevfest.R


class HomeFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        agendaCardView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_agendaFragment)
        }

        speakersCardView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_speakersFragment)
        }

        teamCardView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_teamFragment)
        }

        sponsorsCardView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_sponsorFragment)
        }

        FAQCardView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_FAQFragment)
        }

        locateUsCardView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapsFragment)
        }

        shareBtn.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(
                Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=uz.triples.gdgdevfest"
            )
            startActivity(Intent.createChooser(sharingIntent, "Share"))
        }

        openSocial()
    }

    private fun openSocial() {
        telegram.setOnClickListener { openWeb("https://t.me/gdgtashkent") }
        facebook.setOnClickListener { openWeb("https://www.facebook.com/sharer.php?u=https%3A%2F%2Fgdgtashkent.co%2F&t=GDG%20DevFest%20Season%202019") }
        instagram.setOnClickListener { openWeb("https://www.instagram.com/gdgtashkent/") }
        youtube.setOnClickListener { openWeb("https://www.youtube.com/channel/UCEPvlcWSQJ-lsv-7v20mFBQ") }
        twitter.setOnClickListener { openWeb("https://twitter.com/intent/tweet?text=Check%20out%20GDG%20DevFest%20Season%202019%20at%20%23DevFest%3A%20https%3A%2F%2Fgdgtashkent.co%2F") }
        linkedIn.setOnClickListener { openWeb("https://gdgtashkent.co/") }
        logoDevFest.setOnClickListener { openWeb("https://gdgtashkent.co/") }
    }

    private fun openWeb(web: String?) {
        val uriUrl = Uri.parse(web)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }
}