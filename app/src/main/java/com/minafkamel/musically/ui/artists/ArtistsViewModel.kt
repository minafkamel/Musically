package com.minafkamel.musically.ui.artists

import androidx.lifecycle.MutableLiveData
import com.minafkamel.musically.domain.artists.GetArtists
import com.minafkamel.musically.domain.base.NoParams
import com.minafkamel.musically.extensions.withDefaultSchedulers
import com.minafkamel.musically.ui.base.BaseViewModel

class ArtistsViewModel(
    private val getArtists: GetArtists,
    private val artistsMapper: ArtistsMapper
) : BaseViewModel() {

    val artistsLiveData: MutableLiveData<List<ArtistViewEntity>> = MutableLiveData()
    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun onBind() {
        getArtists.build(NoParams)
            .map { artistsMapper.toModel(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doAfterTerminate { progressLiveData.postValue(false) }
            .withDefaultSchedulers()
            .subscribeToDisposeLater({ artistsLiveData.postValue(it) }, { it })
    }
}