package uz.triples.gdgdevfest.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_agenda.*
import kotlinx.android.synthetic.main.fragment_agenda.shareBtn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.AgendaVPA
import uz.triples.gdgdevfest.models.AgendaItemModel
import uz.triples.gdgdevfest.models.SessionsModel
import uz.triples.gdgdevfest.viewModels.AgendaSharedViewModel

class AgendaFragment : Fragment(R.layout.fragment_agenda) {

    private lateinit var pagerAdapter: AgendaVPA
    private lateinit var sharedViewModel: AgendaSharedViewModel
    private val TAG = "AgendaFragment"
    private lateinit var viewPager: ViewPager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout.setupWithViewPager(agendaViewPager, true)
        viewPager = agendaViewPager

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pagerAdapter = AgendaVPA(childFragmentManager, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedViewModel = ViewModelProviders.of(requireActivity()).get(AgendaSharedViewModel::class.java)

        sharedViewModel.getAgenda().observe(viewLifecycleOwner, {
            GlobalScope.launch {
                val androidList: MutableList<AgendaItemModel> = mutableListOf<AgendaItemModel>()
                val webList: MutableList<AgendaItemModel> = mutableListOf<AgendaItemModel>()
                val cloudList: MutableList<AgendaItemModel> = mutableListOf<AgendaItemModel>()
                val othersList: MutableList<AgendaItemModel> = mutableListOf<AgendaItemModel>()
                for (agenda in it) {
                    val listSessions = Gson().fromJson<List<Int>>(
                        agenda.sessionsList,
                        object : TypeToken<List<Int>>() {}.type
                    )
                    for (session in listSessions) {
                        val sessionItem = Gson().fromJson(
                            sharedViewModel.getSession(session)?.sessionModel,
                            SessionsModel::class.java
                        )

                        val duration = difference(
                            agenda.startTime!!.split(":".toRegex()),
                            agenda.endTime!!.split(":".toRegex())
                        )

                        val agendaItem = AgendaItemModel(
                            agenda.startTime,
                            duration,
                            agenda.date,
                            sessionItem.complexity,
                            sessionItem.description,
                            sessionItem.language,
                            sessionItem.presentation,
                            sessionItem.speakers,
                            null,
                            sessionItem.title,
                            sessionItem.image
                        )
                        if (session < 132 && sessionItem.tags.isNotEmpty()) {
                            agendaItem.image = sharedViewModel.getSpeaker(sessionItem.speakers[0])?.photo
                            agendaItem.speakers =
                                listOf(sharedViewModel.getSpeaker(sessionItem.speakers[0])?.name)
                            agendaItem.degree = sharedViewModel.getSpeaker(sessionItem.speakers[0])?.title
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
                        } else
                            othersList.plusAssign(agendaItem)
                    }
                }

                withContext(Dispatchers.Main) {
                    pagerAdapter = AgendaVPA(
                        childFragmentManager,
                        listOf(androidList, webList, cloudList, othersList)
                    )

                    viewPager.adapter = pagerAdapter

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

    private fun difference(start: List<String>, stop: List<String>): String? {
        return ((stop[0].toInt() - start[0].toInt()) * 60 + stop[1].toInt() - start[1].toInt()).toString()
    }
}