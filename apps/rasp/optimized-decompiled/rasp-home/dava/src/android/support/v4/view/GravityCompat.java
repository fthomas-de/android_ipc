package android.support.v4.view;

import android.os.Build$VERSION;
import android.graphics.Rect;

public class GravityCompat
{
    public static final int END = 8388613;
    static final GravityCompat$GravityCompatImpl IMPL;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 8388615;
    public static final int RELATIVE_LAYOUT_DIRECTION = 8388608;
    public static final int START = 8388611;

    static
    {


        if (Build$VERSION.SDK_INT < 17)
        {
            IMPL = new GravityCompat$GravityCompatImplBase();
        }
        else
        {
            IMPL = new GravityCompat$GravityCompatImplJellybeanMr1();
        }
    }

    public GravityCompat()
    {


        this.<init>();
    }

    public static void apply(int  i0, int  i1, int  i2, Rect  r0, int  i3, int  i4, Rect  r1, int  i5)
    {


        IMPL.apply(i0, i1, i2, r0, i3, i4, r1, i5);
    }

    public static void apply(int  i0, int  i1, int  i2, Rect  r0, Rect  r1, int  i3)
    {


        IMPL.apply(i0, i1, i2, r0, r1, i3);
    }

    public static void applyDisplay(int  i0, Rect  r0, Rect  r1, int  i1)
    {


        IMPL.applyDisplay(i0, r0, r1, i1);
    }

    public static int getAbsoluteGravity(int  i0, int  i1)
    {


        return IMPL.getAbsoluteGravity(i0, i1);
    }
}
