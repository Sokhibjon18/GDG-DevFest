package uz.triples.gdgdevfest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_agenda.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.adaptors.AgendaVPAdapter

class AgendaFragment : Fragment(R.layout.fragment_agenda) {

    private lateinit var pagerAdapter : AgendaVPAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerAdapter = AgendaVPAdapter(requireActivity().supportFragmentManager)
        agendaViewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(agendaViewPager, true)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_smartphone)
        tabLayout.getTabAt(0)!!.setText(R.string.android)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_google_chrome)
        tabLayout.getTabAt(1)!!.setText(R.string.web)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_google_cloud)
        tabLayout.getTabAt(2)!!.setText(R.string.cloud)
    }
}