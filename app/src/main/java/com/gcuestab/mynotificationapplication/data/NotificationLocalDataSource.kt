package com.gcuestab.mynotificationapplication.data

interface NotificationLocalDataSource {
    fun insertNotification(notification: NotificationData)
    fun readNotification(id: Int)
    fun getNotifications(): List<NotificationData>
}