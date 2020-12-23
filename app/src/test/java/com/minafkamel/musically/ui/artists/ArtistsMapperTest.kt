package com.minafkamel.musically.ui.artists

import com.minafkamel.musically.domain.artists.Artist
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
class ArtistsMapperTest : TestCase() {

    @Mock
    lateinit var stringProvider: StringProvider

    private lateinit var mapper: ArtistsMapper

    @Before
    fun setup() {
        mapper = ArtistsMapper(stringProvider)
    }

    @Test
    fun `maps from Artist to ArtistViewEntity`() {
        val permalink = "permalink"
        val name = "name"
        val caption = "caption"
        val url = "url"
        val description = "description"
        val tracksCount = 10
        val tracksString = " tracks"
        val expectedTracksString = "10 tracks"
        ArrangeBuilder().withString(tracksString)
        val artists = listOf(Artist(permalink, name, caption, url, tracksCount, description))
        val entities = listOf(
            ArtistViewEntity(permalink, name, caption, description, expectedTracksString, url)
        )

        val entity = mapper.map(artists)

        assertEquals(entities, entity)
    }

    inner class ArrangeBuilder {

        fun withString(string: String) {
            whenever(stringProvider.getString(any())).thenReturn(string)
        }
    }
}