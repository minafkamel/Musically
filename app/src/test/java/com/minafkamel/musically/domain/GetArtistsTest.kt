package com.minafkamel.musically.domain

import com.minafkamel.musically.data.FeedRepository
import com.minafkamel.musically.data.PopularRaw
import com.minafkamel.musically.data.SingleArtistRaw
import com.minafkamel.musically.domain.artists.Artist
import com.minafkamel.musically.domain.artists.GetArtists
import com.minafkamel.musically.domain.base.NoParams
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.Collections.singletonList

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
    fun `returns list of Artists from repository`() {
        val id = "12"
        val userName = "userName"
        val permalink = "permalink"
        val caption = "caption"
        val avatarUrl = "avatarUrl"
        val trackCount = 1
        val description = "description"
        val popularRaw = singletonList(
            PopularRaw(PopularRaw.UserRaw(id, permalink, userName, caption, avatarUrl))
        )
        val singleArtistRaw = SingleArtistRaw(trackCount, description)
        val expectedArtists =
            listOf(Artist(id, userName, caption, avatarUrl, trackCount, description))
        ArrangeBuilder()
            .withPopular(popularRaw)
            .withSingleArtist(singleArtistRaw)

        val testObserver = getArtists.build(NoParams).test()

        verify(feedRepository).getPopular()
        verify(feedRepository).getSingleArtist(permalink)
        testObserver.assertValue(expectedArtists)
        testObserver.assertComplete()
    }

    inner class ArrangeBuilder {

        fun withPopular(songs: List<PopularRaw>) = apply {
            whenever(feedRepository.getPopular()).thenReturn(Single.just(songs))
        }

        fun withSingleArtist(singleArtistRaw: SingleArtistRaw) = apply {
            whenever(feedRepository.getSingleArtist(any())).thenReturn(Single.just(singleArtistRaw))
        }
    }

}