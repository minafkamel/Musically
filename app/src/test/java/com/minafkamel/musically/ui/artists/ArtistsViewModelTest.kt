package com.minafkamel.musically.ui.artists

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.minafkamel.musically.domain.artists.Artist
import com.minafkamel.musically.domain.artists.GetArtists
import com.minafkamel.musically.domain.base.NoParams
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
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ArtistsViewModelTest : TestCase() {

    @Mock
    lateinit var getArtists: GetArtists

    @Mock
    lateinit var artistsMapper: ArtistsMapper

    private lateinit var artistsViewModel: ArtistsViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        artistsViewModel = ArtistsViewModel(getArtists, artistsMapper)
    }

    @Test
    fun `Given that onBind is called  Then calls GetArtists use case and maps and posts value`() {
        val artists = listOf(Artist("", "", "", "", 0, ""))
        val entities = listOf(ArtistViewEntity("", "", "", "", "", ""))
        ArrangeBuilder()
            .withArtists(artists)
            .withViewEntity(entities)

        artistsViewModel.onBind()

        verify(getArtists).build(NoParams)
        verify(artistsMapper).toModel(artists)
        assertEquals(entities, artistsViewModel.artistsLiveData.value)
    }

    inner class ArrangeBuilder {

        fun withArtists(artists: List<Artist>) = apply {
            whenever(getArtists.build(any())).thenReturn(Single.just(artists))
        }

        fun withViewEntity(entities: List<ArtistViewEntity>) {
            whenever(artistsMapper.toModel(any())).thenReturn(entities)
        }
    }
}