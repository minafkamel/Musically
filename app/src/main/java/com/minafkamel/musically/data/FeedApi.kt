package com.minafkamel.musically.data

import io.reactivex.Single
import retrofit2.http.GET

interface FeedApi {

    @GET("feed/?type=popular&page=1&count=5")
    fun getFeed(): Single<List<Song>>

}