package com.minafkamel.musically.ui.songs

import com.minafkamel.musically.R
import com.minafkamel.musically.data.SongRaw
import com.minafkamel.musically.ui.base.Mapper
import com.minafkamel.musically.util.StringProvider

/**
 * Maps a list of [SongRaw] to a list of [SongViewEntity] and does any needed formatting
 */
class SongsMapper(private val stringProvider: StringProvider) :
    Mapper<List<SongRaw>, List<SongViewEntity>> {

    override fun toModel(input: List<SongRaw>): List<SongViewEntity> {
        return input.map {
            SongViewEntity(it.title, mapSubtitle(it.duration), it.thumb, it.streamUrl)
        }
    }

    private fun mapSubtitle(duration: Int): String {
        return String.format(
            stringProvider.getString(R.string.song_duration),
            (duration / SECONDS_PER_MINUTE).toString()
        )
    }

    companion object {
        private const val SECONDS_PER_MINUTE = 60
    }
}