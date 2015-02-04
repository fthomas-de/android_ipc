package android.support.v4.app;

import android.app.Notification;

class NotificationCompat$NotificationCompatImplIceCreamSandwich implements android.support.v4.app.NotificationCompat$NotificationCompatImpl
{

    NotificationCompat$NotificationCompatImplIceCreamSandwich()
    {


        this.<init>();
    }

    public Notification build(NotificationCompat$Builder  r1)
    {


        return NotificationCompatIceCreamSandwich.add(r1.mContext, r1.mNotification, r1.mContentTitle, r1.mContentText, r1.mContentInfo, r1.mTickerView, r1.mNumber, r1.mContentIntent, r1.mFullScreenIntent, r1.mLargeIcon, r1.mProgressMax, r1.mProgress, r1.mProgressIndeterminate);
    }
}
