package com.gcuestab.pushnotification.di

import com.gcuestab.pushnotification.data.NotificationLocalDataSourceImpl
import com.gcuestab.pushnotification.data.NotificationRepository
import com.gcuestab.pushnotification.data.database.NotificationDataBaseRoom
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val notificationModule = module {
    single {
        NotificationRepository(
            localDataSource = NotificationLocalDataSourceImpl(
                database = NotificationDataBaseRoom.getDataBase(
                    context = androidApplication()
                )
            )
        )
    }
}