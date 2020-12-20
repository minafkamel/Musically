package com.minafkamel.musically.domain.artists

import com.minafkamel.musically.data.FeedRepository
import com.minafkamel.musically.data.SongRaw
import com.minafkamel.musically.domain.base.NoParams
import com.minafkamel.musically.domain.base.UseCase
import io.reactivex.Single

class GetArtists(private val feedRepository: FeedRepository) :
    UseCase<NoParams, List<Artist>> {

    override fun build(params: NoParams): Single<List<Artist>> {
        return feedRepository.getFeed()
            .map { createArtists(it) }
    }

    private fun createArtists(songsRaw: List<SongRaw>): List<Artist> {
        require(songsRaw.isNotEmpty()) { "songsRaw is empty" }
        return songsRaw.map {
            createArtist((it.userRaw))
        }
    }

    private fun createArtist(userRaw: SongRaw.UserRaw?): Artist {
        with(requireNotNull(userRaw) { "userRaw is null" }) {
            val id = requireNotNull(id) { "id is null" }
            val userName = requireNotNull(userName) { "userName is null" }
            val caption = requireNotNull(caption) { "caption is null" }
            val avatarUrl = requireNotNull(avatarUrl) { "avatarUrl is null" }

            return Artist(id, userName, caption, avatarUrl)
        }
    }
}