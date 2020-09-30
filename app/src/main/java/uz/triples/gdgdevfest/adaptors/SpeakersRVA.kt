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
import kotlinx.android.synthetic.main.rv_item_agenda.view.constraintL
import kotlinx.android.synthetic.main.rv_item_speaker.view.*
import uz.triples.gdgdevfest.R
import uz.triples.gdgdevfest.database.entities.Speakers
import uz.triples.gdgdevfest.interfaces.SpeakersRVInterface

class SpeakersRVA(val context: Context, private val speakersRVInterface: SpeakersRVInterface) :
    ListAdapter<Speakers, SpeakersRVA.ViewHolder>(DiffCallBack()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class DiffCallBack() : DiffUtil.ItemCallback<Speakers>() {
        override fun areItemsTheSame(oldItem: Speakers, newItem: Speakers): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: Speakers,
            newItem: Speakers
        ): Boolean {
            return oldItem.title == newItem.title ||
                    oldItem.name == newItem.name ||
                    oldItem.bio == newItem.bio ||
                    oldItem.photo == newItem.photo ||
                    oldItem.shortBio == newItem.shortBio
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_speaker, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val item = getItem(position)
        view.speakerNameSpeaker.text = item.name
        view.degreeSpeaker.text = item.title
        view.shortBioSpeaker.text = item.shortBio
        Picasso.get().load(item.photo).placeholder(R.drawable.dev_fest_logo).into(view.imageSpeaker)

        when ((0..3).random()) {
            0 -> {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    view.constraintL.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.googleBlue)
                    view.underlineLayout.backgroundTintList = ContextCompat.getColorStateList(context, R.color.googleBlue)
                }
            }
            1 -> {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    view.constraintL.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.googleGreen)
                    view.underlineLayout.backgroundTintList = ContextCompat.getColorStateList(context, R.color.googleGreen)
                }
            }
            2 -> {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    view.constraintL.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.googleOrange)
                    view.underlineLayout.backgroundTintList = ContextCompat.getColorStateList(context, R.color.googleOrange)
                }
            }
            3 -> {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    view.constraintL.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.googleRed)
                    view.underlineLayout.backgroundTintList = ContextCompat.getColorStateList(context, R.color.googleRed)
                }
            }
        }
    }
}