package com.gcuestab.pushnotification.data

import com.gcuestab.pushnotification.data.database.NotificationDataBaseRoom
import com.gcuestab.pushnotification.data.database.mapData
import com.gcuestab.pushnotification.data.database.mapDataRoom

internal class NotificationLocalDataSourceImpl(private val database: NotificationDataBaseRoom) :
    NotificationLocalDataSource {
    override fun insertNotification(notification: NotificationData) {
        database.notificationDao().insert(notification = notification.mapDataRoom())
    }

    override fun readNotification(id: Int) {
        database.notificationDao().readNotification(id = id, isRead = true)
    }

    override fun getNotifications(): List<NotificationData> {
        val lNotifications = ArrayList<NotificationData>()

        database.notificationDao().getNotifications().forEach { notification ->
            lNotifications.add(notification.mapData())
        }

        return lNotifications
    }
}