package com.minafkamel.musically.di

import com.minafkamel.musically.domain.artists.GetArtists
import com.minafkamel.musically.domain.songs.GetSongs
import org.koin.dsl.module

val domainModule = module {
    factory { GetArtists(get()) }
    factory { GetSongs(get()) }
}