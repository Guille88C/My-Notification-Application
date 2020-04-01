package com.gcuestab.pushnotification.di

import android.content.Context
import com.gcuestab.pushnotification.data.NotificationLocalDataSourceImpl
import com.gcuestab.pushnotification.data.NotificationRepository
import com.gcuestab.pushnotification.data.database.NotificationDataBaseRoom

private var notificationRepository: NotificationRepository? = null

fun getNotificationRepository(context: Context): NotificationRepository {
    return notificationRepository ?: NotificationRepository(
        localDataSource = NotificationLocalDataSourceImpl(
            database = NotificationDataBaseRoom.getDataBase(
                context
            )
        )
    ).apply {
        notificationRepository = this
    }
}