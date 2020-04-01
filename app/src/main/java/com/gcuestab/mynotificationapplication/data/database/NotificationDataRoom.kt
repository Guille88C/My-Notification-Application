package com.gcuestab.mynotificationapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gcuestab.mynotificationapplication.data.NotificationData

@Entity(tableName = "notification_table")
data class NotificationDataRoom(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "is_read")
    val isRead: Boolean,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "description")
    val description: String
) {

    companion object {
        const val SEND = "envio"
        const val APPOINTMENT = "cita"
        const val UNKNOWN = "desconocido"
    }
}

fun NotificationData.mapDataRoom(): NotificationDataRoom =
    NotificationDataRoom(
        id = id,
        isRead = isRead,
        date = date,
        type = when (type) {
            NotificationData.Type.DELIVERY -> {
                NotificationDataRoom.SEND
            }
            NotificationData.Type.APPOINTMENT -> {
                NotificationDataRoom.APPOINTMENT
            }
            else -> {
                NotificationDataRoom.UNKNOWN
            }
        },
        description = description
    )

fun NotificationDataRoom.mapData(): NotificationData =
    NotificationData(
        id = id,
        isRead = isRead,
        date = date,
        type = when (type) {
            NotificationDataRoom.SEND -> {
                NotificationData.Type.DELIVERY
            }
            NotificationDataRoom.APPOINTMENT -> {
                NotificationData.Type.APPOINTMENT
            }
            else -> {
                NotificationData.Type.UNKNOWN
            }
        },
        description = description
    )

