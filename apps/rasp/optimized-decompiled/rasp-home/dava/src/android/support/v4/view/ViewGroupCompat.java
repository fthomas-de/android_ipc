package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.ViewGroup;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class ViewGroupCompat
{
    static final ViewGroupCompat$ViewGroupCompatImpl IMPL;
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;

    static
    {

        int i0;
        i0 = Build$VERSION.SDK_INT;

        if (i0 < 18)
        {
            if (i0 < 14)
            {
                if (i0 < 11)
                {
                    IMPL = new ViewGroupCompat$ViewGroupCompatStubImpl();
                }
                else
                {
                    IMPL = new ViewGroupCompat$ViewGroupCompatHCImpl();
                }
            }
            else
            {
                IMPL = new ViewGroupCompat$ViewGroupCompatIcsImpl();
            }
        }
        else
        {
            IMPL = new ViewGroupCompat$ViewGroupCompatJellybeanMR2Impl();
        }
    }

    private ViewGroupCompat()
    {


        this.<init>();
    }

    public static int getLayoutMode(ViewGroup  r0)
    {


        return IMPL.getLayoutMode(r0);
    }

    public static boolean onRequestSendAccessibilityEvent(ViewGroup  r0, View  r1, AccessibilityEvent  r2)
    {


        return IMPL.onRequestSendAccessibilityEvent(r0, r1, r2);
    }

    public static void setLayoutMode(ViewGroup  r0, int  i0)
    {


        IMPL.setLayoutMode(r0, i0);
    }

    public static void setMotionEventSplittingEnabled(ViewGroup  r0, boolean  z0)
    {


        IMPL.setMotionEventSplittingEnabled(r0, z0);
    }
}
