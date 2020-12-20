package com.minafkamel.musically.di

import com.minafkamel.musically.data.FeedApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun providesApi(retrofit: Retrofit): FeedApi {
        return retrofit.create(FeedApi::class.java)
    }
    single { providesApi(get()) }
}