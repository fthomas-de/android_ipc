package android.support.v4.view;

import android.os.Build$VERSION;

public class ScaleGestureDetectorCompat
{
    static final ScaleGestureDetectorCompat$ScaleGestureDetectorImpl IMPL;

    static
    {

        Object n0;
        n0 = null;

        if (Build$VERSION.SDK_INT < 19)
        {
            IMPL = new ScaleGestureDetectorCompat$BaseScaleGestureDetectorImpl(n0);
        }
        else
        {
            IMPL = new ScaleGestureDetectorCompat$ScaleGestureDetectorCompatKitKatImpl(n0);
        }
    }

    private ScaleGestureDetectorCompat()
    {


        this.<init>();
    }

    public static boolean isQuickScaleEnabled(Object  r0)
    {


        return IMPL.isQuickScaleEnabled(r0);
    }

    public static void setQuickScaleEnabled(Object  r0, boolean  z0)
    {


        IMPL.setQuickScaleEnabled(r0, z0);
    }
}
