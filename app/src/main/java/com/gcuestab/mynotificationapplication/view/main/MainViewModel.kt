package com.gcuestab.mynotificationapplication.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcuestab.mynotificationapplication.view.entity.Notification
import com.gcuestab.mynotificationapplication.view.entity.toView
import com.gcuestab.pushnotification.data.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NotificationRepository) : ViewModel() {
    private val _notifications = MutableLiveData<List<Notification>>()

    val notifications: LiveData<List<Notification>>
        get() = _notifications

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getNotifications()
        }
    }

    private fun getNotifications() {
        val lNotifications = ArrayList<Notification>()

        repository.getNotifications().forEach { notificationData ->
            val notificationItem = notificationData.toView()
            if (notificationItem.isKnown()) {
                lNotifications.add(notificationData.toView())
            }
        }

        _notifications.postValue(lNotifications)
    }

    fun refreshLaunched() {
        viewModelScope.launch(Dispatchers.IO) {
            getNotifications()
        }
    }

    fun clearPressed() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearNotifications()
            getNotifications()
        }
    }
}