package com.minafkamel.musically.di

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, CACHE_SIZE))
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    fun providesInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    single { providesInterceptor() }
    single { providesOkHttpClient(get(), get()) }
    single { providesRetrofit(get()) }
}

const val BASE_URL = "https://api-v2.hearthis.at/"
const val CACHE_SIZE: Long = 5 * 1024 * 1024 // 5MB
