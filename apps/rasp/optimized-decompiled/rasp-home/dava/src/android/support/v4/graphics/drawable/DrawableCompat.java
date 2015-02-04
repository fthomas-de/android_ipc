package android.support.v4.graphics.drawable;

import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;

public class DrawableCompat
{
    static final DrawableCompat$DrawableImpl IMPL;

    static
    {

        int i0;
        i0 = Build$VERSION.SDK_INT;

        if (i0 < 19)
        {
            if (i0 < 11)
            {
                IMPL = new DrawableCompat$BaseDrawableImpl();
            }
            else
            {
                IMPL = new DrawableCompat$HoneycombDrawableImpl();
            }
        }
        else
        {
            IMPL = new DrawableCompat$KitKatDrawableImpl();
        }
    }

    public DrawableCompat()
    {


        this.<init>();
    }

    public static boolean isAutoMirrored(Drawable  r0)
    {


        return IMPL.isAutoMirrored(r0);
    }

    public static void jumpToCurrentState(Drawable  r0)
    {


        IMPL.jumpToCurrentState(r0);
    }

    public static void setAutoMirrored(Drawable  r0, boolean  z0)
    {


        IMPL.setAutoMirrored(r0, z0);
    }
}
