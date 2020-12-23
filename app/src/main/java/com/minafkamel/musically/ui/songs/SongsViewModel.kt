package com.minafkamel.musically.ui.songs

import androidx.lifecycle.MutableLiveData
import com.minafkamel.musically.domain.songs.GetSongs
import com.minafkamel.musically.domain.songs.SelectSong
import com.minafkamel.musically.extensions.withDefaultSchedulers
import com.minafkamel.musically.ui.base.BaseViewModel
import com.minafkamel.musically.ui.songs.mapping.SongsMapper
import timber.log.Timber

class SongsViewModel(
    private val permalink: String,
    private val getSongs: GetSongs,
    private val selectSong: SelectSong,
    private val mapper: SongsMapper
) : BaseViewModel() {

    val songsLiveData: MutableLiveData<List<SongViewEntity>> = MutableLiveData()
    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun onViewCreate() {
        getSongs.build(GetSongs.Params(permalink))
            .map { mapper.map(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doAfterTerminate { progressLiveData.postValue(false) }
            .withDefaultSchedulers()
            .subscribeToDisposeLater({ songsLiveData.postValue(it) }, { Timber.tag(TAG).e(it) })
    }

    fun songSelected(titleUrlPair: Pair<String, String>) {
        selectSong.build(SelectSong.Params(titleUrlPair.first, titleUrlPair.second))
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doAfterTerminate { progressLiveData.postValue(false) }
            .withDefaultSchedulers()
            .subscribeToDisposeLater({ /* NO-OP */ }, { Timber.tag(TAG).e(it) })
    }

    companion object {
        const val TAG = "SongsViewModel"
    }
}