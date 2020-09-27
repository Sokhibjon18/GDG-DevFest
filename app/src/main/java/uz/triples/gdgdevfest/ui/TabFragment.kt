package uz.triples.gdgdevfest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_tab.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.AgendaRVAdapter
import uz.triples.gdgdevfest.models.AgendaItemModel

class TabFragment : Fragment(R.layout.fragment_tab) {

    private val TAG = "TabFragment"
    private var detector: String? = null
    private lateinit var adaptorRV: AgendaRVAdapter
    private lateinit var listAgendaItemModel: List<AgendaItemModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adaptorRV = AgendaRVAdapter()
        agendaRecyclerView.layoutManager = LinearLayoutManager(context)
        agendaRecyclerView.adapter = adaptorRV

        detector = arguments?.getString("arg")
        listAgendaItemModel = Gson().fromJson(
            detector,
            object : TypeToken<List<AgendaItemModel>>() {}.type
        )

        adaptorRV.submitList(listAgendaItemModel)
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