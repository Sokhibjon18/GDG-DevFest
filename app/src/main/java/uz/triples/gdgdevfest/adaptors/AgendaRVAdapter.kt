package uz.triples.gdgdevfest.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_agenda.view.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.models.AgendaItemModel

class AgendaRVAdapter(): ListAdapter<AgendaItemModel, AgendaRVAdapter.ViewHolder>(DiffCallBack()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class DiffCallBack() : DiffUtil.ItemCallback<AgendaItemModel>() {
        override fun areItemsTheSame(oldItem: AgendaItemModel, newItem: AgendaItemModel): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: AgendaItemModel, newItem: AgendaItemModel): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_agenda, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val item = getItem(position)
        if (item.speakers.isNotEmpty()) {
            view.titleAgendaItem.text = item.title
            view.nameAgendaItem.text = item.speakers[0]
            view.dateAgendaItem.text = item.date
            view.startTimeAgendaItem.text = item.startTime
            view.endTimeAgendaItem.text = item.endTime
        }
    }
}