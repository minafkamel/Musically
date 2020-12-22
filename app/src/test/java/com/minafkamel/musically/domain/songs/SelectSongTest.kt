package com.minafkamel.musically.domain.songs

import com.minafkamel.musically.data.SelectionRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SelectSongTest : TestCase() {

    @Mock
    lateinit var selectionRepository: SelectionRepository

    private lateinit var selectSong: SelectSong

    @Before
    fun setup() {
        selectSong = SelectSong(selectionRepository)
    }

    @Test
    fun `selects song in the selection repository`() {
        val title = "title"
        val streamUrl = "stream url"
        ArrangeBuilder().withSelection()

        selectSong.build(SelectSong.Params(title, streamUrl))

        verify(selectionRepository).setSongSelection(title, streamUrl)
    }

    inner class ArrangeBuilder {

        fun withSelection() = apply {
            whenever(selectionRepository.setSongSelection(any(), any()))
                .thenReturn(Completable.complete())
        }
    }
}