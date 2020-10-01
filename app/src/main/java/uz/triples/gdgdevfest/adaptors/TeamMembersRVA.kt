package uz.triples.gdgdevfest.adaptors

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item_team_members.view.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.database.entities.TeamMembers

class TeamMembersRVA(val context: Context, val list: List<TeamMembers>?) : RecyclerView.Adapter<TeamMembersRVA.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_team_members, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val view = holder.itemView
        val item = list?.get(position)
        view.nameTeamMember.text = list?.get(position)?.name
        view.responsibleFor.text = list?.get(position)?.responsible
        if (list?.get(position)?.photo != "")
        Picasso.get().load(list?.get(position)?.photo).placeholder(R.drawable.ic_puzzle).into(view.imageTeamMember)

        view.twitter.setOnClickListener { openWeb(item?.twitter) }
        view.facebook.setOnClickListener { openWeb(item?.facebook) }
        view.linkedIn.setOnClickListener { openWeb(item?.linkedIn) }
        view.instagram.setOnClickListener { openWeb(item?.instagram) }
        view.web.setOnClickListener { openWeb(item?.web) }

        if (item?.twitter.isNullOrBlank())
            view.twitter.visibility = View.GONE
        else view.twitter.visibility = View.VISIBLE
        if (item?.facebook.isNullOrBlank())
            view.facebook.visibility = View.GONE
        else view.facebook.visibility = View.VISIBLE
        if (item?.linkedIn.isNullOrBlank())
            view.linkedIn.visibility = View.GONE
        else view.linkedIn.visibility = View.VISIBLE
        if (item?.instagram.isNullOrBlank())
            view.instagram.visibility = View.GONE
        else view.instagram.visibility = View.VISIBLE
        if (item?.web.isNullOrBlank())
            view.web.visibility = View.GONE
        else view.web.visibility = View.VISIBLE

        when ((0..3).random()) {
            0 -> {
                view.underlineLayout.background = context.resources.getDrawable(R.color.googleBlue)
            }
            1 -> {
                view.underlineLayout.background = context.resources.getDrawable(R.color.googleGreen)
            }
            2 -> {
                view.underlineLayout.background = context.resources.getDrawable(R.color.googleOrange)
            }
            3 -> {
                view.underlineLayout.background = context.resources.getDrawable(R.color.googleRed)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (list!!.isNotEmpty())
            list.size
        else
            0
    }

    private fun openWeb(web: String?){
        val uriUrl= Uri.parse(web)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        ContextCompat.startActivity(context, launchBrowser, null)
    }
}