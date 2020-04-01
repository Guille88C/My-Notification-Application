package com.gcuestab.pushnotification.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NotificationDataRoom::class], version = 1, exportSchema = false)
internal abstract class NotificationDataBaseRoom : RoomDatabase() {
    abstract fun notificationDao(): NotificationDaoRoom

    companion object {
        @Volatile
        private var INSTANCE: NotificationDataBaseRoom? = null

        fun getDataBase(context: Context): NotificationDataBaseRoom {
            return INSTANCE
                ?: run {
                synchronized(NotificationDataBaseRoom::class.java) {
                    Room.databaseBuilder(
                        context.applicationContext,
                        NotificationDataBaseRoom::class.java,
                        "notification_database"
                    ).build().also { database ->
                        INSTANCE = database
                    }
                }
            }
        }
    }
}