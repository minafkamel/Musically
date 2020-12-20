package com.minafkamel.musically.domain

import com.minafkamel.musically.data.FeedRepository
import com.minafkamel.musically.data.SongRaw
import com.minafkamel.musically.domain.artists.Artist
import com.minafkamel.musically.domain.artists.GetArtists
import com.minafkamel.musically.domain.base.NoParams
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetArtistsTest : TestCase() {

    @Mock
    lateinit var feedRepository: FeedRepository

    private lateinit var getArtists: GetArtists

    @Before
    fun setup() {
        getArtists = GetArtists(feedRepository)
    }

    @Test
    fun `returns list of Artists`() {
        val id = "12"
        val userName = "userName"
        val caption = "caption"
        val avatarUrl = "avatarUrl"
        val songs = listOf(SongRaw(SongRaw.UserRaw(id, userName, caption, avatarUrl)))
        val expectedArtists =
            listOf(Artist(id, userName, caption, avatarUrl))
        ArrangeBuilder()
            .withSongsRaw(songs)

        val testObserver = getArtists.build(NoParams).test()

        verify(feedRepository).getFeed()
        testObserver.assertValue(expectedArtists)
        testObserver.assertComplete()
    }

    inner class ArrangeBuilder() {

        fun withSongsRaw(songs: List<SongRaw>) {
            whenever(feedRepository.getFeed()).thenReturn(Single.just(songs))
        }
    }

}