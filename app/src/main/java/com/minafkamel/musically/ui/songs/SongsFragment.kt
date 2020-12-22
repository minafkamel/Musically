package com.minafkamel.musically.ui.songs

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.minafkamel.musically.R
import com.minafkamel.musically.ui.base.BaseFragment
import kotlinx.android.synthetic.main.f_recycler_view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SongsFragment : BaseFragment<SongsViewModel>(R.layout.f_recycler_view) {

    private val viewModel: SongsViewModel by viewModel {
        parametersOf(arguments?.get(BUNDLE_PERMALINK) as String)
    }

    override fun observeLiveData() {
        viewModel.songsLiveData.observe(this, { showSongs(it) })
        viewModel.progressLiveData.observe(this, { handleProgress(it) })
    }

    private fun handleProgress(show: Boolean) {
        progressView.isVisible = show
    }

    private fun showSongs(songs: List<SongViewEntity>) {
        recyclerView.adapter = SongsAdapter(songs) {}
    }

    override fun passViewModel() = viewModel

    companion object {
        const val TAG = "SongsFragment"
        private const val BUNDLE_PERMALINK = "BUNDLE_PERMALINK"

        fun newInstance(permalink: String): SongsFragment {
            return SongsFragment()
                .apply {
                    arguments = bundleOf(BUNDLE_PERMALINK to permalink)
                }
        }
    }
}