package com.minafkamel.musically.ui.songs.mapping

import com.minafkamel.musically.R
import com.minafkamel.musically.data.SongRaw
import com.minafkamel.musically.ui.base.Mapper
import com.minafkamel.musically.ui.songs.SongViewEntity
import com.minafkamel.musically.util.StringProvider

/**
 * Maps a list of [SongRaw] to a list of [SongViewEntity] and does any needed formatting
 */
class SongsMapper(
    private val stringProvider: StringProvider,
    private val durationMapper: DurationMapper
) :
    Mapper<List<SongRaw>, List<SongViewEntity>> {

    override fun map(input: List<SongRaw>): List<SongViewEntity> {
        return input.map {
            SongViewEntity(it.title, mapSubtitle(it.duration), it.thumb, it.streamUrl)
        }
    }

    private fun mapSubtitle(duration: Long): String {
        return "${stringProvider.getString(R.string.song_duration)}${durationMapper.map(duration)}"
    }
}