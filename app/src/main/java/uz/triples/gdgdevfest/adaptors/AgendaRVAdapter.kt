package uz.triples.gdgdevfest.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.agenda_rv_item.view.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.gdgJSON.JsonMember20160909
import uz.triples.gdgdevfest.gdgJSON.Schedule
import uz.triples.gdgdevfest.gdgJSON.TimeslotsItem

class AgendaRVAdapter(): ListAdapter<TimeslotsItem, AgendaRVAdapter.ViewHolder>(DiffCallBack()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class DiffCallBack() : DiffUtil.ItemCallback<TimeslotsItem>() {
        override fun areItemsTheSame(oldItem: TimeslotsItem, newItem: TimeslotsItem): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: TimeslotsItem, newItem: TimeslotsItem): Boolean {
            return oldItem.sessions.size == newItem.sessions.size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.agenda_rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = holder.itemView
        item.text.text = "$position"
    }
}