package com.minafkamel.musically.domain.artists

data class Artist(
    val permalink: String,
    val name: String,
    val caption: String,
    val url: String,
    val trackCount: Int,
    val description: String
)