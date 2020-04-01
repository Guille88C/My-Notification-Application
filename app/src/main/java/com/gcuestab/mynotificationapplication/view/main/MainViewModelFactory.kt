package com.gcuestab.mynotificationapplication.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcuestab.mynotificationapplication.data.NotificationRepository

class MainViewModelFactory(private val repository: NotificationRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(
        repository = repository
    ) as T
}