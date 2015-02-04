package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.VelocityTracker;

public class VelocityTrackerCompat
{
    static final VelocityTrackerCompat$VelocityTrackerVersionImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 11)
        {
            IMPL = new VelocityTrackerCompat$BaseVelocityTrackerVersionImpl();
        }
        else
        {
            IMPL = new VelocityTrackerCompat$HoneycombVelocityTrackerVersionImpl();
        }
    }

    public VelocityTrackerCompat()
    {


        this.<init>();
    }

    public static float getXVelocity(VelocityTracker  r0, int  i0)
    {


        return IMPL.getXVelocity(r0, i0);
    }

    public static float getYVelocity(VelocityTracker  r0, int  i0)
    {


        return IMPL.getYVelocity(r0, i0);
    }
}
