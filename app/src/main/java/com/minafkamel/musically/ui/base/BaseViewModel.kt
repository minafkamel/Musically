package com.minafkamel.musically.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Base view model for all view models in the app.
 * Holds a [CompositeDisposable] that is cleared when view model is no longer in use.
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    val d = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        onBind(d)
    }

    abstract fun onBind(d: CompositeDisposable)

    override fun onCleared() {
        d.clear()
        super.onCleared()
    }
}