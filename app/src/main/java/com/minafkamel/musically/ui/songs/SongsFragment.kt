package com.minafkamel.musically.ui.songs

import android.os.Bundle
import com.minafkamel.musically.R
import com.minafkamel.musically.ui.base.BaseFragment

class SongsFragment : BaseFragment<SongsViewModel>(SongsViewModel::class.java, R.layout.f_songs) {

    override fun observeLiveData() {}

    companion object {
        private const val BUNDLE_PERMALINK = "BUNDLE_PERMALINK"

        fun newInstance(permalink: String): SongsFragment {
            return SongsFragment()
                .apply {
                    arguments = Bundle()
                        .apply { putString(BUNDLE_PERMALINK, permalink) }
                }
        }
    }
}