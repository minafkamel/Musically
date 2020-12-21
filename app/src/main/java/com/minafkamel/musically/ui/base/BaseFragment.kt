package com.minafkamel.musically.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

/**
 * Base Fragment for all fragments in the app.
 * A [BaseViewModel] should be also declared via generic parameter <VM: BaseViewModel> to provide an instance when the fragment is created.
 */
abstract class BaseFragment<VM : BaseViewModel>(
    private val clazz: Class<VM>,
    @LayoutRes layoutResId: Int
) : Fragment(layoutResId), LifecycleOwner {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(passViewModel())
        observeLiveData()
    }

    abstract fun passViewModel(): VM

    abstract fun observeLiveData()
}