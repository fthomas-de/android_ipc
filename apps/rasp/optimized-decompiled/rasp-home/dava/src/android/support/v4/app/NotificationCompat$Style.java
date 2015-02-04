package android.support.v4.app;

import android.app.Notification;

public abstract class NotificationCompat$Style
{
    CharSequence mBigContentTitle;
    NotificationCompat$Builder mBuilder;
    CharSequence mSummaryText;
    boolean mSummaryTextSet;

    public NotificationCompat$Style()
    {


        this.<init>();
        mSummaryTextSet = false;
    }

    public Notification build()
    {

        Notification r1;
        r1 = null;

        if (mBuilder != null)
        {
            r1 = mBuilder.build();
        }

        return r1;
    }

    public void setBuilder(NotificationCompat$Builder  r1)
    {


        if (mBuilder != r1)
        {
            mBuilder = r1;

            if (mBuilder != null)
            {
                mBuilder.setStyle(this);
            }
        }
    }
}
