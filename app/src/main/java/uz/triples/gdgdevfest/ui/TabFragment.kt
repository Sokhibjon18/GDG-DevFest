package uz.triples.gdgdevfest.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_agenda_info.view.*
import kotlinx.android.synthetic.main.fragment_tab.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.AgendaRVA
import uz.triples.gdgdevfest.database.entities.Speakers
import uz.triples.gdgdevfest.interfaces.AgendaRVInterface
import uz.triples.gdgdevfest.models.AgendaItemModel
import uz.triples.gdgdevfest.viewModels.AgendaSharedViewModel

class TabFragment : Fragment(R.layout.fragment_tab) {

    private val TAG = "TabFragment"
    private var detector: String? = null
    private lateinit var adaptorRV: AgendaRVA
    private lateinit var listAgendaItemModel: List<AgendaItemModel>
    private lateinit var sharedViewModel: AgendaSharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adaptorRV = AgendaRVA(requireContext(), object : AgendaRVInterface {
            override fun getFullInfo(agendaItemModel: AgendaItemModel) {
                GlobalScope.launch {
                    var speaker: Speakers? = null
                    if (agendaItemModel.speakers.isNotEmpty())
                        speaker = sharedViewModel.getSpeakerByName(agendaItemModel.speakers[0]!!)
                    withContext(Dispatchers.Main) {
                        val dialog = AlertDialog.Builder(requireContext())
                        var alertDialog = dialog.create()
                        val dialogView = LayoutInflater.from(requireContext())
                            .inflate(R.layout.dialog_agenda_info, null, false)
                        if (speaker != null){
                            dialogView.speakerNameAgendaInfo.text = speaker.name
                            dialogView.degreeAgendaInfo.text = speaker.title
                            dialogView.twitter.setOnClickListener { openWeb(speaker.twitter) }
                            dialogView.facebook.setOnClickListener { openWeb(speaker.facebook) }
                            dialogView.linkedIn.setOnClickListener { openWeb(speaker.linkedIn) }
                            dialogView.instagram.setOnClickListener { openWeb(speaker.instagram) }
                            dialogView.web.setOnClickListener { openWeb(speaker.web) }

                            if (speaker.twitter.isNullOrBlank()) {
                                dialogView.twitter.visibility = View.GONE
                            }
                            if (speaker.facebook.isNullOrBlank()) {
                                dialogView.facebook.visibility = View.GONE
                            }
                            if (speaker.linkedIn.isNullOrBlank()) {
                                dialogView.linkedIn.visibility = View.GONE
                            }
                            if (speaker.instagram.isNullOrBlank()) {
                                dialogView.instagram.visibility = View.GONE
                            }
                            if (speaker.web.isNullOrBlank()) {
                                dialogView.web.visibility = View.GONE
                            }
                            Picasso.get().load(speaker.photo).placeholder(R.drawable.ic_puzzle)
                                .into(dialogView.speakerImageAgendaInfo)
                        }
                        else {
                            dialogView.twitter.visibility = View.GONE
                            dialogView.facebook.visibility = View.GONE
                            dialogView.instagram.visibility = View.GONE
                            dialogView.linkedIn.visibility = View.GONE
                            dialogView.web.visibility = View.GONE
                            dialogView.complexityAgendaInfo.visibility = View.GONE
                            dialogView.speakerImageAgendaInfo.visibility = View.GONE
                            dialogView.constraintAgendaInfo.visibility = View.GONE
                            dialogView.languageAgendaInfo.visibility = View.GONE
                        }
                        dialog.setView(dialogView)
                        dialogView.titleAgendaInfo.text = agendaItemModel.title
                        dialogView.descriptionAgendaInfo.text = agendaItemModel.description
                        dialogView.dateAgendaInfo.text =
                            "Date: ${agendaItemModel.startTime} ${agendaItemModel.date}"
                        dialogView.durationAgendaInfo.text =
                            "Duration: ${agendaItemModel.duration} min."
                        dialogView.complexityAgendaInfo.text =
                            "Complexity: ${agendaItemModel.complexity}"
                        dialogView.languageAgendaInfo.text = "Language: ${agendaItemModel.language}"


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

    fun openWeb(web: String?) {
        val uriUrl = Uri.parse(web)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }
}