package com.minafkamel.musically.ui.artists

import com.minafkamel.musically.R
import com.minafkamel.musically.domain.artists.Artist
import com.minafkamel.musically.ui.base.Mapper
import com.minafkamel.musically.util.StringProvider

/**
 * Maps the [Artist] object to [ArtistViewEntity] and does any needed formatting
 * ]
 */
class ArtistsMapper(private val stringProvider: StringProvider) : Mapper<Artist, ArtistViewEntity> {

    override fun toModel(input: Artist): ArtistViewEntity {
        return ArtistViewEntity(
            input.id,
            input.name,
            input.caption,
            input.description,
            mapTracksCount(input.trackCount),
            input.url
        )
    }

    private fun mapTracksCount(trackCount: Int): String {
        return trackCount.toString() + stringProvider.getString(R.string.artist_tracks_count)
    }
}