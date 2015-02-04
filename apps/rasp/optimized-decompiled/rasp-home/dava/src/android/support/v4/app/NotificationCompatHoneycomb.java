package android.support.v4.app;

import android.content.Context;
import android.app.Notification;
import android.widget.RemoteViews;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.app.Notification$Builder;

class NotificationCompatHoneycomb
{

    NotificationCompatHoneycomb()
    {


        this.<init>();
    }

    static Notification add(Context  r0, Notification  r1, CharSequence  r2, CharSequence  r3, CharSequence  r4, RemoteViews  r5, int  i0, PendingIntent  r6, PendingIntent  r7, Bitmap  r8)
    {

        Notification$Builder r21, r22, r23, r31;
        boolean z0, z1, z2, z3;
        r21 = (new Notification$Builder(r0)).setWhen(r1.when).setSmallIcon(r1.icon, r1.iconLevel).setContent(r1.contentView).setTicker(r1.tickerText, r5).setSound(r1.sound, r1.audioStreamType).setVibrate(r1.vibrate).setLights(r1.ledARGB, r1.ledOnMS, r1.ledOffMS);

        if ((r1.flags & 2) == 0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        r22 = r21.setOngoing(z0);

        if ((r1.flags & 8) == 0)
        {
            z1 = false;
        }
        else
        {
            z1 = true;
        }

        r23 = r22.setOnlyAlertOnce(z1);

        if ((r1.flags & 16) == 0)
        {
            z2 = false;
        }
        else
        {
            z2 = true;
        }

        r31 = r23.setAutoCancel(z2).setDefaults(r1.defaults).setContentTitle(r2).setContentText(r3).setContentInfo(r4).setContentIntent(r6).setDeleteIntent(r1.deleteIntent);

        if (r1.flags + 128 == 0)
        {
            z3 = false;
        }
        else
        {
            z3 = true;
        }

        return r31.setFullScreenIntent(r7, z3).setLargeIcon(r8).setNumber(i0).getNotification();
    }
}
