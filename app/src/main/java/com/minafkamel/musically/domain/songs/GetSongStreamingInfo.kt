package com.minafkamel.musically.domain.songs

import com.minafkamel.musically.data.SelectionRepository
import com.minafkamel.musically.domain.base.NoParams
import com.minafkamel.musically.domain.base.ObservableUseCase
import io.reactivex.Observable

/**
 * This use case observes the publisher in [SelectionRepository]
 * and returns the emitted title and stream url pair
 */
class GetSongStreamingInfo(
    private val selectionRepository: SelectionRepository
) : ObservableUseCase<NoParams, Pair<String, String>> {

    override fun build(params: NoParams): Observable<Pair<String, String>> {
        return selectionRepository.selectionPublisher
            .map { it }
    }
}