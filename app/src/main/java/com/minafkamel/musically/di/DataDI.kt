package com.minafkamel.musically.di

import com.minafkamel.musically.data.FeedRepository
import com.minafkamel.musically.data.SelectionRepository
import org.koin.dsl.module

val dataModule = module {
    single { FeedRepository(get()) }
    single { SelectionRepository() }
}
