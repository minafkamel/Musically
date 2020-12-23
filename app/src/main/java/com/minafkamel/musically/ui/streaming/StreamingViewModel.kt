package com.minafkamel.musically.ui.streaming

import androidx.lifecycle.MutableLiveData
import com.minafkamel.musically.domain.base.NoParams
import com.minafkamel.musically.domain.songs.GetSongStreamingInfo
import com.minafkamel.musically.extensions.withDefaultSchedulers
import com.minafkamel.musically.ui.base.BaseViewModel
import timber.log.Timber

class StreamingViewModel(private val getSongStreamingInfo: GetSongStreamingInfo) : BaseViewModel() {

    val streamingLiveData: MutableLiveData<Pair<String, String>> = MutableLiveData()

    override fun onViewCreate() {
        getSongStreamingInfo.build(NoParams)
            .withDefaultSchedulers()
            .subscribeToDisposeLater({ streamingLiveData.postValue(it) }, { Timber.tag(TAG).e(it) })
    }

    companion object {
        const val TAG = "StreamingViewModel"
    }
}