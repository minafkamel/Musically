package com.minafkamel.musically.ui.songs

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minafkamel.musically.R
import com.minafkamel.musically.extensions.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.i_song.*

class SongsAdapter(
    private val entities: List<SongViewEntity>,
    private val clickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SongViewHolder(parent.inflate(R.layout.i_song), clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SongViewHolder).onBind(entities[position], position)
    }

    override fun getItemCount() = entities.size

    class SongViewHolder(
        override val containerView: View,
        private val clickListener: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun onBind(model: SongViewEntity, position: Int) {
            itemView.setOnClickListener { clickListener.invoke(position) }
            textViewTitle.text = model.title
            textViewSubtitle.text = model.subtitle
            Picasso.get()
                .load(model.imageUrl)
                .fit()
                .centerCrop()
                .into(imageViewSong)
        }
    }
}