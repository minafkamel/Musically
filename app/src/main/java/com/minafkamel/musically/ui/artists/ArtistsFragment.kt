package com.minafkamel.musically.ui.artists

import androidx.core.view.isVisible
import com.minafkamel.musically.R
import com.minafkamel.musically.ui.base.BaseFragment
import kotlinx.android.synthetic.main.f_artists.*

class ArtistsFragment :
    BaseFragment<ArtistsViewModel>(ArtistsViewModel::class.java, R.layout.f_artists) {

    override fun observeLiveData() {
        viewModel.artistsLiveData.observe(this, { showArtists(it) })
        viewModel.progressLiveData.observe(this, { handleProgress(it) })
    }

    private fun handleProgress(show: Boolean) {
        progressViewArtists.isVisible = show
    }

    private fun showArtists(artists: List<ArtistViewEntity>) {
        recyclerViewArtists.adapter = ArtistsAdapter(artists) { handleArtistClick(it) }
    }

    private fun handleArtistClick(artistId: String) {

    }


    companion object {
        fun newInstance() = ArtistsFragment()
    }
}
