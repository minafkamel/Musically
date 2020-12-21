package com.minafkamel.musically.ui.artists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minafkamel.musically.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.i_artist.view.*

class ArtistsAdapter(private val models: List<ArtistViewEntity>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.i_artist, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArtistViewHolder).onBind(models[position])
    }

    override fun getItemCount() = models.size

    class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(model: ArtistViewEntity) {

            itemView.textViewTitle.text = model.title
            itemView.textViewDescription.text = model.description
            itemView.textViewTracks.text = model.tracksCount
            itemView.textViewSubtitle.text = model.subtitle
            Picasso.get()
                .load(model.url)
                .fit()
                .centerCrop()
                .into(itemView.imageViewArtist)
        }
    }
}