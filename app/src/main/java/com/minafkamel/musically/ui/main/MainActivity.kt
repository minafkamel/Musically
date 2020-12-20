package com.minafkamel.musically.ui.main

import com.minafkamel.musically.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class.java) {

    override fun observeLiveData() {}
}