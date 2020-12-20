package com.minafkamel.musically.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import org.koin.androidx.viewmodel.compat.ViewModelCompat

/**
 * Base Fragment for all fragments in the app.
 * A [BaseViewModel] should be also declared via generic parameter <VM: BaseViewModel> to provide an instance when the fragment is created.
 */
abstract class BaseFragment<VM : BaseViewModel>(private val clazz: Class<VM>) : Fragment(),
    LifecycleOwner {

    lateinit var viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelCompat.getViewModel(this, clazz)
        lifecycle.addObserver(viewModel)
        observeLiveData()
    }

    abstract fun observeLiveData()
}