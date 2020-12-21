package com.minafkamel.musically.data

import io.reactivex.Single

class FeedRepository(private val feedApi: FeedApi) {

    fun getPopular(): Single<List<PopularRaw>> {
        return feedApi.getPopular()
    }

    fun getSingleArtist(permalink: String): Single<SingleArtistRaw> {
        return feedApi.getArtist(permalink)
    }
}