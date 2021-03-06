package com.minafkamel.musically.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Base view model for all view models in the app.
 * Holds a [CompositeDisposable] from [Rx] that is cleared when view model is no longer in use.
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver, Rx {

    override val d: CompositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        onViewCreate()
    }

    abstract fun onViewCreate()

    override fun onCleared() {
        d.clear()
        super.onCleared()
    }
}