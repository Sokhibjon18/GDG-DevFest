package uz.triples.gdgdevfest.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item_sponsors.view.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.database.entities.Sponsors
import uz.triples.gdgdevfest.interfaces.StringTransitionInterface

class SponsorsRVA(val list: List<Sponsors>?,val  param: StringTransitionInterface) : RecyclerView.Adapter<SponsorsRVA.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_sponsors, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val view = holder.itemView
        view.sponsorsName.text = list?.get(position)?.name
        view.sponsorTitle.text = list?.get(position)?.title
        Picasso.get().load(list?.get(position)?.logoUrl).into(view.sponsorsImage)
        view.sponsorsCardView2.setOnClickListener {
            param.getString(list?.get(position)?.url)
        }
    }

    override fun getItemCount(): Int {
        return if (list!!.isNotEmpty())
            list.size
        else
            0
    }
}