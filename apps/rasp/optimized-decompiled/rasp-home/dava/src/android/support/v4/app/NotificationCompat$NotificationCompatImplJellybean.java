package android.support.v4.app;

import java.util.Iterator;
import java.util.ArrayList;
import android.app.Notification;

class NotificationCompat$NotificationCompatImplJellybean implements android.support.v4.app.NotificationCompat$NotificationCompatImpl
{

    NotificationCompat$NotificationCompatImplJellybean()
    {


        this.<init>();
    }

    public Notification build(NotificationCompat$Builder  r1)
    {

        NotificationCompatJellybean r2;
        Iterator r14;
        NotificationCompat$Action r35;
        NotificationCompat$BigTextStyle r47;
        NotificationCompat$InboxStyle r61;
        NotificationCompat$BigPictureStyle r74;
        r2 = new NotificationCompatJellybean(r1.mContext, r1.mNotification, r1.mContentTitle, r1.mContentText, r1.mContentInfo, r1.mTickerView, r1.mNumber, r1.mContentIntent, r1.mFullScreenIntent, r1.mLargeIcon, r1.mProgressMax, r1.mProgress, r1.mProgressIndeterminate, r1.mUseChronometer, r1.mPriority, r1.mSubText);
        r14 = r1.mActions.iterator();

        while (r14.hasNext() != false)
        {
            r35 = (NotificationCompat$Action) r14.next();
            r2.addAction(r35.icon, r35.title, r35.actionIntent);
        }

        if (r1.mStyle != null)
        {
            if (r1.mStyle instanceof NotificationCompat$BigTextStyle == false)
            {
                if (r1.mStyle instanceof NotificationCompat$InboxStyle == false)
                {
                    if (r1.mStyle instanceof NotificationCompat$BigPictureStyle != false)
                    {
                        r74 = (NotificationCompat$BigPictureStyle) r1.mStyle;
                        r2.addBigPictureStyle(r74.mBigContentTitle, r74.mSummaryTextSet, r74.mSummaryText, r74.mPicture, r74.mBigLargeIcon, r74.mBigLargeIconSet);
                    }
                }
                else
                {
                    r61 = (NotificationCompat$InboxStyle) r1.mStyle;
                    r2.addInboxStyle(r61.mBigContentTitle, r61.mSummaryTextSet, r61.mSummaryText, r61.mTexts);
                }
            }
            else
            {
                r47 = (NotificationCompat$BigTextStyle) r1.mStyle;
                r2.addBigTextStyle(r47.mBigContentTitle, r47.mSummaryTextSet, r47.mSummaryText, r47.mBigText);
            }
        }

        return r2.build();
    }
}
