package uz.triples.gdgdevfest.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_agenda_info.*
import kotlinx.android.synthetic.main.fragment_agenda_info.view.*
import kotlinx.android.synthetic.main.fragment_tab.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.AgendaRVAdapter
import uz.triples.gdgdevfest.interfaces.AgendaRVInterface
import uz.triples.gdgdevfest.models.AgendaItemModel
import uz.triples.gdgdevfest.viewModels.AgendaSharedViewModel

class TabFragment : Fragment(R.layout.fragment_tab) {

    private val TAG = "TabFragment"
    private var detector: String? = null
    private lateinit var adaptorRV: AgendaRVAdapter
    private lateinit var listAgendaItemModel: List<AgendaItemModel>
    private lateinit var sharedViewModel: AgendaSharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adaptorRV = AgendaRVAdapter(requireContext(), object : AgendaRVInterface {
            override fun getFullInfo(agendaItemModel: AgendaItemModel) {
                GlobalScope.launch {
                    val speaker =
                        sharedViewModel.getSpeakerByName(agendaItemModel.speakers[0]!!)
                    withContext(Dispatchers.Main) {
                        val dialog = AlertDialog.Builder(requireContext())
                        var alertDialog = dialog.create()
                        val dialogView = LayoutInflater.from(requireContext())
                            .inflate(R.layout.fragment_agenda_info, null, false)
                        dialog.setView(dialogView)
                        dialogView.titleAgendaInfo.text = agendaItemModel.title
                        dialogView.descriptionAgendaInfo.text = agendaItemModel.description
                        dialogView.dateAgendaInfo.text = "Date: ${agendaItemModel.startTime} ${agendaItemModel.date}"
                        dialogView.durationAgendaInfo.text = "Duration: ${agendaItemModel.duration} min."
                        dialogView.complexityAgendaInfo.text = "Complexity: ${agendaItemModel.complexity}"
                        dialogView.languageAgendaInfo.text = "Complexity: ${agendaItemModel.language}"
                        dialogView.closeBtn.setOnClickListener {
                            alertDialog.dismiss()
                        }
                        alertDialog = dialog.create()
                        alertDialog.show()
                    }
                }
            }
        })

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(AgendaSharedViewModel::class.java)
    }
}