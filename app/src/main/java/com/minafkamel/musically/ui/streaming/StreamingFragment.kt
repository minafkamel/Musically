package com.minafkamel.musically.ui.streaming

import android.media.MediaPlayer
import com.bumptech.glide.Glide
import com.minafkamel.musically.R
import com.minafkamel.musically.ui.base.BaseFragment
import kotlinx.android.synthetic.main.f_streaming.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StreamingFragment : BaseFragment<StreamingViewModel>(R.layout.f_streaming) {

    private val viewModel: StreamingViewModel by viewModel()

    var mediaPlayer: MediaPlayer? = null

    override fun observeLiveData() {
        viewModel.streamingLiveData.observe(this, { setStreamingUi(it) })
    }

    private fun setStreamingUi(titleUrlPair: Pair<String, String>) {
        Glide.with(requireActivity())
            .asGif()
            .load(STREAMING_GIF)
            .into(imageViewStreaming)

        textViewStreaming.text = titleUrlPair.first
        playSong(titleUrlPair.second)
    }

    private fun playSong(streamUrl: String) {
        if (mediaPlayer != null) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
        mediaPlayer = MediaPlayer()
            .apply {
                setOnPreparedListener {
                    // Starting the media player when it is prepared.
                    it.start()
                }
                setDataSource(streamUrl)
                // When the media player is prepared as Async, it will not block the UI thread.
                prepareAsync()
            }
    }

    override fun onDestroy() {
        mediaPlayer?.stop()
        super.onDestroy()
    }

    override fun passViewModel() = viewModel

    companion object {
        const val STREAMING_GIF = "https://media.giphy.com/media/PnJP8tvpbUvo1hVo4A/giphy.gif"

        fun newInstance() = StreamingFragment()
    }
}