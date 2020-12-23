package com.minafkamel.musically.di

import com.minafkamel.musically.ui.artists.ArtistsMapper
import com.minafkamel.musically.ui.songs.mapping.DurationMapper
import com.minafkamel.musically.ui.songs.mapping.SongsMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { ArtistsMapper(get()) }
    factory { SongsMapper(get(), get()) }
    factory { DurationMapper() }
}