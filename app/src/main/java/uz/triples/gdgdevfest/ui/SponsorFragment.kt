package uz.triples.gdgdevfest.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_sponsor.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.SponsorsRVA
import uz.triples.gdgdevfest.interfaces.StringTransitionInterface
import uz.triples.gdgdevfest.viewModels.SponsorsViewModel


class SponsorFragment : Fragment(R.layout.fragment_sponsor) {

    private lateinit var viewModel: SponsorsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SponsorsViewModel::class.java)

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        sponsorRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllSponsors().observe(viewLifecycleOwner, {
            sponsorRecyclerView.adapter = SponsorsRVA(it, object : StringTransitionInterface {
                override fun getString(name: String?) {
                    val uriUrl: Uri = Uri.parse(name)
                    val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                    startActivity(launchBrowser)
                }
            })
        })
    }

}