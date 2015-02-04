package android.support.v4.app;

import android.content.Context;
import java.util.ArrayList;
import android.app.Notification;
import android.app.PendingIntent;
import android.widget.RemoteViews;
import android.graphics.Bitmap;
import android.net.Uri;

public class NotificationCompat$Builder
{
    ArrayList mActions;
    CharSequence mContentInfo;
    PendingIntent mContentIntent;
    CharSequence mContentText;
    CharSequence mContentTitle;
    Context mContext;
    PendingIntent mFullScreenIntent;
    Bitmap mLargeIcon;
    Notification mNotification;
    int mNumber;
    int mPriority;
    int mProgress;
    boolean mProgressIndeterminate;
    int mProgressMax;
    NotificationCompat$Style mStyle;
    CharSequence mSubText;
    RemoteViews mTickerView;
    boolean mUseChronometer;

    public NotificationCompat$Builder(Context  r1)
    {


        this.<init>();
        mActions = new ArrayList();
        mNotification = new Notification();
        mContext = r1;
        mNotification.when = System.currentTimeMillis();
        mNotification.audioStreamType = -1;
        mPriority = 0;
    }

    public NotificationCompat$Builder addAction(int  i0, CharSequence  r1, PendingIntent  r2)
    {


        mActions.add(new NotificationCompat$Action(i0, r1, r2));
        return this;
    }

    public Notification build()
    {


        return NotificationCompat.access$000().build(this);
    }

    public Notification getNotification()
    {


        return NotificationCompat.access$000().build(this);
    }

    public NotificationCompat$Builder setAutoCancel(boolean  z0)
    {


        this.setFlag(16, z0);
        return this;
    }

    public NotificationCompat$Builder setContent(RemoteViews  r1)
    {


        mNotification.contentView = r1;
        return this;
    }

    public NotificationCompat$Builder setContentInfo(CharSequence  r1)
    {


        mContentInfo = r1;
        return this;
    }

    public NotificationCompat$Builder setContentIntent(PendingIntent  r1)
    {


        mContentIntent = r1;
        return this;
    }

    public NotificationCompat$Builder setContentText(CharSequence  r1)
    {


        mContentText = r1;
        return this;
    }

    public NotificationCompat$Builder setContentTitle(CharSequence  r1)
    {


        mContentTitle = r1;
        return this;
    }

    public NotificationCompat$Builder setDefaults(int  i0)
    {

        Notification r2;
        mNotification.defaults = i0;

        if ((i0 & 4) != 0)
        {
            r2 = mNotification;
            r2.flags = r2.flags | 1;
        }

        return this;
    }

    public NotificationCompat$Builder setDeleteIntent(PendingIntent  r1)
    {


        mNotification.deleteIntent = r1;
        return this;
    }

    private void setFlag(int  i0, boolean  z0)
    {

        Notification r1, r2;
        if (z0 == false)
        {
            r2 = mNotification;
            r2.flags = r2.flags & (i0 ^ -1);
        }
        else
        {
            r1 = mNotification;
            r1.flags = r1.flags | i0;
        }
    }

    public NotificationCompat$Builder setFullScreenIntent(PendingIntent  r1, boolean  z0)
    {


        mFullScreenIntent = r1;
        this.setFlag(128, z0);
        return this;
    }

    public NotificationCompat$Builder setLargeIcon(Bitmap  r1)
    {


        mLargeIcon = r1;
        return this;
    }

    public NotificationCompat$Builder setLights(int  i0, int  i1, int  i2)
    {

        byte b3, b5;
        Notification r7;
        int i9;
        b3 = (byte) (byte) 1;
        mNotification.ledARGB = i0;
        mNotification.ledOnMS = i1;
        mNotification.ledOffMS = i2;

        label_0:
        {
            if (mNotification.ledOnMS != 0)
            {
                if (mNotification.ledOffMS != 0)
                {
                    b5 = (byte) (byte) 1;
                    break label_0;
                }
            }

            b5 = (byte) (byte) 0;
        } //end label_0:


        r7 = mNotification;
        i9 = mNotification.flags & -2;

        if (b5 == (byte) 0)
        {
            b3 = (byte) (byte) 0;
        }

        r7.flags = b3 | i9;
        return this;
    }

    public NotificationCompat$Builder setNumber(int  i0)
    {


        mNumber = i0;
        return this;
    }

    public NotificationCompat$Builder setOngoing(boolean  z0)
    {


        this.setFlag(2, z0);
        return this;
    }

    public NotificationCompat$Builder setOnlyAlertOnce(boolean  z0)
    {


        this.setFlag(8, z0);
        return this;
    }

    public NotificationCompat$Builder setPriority(int  i0)
    {


        mPriority = i0;
        return this;
    }

    public NotificationCompat$Builder setProgress(int  i0, int  i1, boolean  z0)
    {


        mProgressMax = i0;
        mProgress = i1;
        mProgressIndeterminate = z0;
        return this;
    }

    public NotificationCompat$Builder setSmallIcon(int  i0)
    {


        mNotification.icon = i0;
        return this;
    }

    public NotificationCompat$Builder setSmallIcon(int  i0, int  i1)
    {


        mNotification.icon = i0;
        mNotification.iconLevel = i1;
        return this;
    }

    public NotificationCompat$Builder setSound(Uri  r1)
    {


        mNotification.sound = r1;
        mNotification.audioStreamType = -1;
        return this;
    }

    public NotificationCompat$Builder setSound(Uri  r1, int  i0)
    {


        mNotification.sound = r1;
        mNotification.audioStreamType = i0;
        return this;
    }

    public NotificationCompat$Builder setStyle(NotificationCompat$Style  r1)
    {


        if (mStyle != r1)
        {
            mStyle = r1;

            if (mStyle != null)
            {
                mStyle.setBuilder(this);
            }
        }

        return this;
    }

    public NotificationCompat$Builder setSubText(CharSequence  r1)
    {


        mSubText = r1;
        return this;
    }

    public NotificationCompat$Builder setTicker(CharSequence  r1)
    {


        mNotification.tickerText = r1;
        return this;
    }

    public NotificationCompat$Builder setTicker(CharSequence  r1, RemoteViews  r2)
    {


        mNotification.tickerText = r1;
        mTickerView = r2;
        return this;
    }

    public NotificationCompat$Builder setUsesChronometer(boolean  z0)
    {


        mUseChronometer = z0;
        return this;
    }

    public NotificationCompat$Builder setVibrate(long[]  r1)
    {


        mNotification.vibrate = r1;
        return this;
    }

    public NotificationCompat$Builder setWhen(long  l0)
    {


        mNotification.when = l0;
        return this;
    }
}
