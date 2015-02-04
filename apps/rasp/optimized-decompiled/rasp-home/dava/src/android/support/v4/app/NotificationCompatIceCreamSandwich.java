package android.support.v4.app;

import android.content.Context;
import android.app.Notification;
import android.widget.RemoteViews;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.app.Notification$Builder;

class NotificationCompatIceCreamSandwich
{

    NotificationCompatIceCreamSandwich()
    {


        this.<init>();
    }

    static Notification add(Context  r0, Notification  r1, CharSequence  r2, CharSequence  r3, CharSequence  r4, RemoteViews  r5, int  i0, PendingIntent  r6, PendingIntent  r7, Bitmap  r8, int  i1, int  i2, boolean  z0)
    {

        Notification$Builder r22, r23, r24, r32;
        boolean z2, z3, z4, z5;
        r22 = (new Notification$Builder(r0)).setWhen(r1.when).setSmallIcon(r1.icon, r1.iconLevel).setContent(r1.contentView).setTicker(r1.tickerText, r5).setSound(r1.sound, r1.audioStreamType).setVibrate(r1.vibrate).setLights(r1.ledARGB, r1.ledOnMS, r1.ledOffMS);

        if ((r1.flags & 2) == 0)
        {
            z2 = false;
        }
        else
        {
            z2 = true;
        }

        r23 = r22.setOngoing(z2);

        if ((r1.flags & 8) == 0)
        {
            z3 = false;
        }
        else
        {
            z3 = true;
        }

        r24 = r23.setOnlyAlertOnce(z3);

        if ((r1.flags & 16) == 0)
        {
            z4 = false;
        }
        else
        {
            z4 = true;
        }

        r32 = r24.setAutoCancel(z4).setDefaults(r1.defaults).setContentTitle(r2).setContentText(r3).setContentInfo(r4).setContentIntent(r6).setDeleteIntent(r1.deleteIntent);

        if (r1.flags + 128 == 0)
        {
            z5 = false;
        }
        else
        {
            z5 = true;
        }

        return r32.setFullScreenIntent(r7, z5).setLargeIcon(r8).setNumber(i0).setProgress(i1, i2, z0).getNotification();
    }
}
