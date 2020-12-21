package com.minafkamel.musically.di

import com.minafkamel.musically.ui.artists.ArtistsMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { ArtistsMapper(get()) }
}