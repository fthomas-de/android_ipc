package android.support.v4.app;

import android.os.Build$VERSION;

public class NotificationCompat
{
    public static final int FLAG_HIGH_PRIORITY = 128;
    private static final NotificationCompat$NotificationCompatImpl IMPL;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;

    static
    {


        if (Build$VERSION.SDK_INT < 16)
        {
            if (Build$VERSION.SDK_INT < 14)
            {
                if (Build$VERSION.SDK_INT < 11)
                {
                    if (Build$VERSION.SDK_INT < 9)
                    {
                        IMPL = new NotificationCompat$NotificationCompatImplBase();
                    }
                    else
                    {
                        IMPL = new NotificationCompat$NotificationCompatImplGingerbread();
                    }
                }
                else
                {
                    IMPL = new NotificationCompat$NotificationCompatImplHoneycomb();
                }
            }
            else
            {
                IMPL = new NotificationCompat$NotificationCompatImplIceCreamSandwich();
            }
        }
        else
        {
            IMPL = new NotificationCompat$NotificationCompatImplJellybean();
        }
    }

    public NotificationCompat()
    {


        this.<init>();
    }

    static NotificationCompat$NotificationCompatImpl access$000()
    {


        return IMPL;
    }
}
