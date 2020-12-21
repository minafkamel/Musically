package com.minafkamel.musically.ui.songs

import com.minafkamel.musically.data.SongRaw
import com.minafkamel.musically.util.StringProvider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SongsMapperTest : TestCase() {

    @Mock
    lateinit var stringProvider: StringProvider

    private lateinit var mapper: SongsMapper

    @Before
    fun setup() {
        mapper = SongsMapper(stringProvider)
    }

    @Test
    fun `maps from SongRaw to SongViewEntity`() {
        ArrangeBuilder().withString("Duration: 60 minutes")
        val title = "title"
        val duration = 600
        val thumb = "url"
        val songsRaw = listOf(SongRaw(title, duration, thumb))
        val expectedSubtitle = "Duration: 60 minutes"
        val expectedSongsViewEntities = listOf(SongViewEntity(title, expectedSubtitle, thumb))

        val entities = mapper.toModel(songsRaw)

        assertEquals(expectedSongsViewEntities, entities)
    }

    inner class ArrangeBuilder {

        fun withString(string: String) {
            whenever(stringProvider.getString(any())).thenReturn(string)
        }
    }
}