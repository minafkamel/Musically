package com.minafkamel.musically.ui.main

import android.os.Bundle
import com.minafkamel.musically.R
import com.minafkamel.musically.ui.artists.ArtistsFragment
import com.minafkamel.musically.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class.java) {

    override fun observeLiveData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.content, ArtistsFragment.newInstance())
            .commit()
    }
}