package com.minafkamel.musically.ui.base

import com.minafkamel.musically.extensions.addTo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

interface Rx {

    val d: CompositeDisposable

    /**
     * An extension method that ensures disposal of a [Single] after subscribe
     * */
    fun <T> Single<T>.subscribeToDisposeLater(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        subscribe(onSuccess, onError).addTo(d)
    }

    /**
     * An extension methods that ensures disposal of an [Observable] after subscribe
     * */
    fun <T> Observable<T>.subscribeToDisposeLater(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        subscribe(onSuccess, onError).addTo(d)
    }
}
