package com.minafkamel.musically.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import org.koin.androidx.viewmodel.compat.ViewModelCompat.getViewModel

/**
 * Base activity for all activities in the app.
 * A [BaseViewModel] should be also declared via generic parameter <VN: BaseViewModel> to provide an instance when the activity is created.
 */
abstract class BaseActivity<VM : BaseViewModel>(
    private val clazz: Class<VM>,
    @LayoutRes layoutResId: Int
) : AppCompatActivity(layoutResId), LifecycleOwner {

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(this, clazz)
        lifecycle.addObserver(viewModel)
        observeLiveData()
    }

    abstract fun observeLiveData()
}