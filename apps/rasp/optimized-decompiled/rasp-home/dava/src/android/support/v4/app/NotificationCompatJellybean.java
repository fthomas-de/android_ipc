package android.support.v4.app;

import android.content.Context;
import android.app.Notification;
import android.widget.RemoteViews;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.app.Notification$Builder;
import android.app.Notification$BigPictureStyle;
import android.app.Notification$BigTextStyle;
import java.util.ArrayList;
import android.app.Notification$InboxStyle;
import java.util.Iterator;

class NotificationCompatJellybean
{
    private Notification$Builder b;

    public NotificationCompatJellybean(Context  r1, Notification  r2, CharSequence  r3, CharSequence  r4, CharSequence  r5, RemoteViews  r6, int  i0, PendingIntent  r7, PendingIntent  r8, Bitmap  r9, int  i1, int  i2, boolean  z0, boolean  z1, int  i3, CharSequence  r10)
    {

        Notification$Builder r23, r24, r25, r34;
        boolean z3, z4, z5, z6;
        this.<init>();
        r23 = (new Notification$Builder(r1)).setWhen(r2.when).setSmallIcon(r2.icon, r2.iconLevel).setContent(r2.contentView).setTicker(r2.tickerText, r6).setSound(r2.sound, r2.audioStreamType).setVibrate(r2.vibrate).setLights(r2.ledARGB, r2.ledOnMS, r2.ledOffMS);

        if ((r2.flags & 2) == 0)
        {
            z3 = false;
        }
        else
        {
            z3 = true;
        }

        r24 = r23.setOngoing(z3);

        if ((r2.flags & 8) == 0)
        {
            z4 = false;
        }
        else
        {
            z4 = true;
        }

        r25 = r24.setOnlyAlertOnce(z4);

        if ((r2.flags & 16) == 0)
        {
            z5 = false;
        }
        else
        {
            z5 = true;
        }

        r34 = r25.setAutoCancel(z5).setDefaults(r2.defaults).setContentTitle(r3).setContentText(r4).setSubText(r10).setContentInfo(r5).setContentIntent(r7).setDeleteIntent(r2.deleteIntent);

        if (r2.flags + 128 == 0)
        {
            z6 = false;
        }
        else
        {
            z6 = true;
        }

        b = r34.setFullScreenIntent(r8, z6).setLargeIcon(r9).setNumber(i0).setUsesChronometer(z1).setPriority(i3).setProgress(i1, i2, z0);
    }

    public void addAction(int  i0, CharSequence  r1, PendingIntent  r2)
    {


        b.addAction(i0, r1, r2);
    }

    public void addBigPictureStyle(CharSequence  r1, boolean  z0, CharSequence  r2, Bitmap  r3, Bitmap  r4, boolean  z1)
    {

        Notification$BigPictureStyle r7;
        r7 = (new Notification$BigPictureStyle(b)).setBigContentTitle(r1).bigPicture(r3);

        if (z1 != false)
        {
            r7.bigLargeIcon(r4);
        }

        if (z0 != false)
        {
            r7.setSummaryText(r2);
        }
    }

    public void addBigTextStyle(CharSequence  r1, boolean  z0, CharSequence  r2, CharSequence  r3)
    {


        if (z0 != false)
        {
            (new Notification$BigTextStyle(b)).setBigContentTitle(r1).bigText(r3).setSummaryText(r2);
        }
    }

    public void addInboxStyle(CharSequence  r1, boolean  z0, CharSequence  r2, ArrayList  r3)
    {

        Notification$InboxStyle r6;
        Iterator r7;
        r6 = (new Notification$InboxStyle(b)).setBigContentTitle(r1);

        if (z0 != false)
        {
            r6.setSummaryText(r2);
        }

        r7 = r3.iterator();

        while (r7.hasNext() != false)
        {
            r6.addLine((CharSequence) r7.next());
        }
    }

    public Notification build()
    {


        return b.build();
    }
}
