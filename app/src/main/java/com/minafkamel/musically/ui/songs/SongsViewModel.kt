package com.minafkamel.musically.ui.songs

import androidx.lifecycle.MutableLiveData
import com.minafkamel.musically.domain.songs.GetSongs
import com.minafkamel.musically.extensions.withDefaultSchedulers
import com.minafkamel.musically.ui.base.BaseViewModel

class SongsViewModel(
    private val permalink: String,
    private val getSongs: GetSongs,
    private val mapper: SongsMapper
) : BaseViewModel() {

    val songsLiveData: MutableLiveData<List<SongViewEntity>> = MutableLiveData()
    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun onViewCreate() {
        getSongs.build(GetSongs.Params(permalink))
            .map { mapper.toModel(it) }
            .withDefaultSchedulers()
            .subscribeToDisposeLater({ songsLiveData.postValue(it) }, { it })
    }
}