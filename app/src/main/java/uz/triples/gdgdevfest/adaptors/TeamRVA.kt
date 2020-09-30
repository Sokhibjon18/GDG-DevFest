package uz.triples.gdgdevfest.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_team.view.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.database.entities.Team
import uz.triples.gdgdevfest.interfaces.StringTransitionInterface

class TeamRVA(val list: List<Team>?, val param: StringTransitionInterface) : RecyclerView.Adapter<TeamRVA.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_team, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val view = holder.itemView
        view.teamName.text = list?.get(position)?.name
        view.memberCount.text = "${list?.get(position)?.membersCount} members"
        view.teamCard.setOnClickListener {
            param.getString(list?.get(position)?.name)
        }
    }

    override fun getItemCount(): Int {
        return if (list!!.isNotEmpty())
            list.size
        else
            0
    }
}