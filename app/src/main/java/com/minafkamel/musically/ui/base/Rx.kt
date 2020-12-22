package com.minafkamel.musically.ui.base

import com.minafkamel.musically.extensions.addTo
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

interface Rx {

    val d: CompositeDisposable

    /**
     * An extension methods that ensures disposal after subscribe
     * */
    fun <T> Single<T>.subscribeToDisposeLater(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        subscribe(onSuccess, onError).addTo(d)
    }
}
