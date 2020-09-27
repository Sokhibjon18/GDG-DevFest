package uz.triples.gdgdevfest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_agenda.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.AgendaVPAdapter
import uz.triples.gdgdevfest.database.entities.SessionsModel
import uz.triples.gdgdevfest.models.AgendaItemModel
import uz.triples.gdgdevfest.viewModels.AgendaViewModel

class AgendaFragment : Fragment(R.layout.fragment_agenda) {

    private lateinit var pagerAdapter : AgendaVPAdapter
    private lateinit var viewModel: AgendaViewModel
    private lateinit var androidList: MutableList<AgendaItemModel>
    private lateinit var cloudList: MutableList<AgendaItemModel>
    private lateinit var webList: MutableList<AgendaItemModel>
    private lateinit var othersList: MutableList<AgendaItemModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout.setupWithViewPager(agendaViewPager, true)

        viewModel = ViewModelProviders.of(this).get(AgendaViewModel::class.java)

        viewModel.getAgenda().observe(viewLifecycleOwner, {

            GlobalScope.launch {
                androidList = mutableListOf<AgendaItemModel>()
                webList = mutableListOf<AgendaItemModel>()
                cloudList = mutableListOf<AgendaItemModel>()
                othersList = mutableListOf<AgendaItemModel>()
                for (agenda in it) {
                    val listSessions = Gson().fromJson<List<Int>>(
                        agenda.sessionsList,
                        object : TypeToken<List<Int>>() {}.type
                    )
                    for (session in listSessions) {
                        val sessionItem = Gson().fromJson(
                            viewModel.getSession(session).sessionModel,
                            SessionsModel::class.java
                        )
                        val agendaItem = AgendaItemModel(
                            agenda.startTime,
                            agenda.endTime,
                            agenda.date,
                            sessionItem.complexity,
                            sessionItem.description,
                            sessionItem.language,
                            sessionItem.presentation,
                            sessionItem.speakers,
                            sessionItem.title,
                            sessionItem.image
                        )
                        if (session < 132 && sessionItem.tags.isNotEmpty())
                            when (sessionItem.tags) {
                                "Android" -> {
                                    androidList.plusAssign(agendaItem)
                                }
                                "Web" -> {
                                    webList.plusAssign(agendaItem)
                                }
                                "Cloud" -> {
                                    cloudList.plusAssign(agendaItem)
                                }
                            }
                        else
                            othersList.plusAssign(agendaItem)
                    }
                }

                withContext(Dispatchers.Main){
                    pagerAdapter = AgendaVPAdapter(requireActivity().supportFragmentManager,
                    listOf(androidList, webList, cloudList, othersList))
                    agendaViewPager.adapter = pagerAdapter

                    tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_smartphone)
                    tabLayout.getTabAt(0)!!.setText(R.string.android)
                    tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_google_chrome)
                    tabLayout.getTabAt(1)!!.setText(R.string.web)
                    tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_google_cloud)
                    tabLayout.getTabAt(2)!!.setText(R.string.cloud)
                    tabLayout.getTabAt(3)!!.setIcon(R.drawable.ic_coffee)
                    tabLayout.getTabAt(3)!!.setText(R.string.more)
                }
            }
        })

    }
}