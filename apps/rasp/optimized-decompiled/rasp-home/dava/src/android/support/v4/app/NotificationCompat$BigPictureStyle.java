package android.support.v4.app;

import android.graphics.Bitmap;

public class NotificationCompat$BigPictureStyle extends NotificationCompat$Style
{
    Bitmap mBigLargeIcon;
    boolean mBigLargeIconSet;
    Bitmap mPicture;

    public NotificationCompat$BigPictureStyle()
    {


        this.<init>();
    }

    public NotificationCompat$BigPictureStyle(NotificationCompat$Builder  r1)
    {


        this.<init>();
        this.setBuilder(r1);
    }

    public NotificationCompat$BigPictureStyle bigLargeIcon(Bitmap  r1)
    {


        mBigLargeIcon = r1;
        mBigLargeIconSet = true;
        return this;
    }

    public NotificationCompat$BigPictureStyle bigPicture(Bitmap  r1)
    {


        mPicture = r1;
        return this;
    }

    public NotificationCompat$BigPictureStyle setBigContentTitle(CharSequence  r1)
    {


        mBigContentTitle = r1;
        return this;
    }

    public NotificationCompat$BigPictureStyle setSummaryText(CharSequence  r1)
    {


        mSummaryText = r1;
        mSummaryTextSet = true;
        return this;
    }
}
