package com.minafkamel.musically.domain.songs

import com.minafkamel.musically.data.FeedRepository
import com.minafkamel.musically.data.SongRaw
import com.minafkamel.musically.domain.base.UseCase
import io.reactivex.Single

/**
 * This use case calls retrieves the songs of a permalink via [FeedRepository]
 */
class GetSongs(private val feedRepository: FeedRepository) :
    UseCase<GetSongs.Params, List<SongRaw>> {

    override fun build(params: Params): Single<List<SongRaw>> {
        return feedRepository.getSongs(params.permalink)
    }

    class Params(val permalink: String)
}