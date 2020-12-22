package com.minafkamel.musically.domain.songs

import com.minafkamel.musically.data.FeedRepository
import com.minafkamel.musically.data.SongRaw
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetSongsTest : TestCase() {

    @Mock
    lateinit var feedRepository: FeedRepository

    private lateinit var getSongs: GetSongs

    @Before
    fun setup() {
        getSongs = GetSongs(feedRepository)
    }

    @Test
    fun `returns list of Songs from repository`() {
        val songs =
            listOf(
                SongRaw("title1", 126, "thumb1", "streamUrl1"),
                SongRaw("titl2", 312, "thumb2","streamUrl2")
            )
        ArrangeBuilder().withSongs(songs)

        val testObserver = getSongs.build(GetSongs.Params("")).test()

        testObserver.assertValue(songs)
    }

    inner class ArrangeBuilder {

        fun withSongs(songs: List<SongRaw>) {
            whenever(feedRepository.getSongs(any())).thenReturn(Single.just(songs))
        }
    }
}