package com.minafkamel.musically.data

import com.google.gson.annotations.SerializedName

class PopularRaw(@SerializedName("user") val userRaw: UserRaw) {

    class UserRaw(
        val permalink: String,
        @SerializedName("username")
        val userName: String,
        val caption: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
    )
}