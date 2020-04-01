package com.gcuestab.mynotificationapplication.data

data class NotificationData(
    val id: Int,
    val isRead: Boolean,
    val date: Long,
    val type: Type,
    val description: String
) {
    enum class Type {
        DELIVERY,
        APPOINTMENT,
        UNKNOWN
    }
}