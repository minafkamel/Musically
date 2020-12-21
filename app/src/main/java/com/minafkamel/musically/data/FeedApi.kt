package com.minafkamel.musically.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FeedApi {

    @GET("feed/?type=popular&page=1&count=5")
    fun getPopular(): Single<List<PopularRaw>>

    @GET("{permalink}")
    fun getArtist(@Path("permalink") permalink: String): Single<SingleArtistRaw>
}