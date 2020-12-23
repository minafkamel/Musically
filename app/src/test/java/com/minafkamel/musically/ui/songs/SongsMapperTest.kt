package com.minafkamel.musically.ui.songs

import com.minafkamel.musically.data.SongRaw
import com.minafkamel.musically.ui.songs.mapping.DurationMapper
import com.minafkamel.musically.ui.songs.mapping.SongsMapper
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

    @Mock
    lateinit var durationMapper: DurationMapper

    private lateinit var mapper: SongsMapper

    @Before
    fun setup() {
        mapper = SongsMapper(stringProvider, durationMapper)
    }

    @Test
    fun `maps from SongRaw to SongViewEntity`() {
        val durationTime = "1:00:05"
        val durationString = "Duration"
        ArrangeBuilder()
            .withString(durationString)
            .withDuration(durationTime)
        val title = "title"
        val thumb = "url"
        val streamUrl = "stream url"
        val songsRaw = listOf(SongRaw(title, 10, thumb, streamUrl))
        val expectedSubtitle = "$durationString$durationTime"
        val expectedSongsViewEntities = listOf(SongViewEntity(title, expectedSubtitle, thumb, streamUrl))

        val entities = mapper.map(songsRaw)

        assertEquals(expectedSongsViewEntities, entities)
    }

    inner class ArrangeBuilder {

        fun withString(string: String) = apply {
            whenever(stringProvider.getString(any())).thenReturn(string)
        }

        fun withDuration(string: String) = apply {
            whenever(durationMapper.map(any())).thenReturn(string)
        }
    }
}