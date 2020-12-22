package com.minafkamel.musically.domain.songs

import com.minafkamel.musically.data.SelectionRepository
import com.minafkamel.musically.domain.base.NoParams
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.subjects.PublishSubject
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetSongStreamingInfoTest : TestCase() {

    @Mock
    lateinit var selectionRepository: SelectionRepository

    lateinit var getSongStreamingInfo: GetSongStreamingInfo

    @Before
    fun setup() {
        getSongStreamingInfo = GetSongStreamingInfo(selectionRepository)
    }

    @Test
    fun `Given that selection repository publishes Then emits with the same value`() {
        val title = "title"
        val url = "url"
        val pair = Pair(title, url)
        val subject: PublishSubject<Pair<String, String>> = PublishSubject.create()
        whenever(selectionRepository.selectionPublisher).thenReturn(subject)

        val testObserver = getSongStreamingInfo.build(NoParams).test()
        subject.onNext(pair)

        testObserver.assertValue(pair)
    }
}