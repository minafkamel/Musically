package com.minafkamel.musically.ui.songs

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.minafkamel.musically.data.SongRaw
import com.minafkamel.musically.domain.base.NoResult
import com.minafkamel.musically.domain.songs.GetSongs
import com.minafkamel.musically.domain.songs.SelectSong
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SongsViewModelTest : TestCase() {

    private val permalink: String = "permalink"

    @Mock
    lateinit var getSongs: GetSongs

    @Mock
    lateinit var selectSong: SelectSong

    @Mock
    lateinit var mapper: SongsMapper

    lateinit var songsViewModel: SongsViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        songsViewModel = SongsViewModel("permalink", getSongs, selectSong, mapper)
    }

    @Test
    fun `Given that on view create is fired Then gets songs and maps and posts value`() {
        val songs = listOf(SongRaw("title", 10, "thumb", "stream url"))
        val entities = listOf(SongViewEntity("title", "subtitle", "image url", "stream url"))
        ArrangeBuilder()
            .withSongs(songs)
            .withMappedEntities(entities)

        songsViewModel.onViewCreate()

        verify(getSongs).build(GetSongs.Params(permalink))
        verify(mapper).toModel(songs)
        assertEquals(entities, songsViewModel.songsLiveData.value)
    }

    @Test
    fun `Given that a song is selected Then calls select song use case`() {
        val title = "song title"
        val url = "sing url"
        val pair: Pair<String, String> = Pair(title, url)
        ArrangeBuilder().withSelectSong()

        songsViewModel.songSelected(pair)

        verify(selectSong).build(SelectSong.Params(title, url))
    }

    inner class ArrangeBuilder {

        fun withSongs(songs: List<SongRaw>) = apply {
            whenever(getSongs.build(any())).thenReturn(Single.just(songs))
        }

        fun withMappedEntities(entities: List<SongViewEntity>) = apply {
            whenever(mapper.toModel(anyList())).thenReturn(entities)
        }

        fun withSelectSong() = apply {
            whenever(selectSong.build(any())).thenReturn(Single.just(NoResult))
        }
    }
}