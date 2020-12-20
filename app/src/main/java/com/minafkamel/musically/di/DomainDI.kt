package com.minafkamel.musically.di

import com.minafkamel.musically.domain.artists.GetArtists
import org.koin.dsl.module

val domainModule = module {
    factory { GetArtists(get()) }
}