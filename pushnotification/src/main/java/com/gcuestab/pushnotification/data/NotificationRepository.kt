package com.gcuestab.pushnotification.data

class NotificationRepository(private val localDataSource: NotificationLocalDataSource) {
    fun getNotifications() = localDataSource.getNotifications()

    fun insertNotification(notification: NotificationData) {
        localDataSource.insertNotification(notification = notification)
    }

    fun readNotification(notification: NotificationData) {
        localDataSource.readNotification(id = notification.id)
    }

    fun clearNotifications() {
        localDataSource.clearNotifications()
    }
}