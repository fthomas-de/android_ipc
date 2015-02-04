package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.ViewGroup$MarginLayoutParams;

public class MarginLayoutParamsCompat
{
    static final MarginLayoutParamsCompat$MarginLayoutParamsCompatImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 17)
        {
            IMPL = new MarginLayoutParamsCompat$MarginLayoutParamsCompatImplBase();
        }
        else
        {
            IMPL = new MarginLayoutParamsCompat$MarginLayoutParamsCompatImplJbMr1();
        }
    }

    public MarginLayoutParamsCompat()
    {


        this.<init>();
    }

    public static int getLayoutDirection(ViewGroup$MarginLayoutParams  r0)
    {


        return IMPL.getLayoutDirection(r0);
    }

    public static int getMarginEnd(ViewGroup$MarginLayoutParams  r0)
    {


        return IMPL.getMarginEnd(r0);
    }

    public static int getMarginStart(ViewGroup$MarginLayoutParams  r0)
    {


        return IMPL.getMarginStart(r0);
    }

    public static boolean isMarginRelative(ViewGroup$MarginLayoutParams  r0)
    {


        return IMPL.isMarginRelative(r0);
    }

    public static void resolveLayoutDirection(ViewGroup$MarginLayoutParams  r0, int  i0)
    {


        IMPL.resolveLayoutDirection(r0, i0);
    }

    public static void setLayoutDirection(ViewGroup$MarginLayoutParams  r0, int  i0)
    {


        IMPL.setLayoutDirection(r0, i0);
    }

    public static void setMarginEnd(ViewGroup$MarginLayoutParams  r0, int  i0)
    {


        IMPL.setMarginEnd(r0, i0);
    }

    public static void setMarginStart(ViewGroup$MarginLayoutParams  r0, int  i0)
    {


        IMPL.setMarginStart(r0, i0);
    }
}
