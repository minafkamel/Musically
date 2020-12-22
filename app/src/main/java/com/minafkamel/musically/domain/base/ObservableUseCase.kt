package com.minafkamel.musically.domain.base

import io.reactivex.Observable

/**
 * A base observable use case for all observable use cases in the app
 */
interface ObservableUseCase<Params, Result> {

    fun build(params: Params): Observable<Result>
}