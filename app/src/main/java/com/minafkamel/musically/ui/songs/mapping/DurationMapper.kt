package com.minafkamel.musically.ui.songs.mapping

import android.annotation.SuppressLint
import com.minafkamel.musically.ui.base.Mapper
import java.text.SimpleDateFormat
import java.util.*

/**
 * Maps melli seconds to formatted the duration of a song
 */
class DurationMapper: Mapper<Long, String> {

    @SuppressLint("SimpleDateFormat")
    override fun map(input: Long): String {
        val date = Date(input)
        val formatter = SimpleDateFormat(DURATION_FORMAT)
        return formatter.format(date)
    }
    companion object {
        const val DURATION_FORMAT = "HH:mm:ss"
    }
}