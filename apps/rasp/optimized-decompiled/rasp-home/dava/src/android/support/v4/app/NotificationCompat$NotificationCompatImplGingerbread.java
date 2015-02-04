package android.support.v4.app;

import android.app.Notification;

class NotificationCompat$NotificationCompatImplGingerbread extends NotificationCompat$NotificationCompatImplBase
{

    NotificationCompat$NotificationCompatImplGingerbread()
    {


        this.<init>();
    }

    public Notification build(NotificationCompat$Builder  r1)
    {

        Notification r2, r12;
        r2 = r1.mNotification;
        r2.setLatestEventInfo(r1.mContext, r1.mContentTitle, r1.mContentText, r1.mContentIntent);
        r12 = NotificationCompatGingerbread.add(r2, r1.mContext, r1.mContentTitle, r1.mContentText, r1.mContentIntent, r1.mFullScreenIntent);

        if (r1.mPriority > 0)
        {
            r12.flags = r12.flags | 128;
        }

        return r12;
    }
}
