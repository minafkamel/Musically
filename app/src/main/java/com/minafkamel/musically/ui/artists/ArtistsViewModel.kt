package com.minafkamel.musically.ui.artists

import androidx.lifecycle.MutableLiveData
import com.minafkamel.musically.domain.artists.GetArtists
import com.minafkamel.musically.domain.base.NoParams
import com.minafkamel.musically.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ArtistsViewModel(
    private val getArtists: GetArtists,
    private val artistsMapper: ArtistsMapper
) : BaseViewModel() {

    val artistsLiveData: MutableLiveData<List<ArtistViewEntity>> = MutableLiveData()

    override fun onBind(d: CompositeDisposable) {
        d.add(getArtists.build(NoParams)
            .map { artist -> artist.map { artistsMapper.toModel(it) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ artistsLiveData.postValue(it) }, { it })
        )
    }
}