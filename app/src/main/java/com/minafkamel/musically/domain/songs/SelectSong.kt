package com.minafkamel.musically.domain.songs

import com.minafkamel.musically.data.SelectionRepository
import com.minafkamel.musically.domain.base.NoResult
import com.minafkamel.musically.domain.base.UseCase
import io.reactivex.Single

/**
 * This use case selects a certain song via the [SelectionRepository]
 */
class SelectSong(private val selectionRepository: SelectionRepository) :
    UseCase<SelectSong.Params, NoResult> {

    override fun build(params: Params): Single<NoResult> {
        return selectionRepository.setSongSelection(params.title, params.streamUrl)
            .toSingle { NoResult }
    }

    data class Params(val title: String, val streamUrl: String)

}