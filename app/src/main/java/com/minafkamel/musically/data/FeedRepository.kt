package com.minafkamel.musically.data

import io.reactivex.Single

class FeedRepository(private val feedApi: FeedApi) {

    fun getFeed(): Single<List<SongRaw>> {
        return feedApi.getFeed()
    }
}