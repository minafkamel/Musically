package com.minafkamel.musically.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import org.koin.androidx.viewmodel.compat.ViewModelCompat.getViewModel

/**
 * Base activity for all activities in the app.
 * A [BaseViewModel] should be also declared via generic parameter <VN: BaseViewModel> to provide an instance when the activity is created.
 */
abstract class BaseActivity<VM : BaseViewModel>(private val clazz: Class<VM>) : AppCompatActivity(),
    LifecycleOwner {

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(this, clazz)
        lifecycle.addObserver(viewModel)
        observeLiveData()
    }

    abstract fun observeLiveData()
}