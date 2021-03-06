package com.minafkamel.musically.ui.artists

/**
 * A view entity that represents an Artist item in the list
 */
data class ArtistViewEntity(
    val permalink:String,
    val title: String,
    val subtitle: String,
    val description: String,
    val tracksCount: String,
    val imageUrl: String
)