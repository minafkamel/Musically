package com.minafkamel.musically.data

import com.google.gson.annotations.SerializedName

class SongRaw(
    val title: String,
    val duration: Long,
    val thumb: String,
    @SerializedName("stream_url") val streamUrl: String
)