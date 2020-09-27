package uz.triples.gdgdevfest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tab.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.AgendaRVAdapter
import uz.triples.gdgdevfest.viewModels.AgendaViewModel

class TabFragment : Fragment(R.layout.fragment_tab) {
    private val TAG = "TabFragment"
    private var detector: String? = null
    private lateinit var adaptorRV: AgendaRVAdapter
    private lateinit var agendaViewModel: AgendaViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detector = arguments?.getString("arg")

        agendaViewModel = ViewModelProviders.of(this).get(AgendaViewModel::class.java)
        agendaViewModel.updatePartners()
        agendaViewModel.getAgendaLiveData().observe(viewLifecycleOwner, {
            for (agendaItem in it) {
                Log.d(TAG, "${agendaItem.date}  ${agendaItem.startTime}  ${agendaItem.endTime}  ${agendaItem.sessionsList}")
            }
        })

        adaptorRV = AgendaRVAdapter()
        agendaRecyclerView.layoutManager = LinearLayoutManager(context)
        agendaRecyclerView.adapter = adaptorRV
    }

    companion object {
        fun newInstance(param1: String) =
            TabFragment().apply {
                arguments = Bundle().apply {
                    putString("arg", param1)
                }
            }
    }

}