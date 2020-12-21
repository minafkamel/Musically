package com.minafkamel.musically.ui.artists

import com.minafkamel.musically.R
import com.minafkamel.musically.domain.artists.Artist
import com.minafkamel.musically.ui.base.Mapper
import com.minafkamel.musically.util.StringProvider

/**
 * Maps a list of [Artist] to a list of [ArtistViewEntity] and does any needed formatting
 * ]
 */
class ArtistsMapper(private val stringProvider: StringProvider) :
    Mapper<List<Artist>, List<ArtistViewEntity>> {

    override fun toModel(input: List<Artist>): List<ArtistViewEntity> {
        return input.map {
            ArtistViewEntity(
                it.id,
                it.name,
                it.caption,
                it.description,
                mapTracksCount(it.trackCount),
                it.url
            )
        }
    }

    private fun mapTracksCount(trackCount: Int): String {
        return trackCount.toString() + stringProvider.getString(R.string.artist_tracks_count)
    }
}