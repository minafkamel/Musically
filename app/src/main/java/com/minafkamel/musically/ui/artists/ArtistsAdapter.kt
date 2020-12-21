package com.minafkamel.musically.ui.artists

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minafkamel.musically.R
import com.minafkamel.musically.extensions.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.i_artist.*

class ArtistsAdapter(
    private val models: List<ArtistViewEntity>,
    private val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArtistViewHolder(parent.inflate(R.layout.i_artist), clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArtistViewHolder).onBind(models[position])
    }

    override fun getItemCount() = models.size

    class ArtistViewHolder(
        override val containerView: View,
        private val clickListener: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun onBind(model: ArtistViewEntity) {
            itemView.setOnClickListener { clickListener.invoke(model.id) }
            textViewTitle.text = model.title
            textViewDescription.text = model.description
            textViewTracks.text = model.tracksCount
            textViewSubtitle.text = model.subtitle
            Picasso.get()
                .load(model.url)
                .fit()
                .centerCrop()
                .into(imageViewArtist)
        }
    }
}