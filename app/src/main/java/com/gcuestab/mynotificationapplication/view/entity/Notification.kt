package com.gcuestab.mynotificationapplication.view.entity

import android.os.Parcelable
import com.gcuestab.mynotificationapplication.DAYS_PATTERN
import com.gcuestab.mynotificationapplication.HOURS_PATTERN
import com.gcuestab.mynotificationapplication.data.NotificationData
import kotlinx.android.parcel.Parcelize
import java.util.*
import java.text.SimpleDateFormat as SimpleDateFormat1

@Parcelize
data class Notification(
    val id: Int,
    val isRead: Boolean,
    val date: Long,
    val type: Type,
    val description: String
) : Parcelable {
    enum class Type {
        DELIVERY,
        APPOINTMENT,
        UNKNOWN
    }

    fun dateToHours(): String = getDateFormatted(pattern = HOURS_PATTERN)

    fun dateToDays(): String = getDateFormatted(pattern = DAYS_PATTERN)

    private fun getDateFormatted(pattern: String): String {
        val formatter = SimpleDateFormat1(pattern, Locale.getDefault())
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        return formatter.format(calendar.time)
    }
}

fun NotificationData.toView(): Notification =
    Notification(
        id = id,
        isRead = isRead,
        date = date,
        type = when (type) {
            NotificationData.Type.DELIVERY -> Notification.Type.DELIVERY
            NotificationData.Type.APPOINTMENT -> Notification.Type.APPOINTMENT
            NotificationData.Type.UNKNOWN -> Notification.Type.UNKNOWN
        },
        description = description
    )

fun Notification.toData(): NotificationData =
    NotificationData(
        id = id,
        isRead = isRead,
        date = date,
        type = when (type) {
            Notification.Type.DELIVERY -> NotificationData.Type.DELIVERY
            Notification.Type.APPOINTMENT -> NotificationData.Type.APPOINTMENT
            Notification.Type.UNKNOWN -> NotificationData.Type.UNKNOWN
        },
        description = description
    )