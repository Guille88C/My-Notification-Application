package com.gcuestab.mynotificationapplication.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcuestab.mynotificationapplication.data.NotificationRepository
import com.gcuestab.mynotificationapplication.view.entity.Notification
import com.gcuestab.mynotificationapplication.view.entity.toData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(repository: NotificationRepository, notification: Notification?) :
    ViewModel() {

    private val _notification = MutableLiveData<Notification>()
    val notification: LiveData<Notification>
        get() = _notification

    init {
        if (notification != null) {
            _notification.value = notification

            viewModelScope.launch(Dispatchers.IO) {
                repository.readNotification(notification = notification.toData())
            }
        }
    }
}