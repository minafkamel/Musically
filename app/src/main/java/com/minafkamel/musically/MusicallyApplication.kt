package com.minafkamel.musically

import android.app.Application
import com.minafkamel.musically.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MusicallyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MusicallyApplication)
            printLogger()
            modules(
                listOf(
                    networkModule,
                    apiModule,
                    dataModule,
                    domainModule,
                    viewModelModule,
                    mapperModule,
                    utilModule
                )
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}