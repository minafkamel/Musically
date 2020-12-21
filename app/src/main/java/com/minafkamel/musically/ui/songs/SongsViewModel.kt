package com.minafkamel.musically.ui.songs

import com.minafkamel.musically.domain.songs.GetSongs
import com.minafkamel.musically.extensions.withDefaultSchedulers
import com.minafkamel.musically.ui.base.BaseViewModel

class SongsViewModel(private val permalink: String, private val getSongs: GetSongs) : BaseViewModel() {

    override fun onViewCreate() {
        getSongs.build(GetSongs.Params(permalink))
            .withDefaultSchedulers()
            .subscribeToDisposeLater({ it }, { it })
    }
}