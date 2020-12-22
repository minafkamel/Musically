package com.minafkamel.musically.ui.streaming

import com.minafkamel.musically.R
import com.minafkamel.musically.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class StreamingFragment : BaseFragment<StreamingViewModel>(R.layout.f_streaming) {

    private val viewModel: StreamingViewModel by viewModel()

    override fun observeLiveData() {}

    override fun passViewModel() = viewModel

    companion object {

        fun newInstance() = StreamingFragment()
    }
}