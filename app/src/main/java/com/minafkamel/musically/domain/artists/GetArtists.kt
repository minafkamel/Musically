package com.minafkamel.musically.domain.artists

import com.minafkamel.musically.data.FeedRepository
import com.minafkamel.musically.data.PopularRaw
import com.minafkamel.musically.data.SingleArtistRaw
import com.minafkamel.musically.domain.base.NoParams
import com.minafkamel.musically.domain.base.UseCase
import io.reactivex.Single

/**
 * This use case calls the popular and the single artist Apis and returns a list of [Artist]
 */
class GetArtists(private val feedRepository: FeedRepository) :
    UseCase<NoParams, List<Artist>> {

    override fun build(params: NoParams): Single<List<Artist>> {
        return feedRepository.getPopular()
            .flattenAsObservable { it }
            .flatMapSingle { popular -> getSingleArtistAndMapToArtist(popular) }
            .toList()
    }

    private fun getSingleArtistAndMapToArtist(popular: PopularRaw): Single<Artist> {
        return feedRepository.getSingleArtist(popular.userRaw.permalink)
            .map { createArtist(popular, it) }
    }

    private fun createArtist(popular: PopularRaw, singleArtistRaw: SingleArtistRaw): Artist {
        return Artist(
            popular.userRaw.permalink,
            popular.userRaw.userName,
            popular.userRaw.caption,
            popular.userRaw.avatarUrl,
            singleArtistRaw.trackCount,
            singleArtistRaw.description
        )
    }
}