package uz.triples.gdgdevfest.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item_team.view.*
import kotlinx.android.synthetic.main.rv_item_team_members.view.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.database.entities.TeamMembers

class TeamMembersRVA(val list: List<TeamMembers>?) : RecyclerView.Adapter<TeamMembersRVA.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_team_members, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val view = holder.itemView
        view.nameTeamMember.text = list?.get(position)?.name
        view.responsibleFor.text = list?.get(position)?.responsible
        if (list?.get(position)?.photo != "")
        Picasso.get().load(list?.get(position)?.photo).placeholder(R.drawable.ic_puzzle).into(view.imageTeamMember)
    }

    override fun getItemCount(): Int {
        return if (list!!.isNotEmpty())
            list.size
        else
            0
    }
}