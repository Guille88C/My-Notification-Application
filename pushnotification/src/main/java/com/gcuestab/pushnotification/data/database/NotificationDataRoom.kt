package com.gcuestab.pushnotification.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gcuestab.pushnotification.data.NotificationData

@Entity(tableName = "notification_table")
internal data class NotificationDataRoom(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "is_read")
    val isRead: Boolean,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String
)

internal fun NotificationData.mapDataRoom(): NotificationDataRoom =
    NotificationDataRoom(
        id = id,
        isRead = isRead,
        date = date,
        title = title,
        description = description
    )

internal fun NotificationDataRoom.mapData(): NotificationData =
    NotificationData(
        id = id,
        isRead = isRead,
        date = date,
        title = title,
        description = description
    )

