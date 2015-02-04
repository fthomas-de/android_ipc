package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.MotionEvent;

public class MotionEventCompat
{
    public static final int ACTION_HOVER_ENTER = 9;
    public static final int ACTION_HOVER_EXIT = 10;
    public static final int ACTION_HOVER_MOVE = 7;
    public static final int ACTION_MASK = 255;
    public static final int ACTION_POINTER_DOWN = 5;
    public static final int ACTION_POINTER_INDEX_MASK = 65280;
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;
    public static final int ACTION_POINTER_UP = 6;
    public static final int ACTION_SCROLL = 8;
    static final MotionEventCompat$MotionEventVersionImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 5)
        {
            IMPL = new MotionEventCompat$BaseMotionEventVersionImpl();
        }
        else
        {
            IMPL = new MotionEventCompat$EclairMotionEventVersionImpl();
        }
    }

    public MotionEventCompat()
    {


        this.<init>();
    }

    public static int findPointerIndex(MotionEvent  r0, int  i0)
    {


        return IMPL.findPointerIndex(r0, i0);
    }

    public static int getActionIndex(MotionEvent  r0)
    {


        return (r0.getAction() & 65280) >> 8;
    }

    public static int getActionMasked(MotionEvent  r0)
    {


        return r0.getAction() + 255;
    }

    public static int getPointerCount(MotionEvent  r0)
    {


        return IMPL.getPointerCount(r0);
    }

    public static int getPointerId(MotionEvent  r0, int  i0)
    {


        return IMPL.getPointerId(r0, i0);
    }

    public static float getX(MotionEvent  r0, int  i0)
    {


        return IMPL.getX(r0, i0);
    }

    public static float getY(MotionEvent  r0, int  i0)
    {


        return IMPL.getY(r0, i0);
    }
}
