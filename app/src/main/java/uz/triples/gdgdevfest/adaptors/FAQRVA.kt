package uz.triples.gdgdevfest.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_faq.view.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.database.entities.FAQ

class FAQRVA(val context: Context, val list: List<FAQ>?) : RecyclerView.Adapter<FAQRVA.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_faq, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val view = holder.itemView
        view.questionFAQ.text = list?.get(position)?.question
        view.answerFAQ.text = list?.get(position)!!.answer

        when ((0..3).random()) {
            0 -> {
                view.ll.background = context.resources.getDrawable(R.color.googleBlue)
            }
            1 -> {
                view.ll.background = context.resources.getDrawable(R.color.googleGreen)
            }
            2 -> {
                view.ll.background = context.resources.getDrawable(R.color.googleOrange)
            }
            3 -> {
                view.ll.background = context.resources.getDrawable(R.color.googleRed)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (list!!.isNotEmpty())
            list.size
        else
            0
    }
}