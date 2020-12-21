package com.minafkamel.musically.ui.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minafkamel.musically.R
import com.minafkamel.musically.ui.base.BaseFragment
import kotlinx.android.synthetic.main.f_artists.*

class ArtistsFragment : BaseFragment<ArtistsViewModel>(ArtistsViewModel::class.java) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.f_artists, container, false)
    }

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