package com.gcuestab.mynotificationapplication.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcuestab.pushnotification.data.NotificationRepository
import com.gcuestab.mynotificationapplication.view.entity.Notification

class DetailViewModelFactory(
    private val repository: NotificationRepository,
    private val notification: Notification?
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailViewModel(repository = repository, notification = notification) as T
}