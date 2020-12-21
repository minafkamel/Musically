package com.minafkamel.musically.di

import com.minafkamel.musically.ui.artists.ArtistsViewModel
import com.minafkamel.musically.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { ArtistsViewModel(get(), get()) }
}