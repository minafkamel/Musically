package com.minafkamel.musically.extensions

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * An extension method to subscribes and observes on the default schedulers
 */
fun <T> Single<T>.withDefaultSchedulers(): Single<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

/**
 * An extension methods that adds disposables to the [CompositeDisposable] instance
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable = apply {
    compositeDisposable.add(this)
}