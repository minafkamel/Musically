package com.minafkamel.musically.ui.main

import android.os.Bundle
import com.minafkamel.musically.R
import com.minafkamel.musically.ui.artists.ArtistsFragment
import com.minafkamel.musically.ui.base.BaseActivity
import com.minafkamel.musically.ui.songs.SongsFragment

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

    fun artistClicked(permalink: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, SongsFragment.newInstance(permalink))
            .commit()
    }
}