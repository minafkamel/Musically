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
        val id = "id"
        val name = "name"
        val caption = "caption"
        val url = "url"
        val description = "description"
        val tracksCount = 10
        val tracksString = " tracks"
        val expectedTracksString = "10 tracks"
        ArrangeBuilder().withString(tracksString)
        val artist = Artist(id, name, caption, url, tracksCount, description)
        val expectedEntity = ArtistViewEntity(id, name, caption, description, expectedTracksString, url)

        val entity = mapper.toModel(artist)

        assertEquals(expectedEntity, entity)
    }

    inner class ArrangeBuilder {

        fun withString(string: String) {
            whenever(stringProvider.getString(any())).thenReturn(string)
        }
    }
}