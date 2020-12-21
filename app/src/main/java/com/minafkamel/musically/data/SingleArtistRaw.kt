package com.minafkamel.musically.data

import com.google.gson.annotations.SerializedName

class SingleArtistRaw(@SerializedName("track_count") val trackCount: Int, val description: String)