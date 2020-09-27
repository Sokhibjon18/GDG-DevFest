package uz.triples.gdgdevfest.adaptors

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.triples.gdgdevfest.ui.TabFragment

class AgendaVPAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                TabFragment.newInstance("android")
            }
            1->{
                TabFragment.newInstance("web")
            }
            else ->{
                TabFragment.newInstance("cloud")
            }
        }
    }
}