package com.minafkamel.musically.ui.songs

import androidx.core.os.bundleOf
import com.minafkamel.musically.R
import com.minafkamel.musically.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SongsFragment : BaseFragment<SongsViewModel>(R.layout.f_songs) {

    private val viewModel: SongsViewModel by viewModel { parametersOf(arguments?.get(BUNDLE_PERMALINK) as String) }

    override fun observeLiveData() {}

    companion object {
        private const val BUNDLE_PERMALINK = "BUNDLE_PERMALINK"

        fun newInstance(permalink: String): SongsFragment {
            return SongsFragment()
                .apply {
                    arguments = bundleOf(BUNDLE_PERMALINK to permalink)
                }
        }
    }

    override fun passViewModel() = viewModel
}