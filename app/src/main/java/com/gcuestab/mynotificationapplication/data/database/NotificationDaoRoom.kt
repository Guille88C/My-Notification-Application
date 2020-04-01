package com.gcuestab.mynotificationapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationDaoRoom {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notification: NotificationDataRoom)

    @Query(value = "SELECT * from notification_table")
    fun getNotifications(): List<NotificationDataRoom>

    @Query(value = "UPDATE notification_table SET is_read = :isRead WHERE id = :id")
    fun readNotification(id: Int, isRead: Boolean)
}