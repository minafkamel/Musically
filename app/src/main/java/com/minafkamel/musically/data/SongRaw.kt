package com.minafkamel.musically.data

import com.google.gson.annotations.SerializedName

class SongRaw(@SerializedName("user") val userRaw: UserRaw?) {

    class UserRaw(
        val id: String?,
        @SerializedName("username")
        val userName: String?,
        val caption: String?,
        @SerializedName("avatar_url")
        val avatarUrl: String?
    )
}