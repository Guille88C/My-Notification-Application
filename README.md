# My-Notification-Application
Application that receives a push notification and save it in a room database.

It uses koin for dependency injection.

# To use it:

1. Add _notificationModule_ in the application using koin, with this module it is possible to use a _NotificationRepository_ for next purposes:

    1.1. Insert a notification in the room database (_insertNotification_).

    1.2. Mark a notification as read (_readNotification_).
    
    1.3. Get all saved notifications from the room databse (_getNotifications_).
    
2. When a notification is received (_onMessageReceived(remoteMessage: RemoteMessage)_), it is possible to do next things using _MyNotificationManager_ object:
    
    2.1. Save the notification in the room database using _MyNotificationManager::saveNotification_ function.
    
    2.2. Show the notification using the __MyNotificationManager::makeNotification_ function.
