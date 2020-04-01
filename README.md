# pushnotification module:
This module is able to save a notification with 2 String fields (_title_ and _description_) in a room database using a _WorkManager_..

# Functions:

1. With **_getNotificationRepository(context: Context): NotificationRepository_** function from **_Dependency.kt_** file it is possible to:

    1.1. Store a notification in a room database (**_insertNotification(notification: NotificationData)_**).

    1.2. Mark a notification as read (**_readNotification(notification: NotificationData)_**).
    
    1.3. Get all stored notifications (**_getNotifications(): List\<NotificationData\>_**).
    
2. When a notification is received (**_onMessageReceived(remoteMessage: RemoteMessage)_**), with **_MyNotificationManager_** object it is possible to:
    
    2.1. Save a notification in a room database (**_saveNotification(application: Application, title: String?, description: String?)_**).
    
    2.2. Show a notification (**_makeNotification(title: String, message: String, context: Context)_**).
