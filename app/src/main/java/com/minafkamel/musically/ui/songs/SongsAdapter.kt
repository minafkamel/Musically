package com.minafkamel.musically.ui.songs

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minafkamel.musically.R
import com.minafkamel.musically.extensions.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.i_song.*

class SongsAdapter(
    private val entities: List<SongViewEntity>,
    private val clickListener: (Pair<String, String>) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SongViewHolder(parent.inflate(R.layout.i_song), clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SongViewHolder).onBind(entities[position])
    }

    override fun getItemCount() = entities.size

    class SongViewHolder(
        override val containerView: View,
        private val clickListener: (Pair<String, String>) -> Unit
    ) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun onBind(entity: SongViewEntity) {
            textViewTitle.text = entity.title
            textViewSubtitle.text = entity.subtitle

            Glide.with(containerView.context)
                .load(entity.imageUrl)
                .into(imageViewSong)

            itemView.setOnClickListener {
                clickListener.invoke(Pair(entity.title, entity.streamUrl))
            }
        }
    }
}