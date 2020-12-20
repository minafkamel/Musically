package com.minafkamel.musically

import android.app.Application
import com.minafkamel.musically.di.apiModule
import com.minafkamel.musically.di.dataModule
import com.minafkamel.musically.di.networkModule
import com.minafkamel.musically.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MusicallyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MusicallyApplication)
            printLogger()
            modules(listOf(networkModule, apiModule, dataModule, viewModelModule))
        }
    }
}