package uz.triples.gdgdevfest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_speakers.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.SpeakersRVA
import uz.triples.gdgdevfest.interfaces.SpeakersRVInterface
import uz.triples.gdgdevfest.models.AgendaItemModel
import uz.triples.gdgdevfest.viewModels.SpeakerViewModel

class SpeakersFragment : Fragment(R.layout.fragment_speakers) {

    private lateinit var adapter: SpeakersRVA
    private lateinit var viewModel: SpeakerViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SpeakersRVA(requireContext(), object : SpeakersRVInterface{
            override fun getFullInfo(agendaItemModel: AgendaItemModel) {

            }
        })

        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.getSpeakers().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        speakersRecyclerView.adapter = adapter
        speakersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}