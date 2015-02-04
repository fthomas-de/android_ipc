package android.support.v4.view;

import android.graphics.Rect;
import android.view.Gravity;

class GravityCompat$GravityCompatImplBase implements android.support.v4.view.GravityCompat$GravityCompatImpl
{

    GravityCompat$GravityCompatImplBase()
    {


        this.<init>();
    }

    public void apply(int  i0, int  i1, int  i2, Rect  r1, int  i3, int  i4, Rect  r2, int  i5)
    {


        Gravity.apply(i0, i1, i2, r1, i3, i4, r2);
    }

    public void apply(int  i0, int  i1, int  i2, Rect  r1, Rect  r2, int  i3)
    {


        Gravity.apply(i0, i1, i2, r1, r2);
    }

    public void applyDisplay(int  i0, Rect  r1, Rect  r2, int  i1)
    {


        Gravity.applyDisplay(i0, r1, r2);
    }

    public int getAbsoluteGravity(int  i0, int  i1)
    {


        return -8388609 & i0;
    }
}
