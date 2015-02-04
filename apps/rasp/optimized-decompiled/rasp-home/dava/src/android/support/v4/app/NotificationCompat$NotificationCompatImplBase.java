package android.support.v4.app;

import android.app.Notification;

class NotificationCompat$NotificationCompatImplBase implements android.support.v4.app.NotificationCompat$NotificationCompatImpl
{

    NotificationCompat$NotificationCompatImplBase()
    {


        this.<init>();
    }

    public Notification build(NotificationCompat$Builder  r1)
    {

        Notification r2;
        r2 = r1.mNotification;
        r2.setLatestEventInfo(r1.mContext, r1.mContentTitle, r1.mContentText, r1.mContentIntent);

        if (r1.mPriority > 0)
        {
            r2.flags = r2.flags | 128;
        }

        return r2;
    }
}
