package com.minafkamel.musically.di

import com.minafkamel.musically.util.StringProvider
import org.koin.dsl.module

val utilModule = module {
    factory { StringProvider(get()) }
}