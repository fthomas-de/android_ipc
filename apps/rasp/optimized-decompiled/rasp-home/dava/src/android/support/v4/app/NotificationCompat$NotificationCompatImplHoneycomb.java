package android.support.v4.app;

import android.app.Notification;

class NotificationCompat$NotificationCompatImplHoneycomb implements android.support.v4.app.NotificationCompat$NotificationCompatImpl
{

    NotificationCompat$NotificationCompatImplHoneycomb()
    {


        this.<init>();
    }

    public Notification build(NotificationCompat$Builder  r1)
    {


        return NotificationCompatHoneycomb.add(r1.mContext, r1.mNotification, r1.mContentTitle, r1.mContentText, r1.mContentInfo, r1.mTickerView, r1.mNumber, r1.mContentIntent, r1.mFullScreenIntent, r1.mLargeIcon);
    }
}
