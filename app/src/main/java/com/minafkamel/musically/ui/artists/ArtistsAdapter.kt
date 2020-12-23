package com.minafkamel.musically.ui.artists

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minafkamel.musically.R
import com.minafkamel.musically.extensions.inflate
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
        fun onBind(entity: ArtistViewEntity) {
            itemView.setOnClickListener { clickListener.invoke(entity.permalink) }
            textViewTitle.text = entity.title
            textViewDescription.text = entity.description
            textViewTracks.text = entity.tracksCount
            textViewSubtitle.text = entity.subtitle

            Glide.with(containerView.context)
                .load(entity.imageUrl)
                .into(imageViewArtist)
        }
    }
}