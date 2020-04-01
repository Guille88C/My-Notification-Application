package com.gcuestab.pushnotification.data

data class NotificationData(
    val id: Int,
    val isRead: Boolean,
    val date: Long,
    val title: String,
    val description: String
)