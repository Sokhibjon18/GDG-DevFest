package uz.triples.gdgdevfest.adaptors

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.gson.Gson
import uz.triples.gdgdevfest.models.AgendaItemModel
import uz.triples.gdgdevfest.ui.TabFragment

class AgendaVPA(fm: FragmentManager, private val aList: List<List<AgendaItemModel>>?) :
    FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TabFragment.newInstance(Gson().toJson(aList?.get(0)))
            }
            1 -> {
                TabFragment.newInstance(Gson().toJson(aList?.get(1)))
            }
            2 -> {
                TabFragment.newInstance(Gson().toJson(aList?.get(2)))
            }
            else -> {
                TabFragment.newInstance(Gson().toJson(aList?.get(3)))
            }
        }
    }
}