package com.gcuestab.mynotificationapplication.notification

import android.util.Log
import com.gcuestab.mynotificationapplication.APP_TAG
import com.gcuestab.mynotificationapplication.NOTIFICATION_DESCRIPTION_KEY
import com.gcuestab.mynotificationapplication.NOTIFICATION_TYPE_KEY
import com.gcuestab.pushnotification.notification.MyNotificationManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyNotificationService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d(APP_TAG, "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val type = getType(remoteMessage = remoteMessage)
        val description = getDescription(remoteMessage = remoteMessage)

        if (type.isNullOrEmpty() || description.isNullOrEmpty()) return

        MyNotificationManager.run {
            saveNotification(
                application = application,
                title = type,
                description = description
            )
            makeNotification(
                title = type,
                message = description,
                context = applicationContext
            )
        }
    }

    private fun getType(remoteMessage: RemoteMessage) =
        remoteMessage.data[NOTIFICATION_TYPE_KEY]

    private fun getDescription(remoteMessage: RemoteMessage) =
        remoteMessage.data[NOTIFICATION_DESCRIPTION_KEY]
}