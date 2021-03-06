package uz.triples.gdgdevfest.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item_agenda.view.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.interfaces.AgendaRVInterface
import uz.triples.gdgdevfest.models.AgendaItemModel

class AgendaRVA(val context: Context, private val agendaRVInterface: AgendaRVInterface) :
    ListAdapter<AgendaItemModel, AgendaRVA.ViewHolder>(DiffCallBack()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class DiffCallBack() : DiffUtil.ItemCallback<AgendaItemModel>() {
        override fun areItemsTheSame(oldItem: AgendaItemModel, newItem: AgendaItemModel): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: AgendaItemModel,
            newItem: AgendaItemModel
        ): Boolean {
            return oldItem.title == newItem.title ||
                    oldItem.image == newItem.image||
                    oldItem.duration == newItem.duration||
                    oldItem.speakers == newItem.speakers ||
                    oldItem.startTime == newItem.startTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_agenda, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val item = getItem(position)
        view.titleAgendaItem.text = item.title
        view.cardAgenda.setOnClickListener {
            agendaRVInterface.getFullInfo(item)
        }
        if (item.speakers.isNotEmpty()) {
            view.nameAgendaItem.text = item.speakers[0]
        }
        else {
            view.nameAgendaItem.visibility = View.GONE
            view.descriptionAgendaItem.visibility = View.VISIBLE
            view.descriptionAgendaItem.text = item.description
            view.titleAgendaItem.maxLines = 1
        }
        if (item.degree != null)
            view.degreeAgendaItem.text = item.degree
        else
            view.degreeAgendaItem.visibility = View.GONE
        view.dateAgendaItem.text = item.date
        view.startTimeAgendaItem.text = item.startTime
        view.durationTimeAgendaItem.text = "${item.duration} min"
        if (item.image != null)
            Picasso.get().load(item.image).error(R.drawable.ic_puzzle)
                .into(view.imageAgendaItem)
        when ((0..3).random()) {
            0 -> {
                view.timeLayout.background = ContextCompat.getDrawable(context, R.color.googleBlue)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    view.constraintL.foregroundTintList =
                        ContextCompat.getColorStateList(context, R.color.googleBlue)
                }
            }
            1 -> {
                view.timeLayout.background = ContextCompat.getDrawable(context, R.color.googleGreen)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    view.constraintL.foregroundTintList =
                        ContextCompat.getColorStateList(context, R.color.googleGreen)
                }
            }
            2 -> {
                view.timeLayout.background =
                    ContextCompat.getDrawable(context, R.color.googleOrange)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    view.constraintL.foregroundTintList =
                        ContextCompat.getColorStateList(context, R.color.googleOrange)
                }
            }
            3 -> {
                view.timeLayout.background = ContextCompat.getDrawable(context, R.color.googleRed)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    view.constraintL.foregroundTintList =
                        ContextCompat.getColorStateList(context, R.color.googleRed)
                }
            }
        }
    }
}