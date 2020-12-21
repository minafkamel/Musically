package com.minafkamel.musically.ui.artists

import androidx.core.view.isVisible
import com.minafkamel.musically.R
import com.minafkamel.musically.ui.base.BaseFragment
import com.minafkamel.musically.ui.main.MainActivity
import kotlinx.android.synthetic.main.f_artists.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtistsFragment : BaseFragment<ArtistsViewModel>(R.layout.f_artists) {

    private val viewModel: ArtistsViewModel by viewModel()

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
        (requireActivity() as MainActivity).artistClicked(artistId)
    }

    override fun passViewModel() = viewModel

    companion object {
        fun newInstance() = ArtistsFragment()
    }
}
