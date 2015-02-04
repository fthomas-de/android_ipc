package android.support.v4.text;

import android.os.Build$VERSION;

public class ICUCompat
{
    private static final ICUCompat$ICUCompatImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 14)
        {
            IMPL = new ICUCompat$ICUCompatImplBase();
        }
        else
        {
            IMPL = new ICUCompat$ICUCompatImplIcs();
        }
    }

    public ICUCompat()
    {


        this.<init>();
    }

    public static String addLikelySubtags(String  r0)
    {


        return IMPL.addLikelySubtags(r0);
    }

    public static String getScript(String  r0)
    {


        return IMPL.getScript(r0);
    }
}
