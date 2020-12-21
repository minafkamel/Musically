package com.minafkamel.musically.ui.main

import android.os.Bundle
import com.minafkamel.musically.R
import com.minafkamel.musically.ui.artists.ArtistsFragment
import com.minafkamel.musically.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class.java, R.layout.a_main) {

    override fun observeLiveData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadArtistsFragment()
    }

    private fun loadArtistsFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, ArtistsFragment.newInstance())
            .commit()
    }
}