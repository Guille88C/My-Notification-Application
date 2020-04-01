package com.gcuestab.mynotificationapplication.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcuestab.mynotificationapplication.data.NotificationData
import com.gcuestab.mynotificationapplication.data.NotificationRepository
import com.gcuestab.mynotificationapplication.view.entity.Notification
import com.gcuestab.mynotificationapplication.view.entity.toView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NotificationRepository) : ViewModel() {
    private val _notifications = MutableLiveData<List<Notification>>()

    val notifications: LiveData<List<Notification>>
        get() = _notifications

    init {
        getNotifications()
    }

    private fun getNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            val lNotifications = ArrayList<Notification>()

            repository.getNotifications().forEach { notificationData ->
                if (notificationData.type != NotificationData.Type.UNKNOWN) {
                    lNotifications.add(notificationData.toView())
                }
            }

            _notifications.postValue(lNotifications)
        }
    }

    fun refreshLaunched() {
        getNotifications()
    }
}