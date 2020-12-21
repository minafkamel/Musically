package com.minafkamel.musically.ui.artists

import com.minafkamel.musically.R
import com.minafkamel.musically.ui.base.BaseFragment
import kotlinx.android.synthetic.main.f_artists.*

class ArtistsFragment : BaseFragment<ArtistsViewModel>(ArtistsViewModel::class.java, R.layout.f_artists) {

    override fun observeLiveData() {
        viewModel.artistsLiveData.observe(this, { showArtists(it)})
    }

    private fun showArtists(artists: List<ArtistViewEntity>) {
        recyclerViewArtists.adapter = ArtistsAdapter(artists)
    }

    companion object {
        fun newInstance() = ArtistsFragment()
    }
}