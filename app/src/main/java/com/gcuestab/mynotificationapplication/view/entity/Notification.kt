package com.gcuestab.mynotificationapplication.view.entity

import android.os.Parcelable
import com.gcuestab.mynotificationapplication.DAYS_PATTERN
import com.gcuestab.mynotificationapplication.HOURS_PATTERN
import com.gcuestab.pushnotification.data.NotificationData
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
    enum class Type(val description: String) {
        DELIVERY("envio"),
        APPOINTMENT("cita"),
        UNKNOWN("desconocido")
    }

    fun dateToHours(): String = getDateFormatted(pattern = HOURS_PATTERN)

    fun dateToDays(): String = getDateFormatted(pattern = DAYS_PATTERN)

    private fun getDateFormatted(pattern: String): String {
        val formatter = SimpleDateFormat1(pattern, Locale.getDefault())
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        return formatter.format(calendar.time)
    }

    fun isKnown() = type != Type.UNKNOWN
}

fun NotificationData.toView(): Notification =
    Notification(
        id = id,
        isRead = isRead,
        date = date,
        type = when (title) {
            "envio" -> Notification.Type.DELIVERY
            "cita" -> Notification.Type.APPOINTMENT
            else -> Notification.Type.UNKNOWN
        },
        description = description
    )

fun Notification.toData(): NotificationData =
    NotificationData(
        id = id,
        isRead = isRead,
        date = date,
        title = type.description,
        description = description
    )