package android.support.v4.widget;

import android.widget.OverScroller;
import android.content.Context;
import android.view.animation.Interpolator;

class ScrollerCompatGingerbread
{

    ScrollerCompatGingerbread()
    {


        this.<init>();
    }

    public static void abortAnimation(Object  r0)
    {


        ((OverScroller) r0).abortAnimation();
    }

    public static boolean computeScrollOffset(Object  r0)
    {


        return ((OverScroller) r0).computeScrollOffset();
    }

    public static Object createScroller(Context  r0, Interpolator  r1)
    {

        OverScroller r2;
        if (r1 == null)
        {
            r2 = new OverScroller(r0);
        }
        else
        {
            r2 = new OverScroller(r0, r1);
        }

        return r2;
    }

    public static void fling(Object  r0, int  i0, int  i1, int  i2, int  i3, int  i4, int  i5, int  i6, int  i7)
    {


        ((OverScroller) r0).fling(i0, i1, i2, i3, i4, i5, i6, i7);
    }

    public static void fling(Object  r0, int  i0, int  i1, int  i2, int  i3, int  i4, int  i5, int  i6, int  i7, int  i8, int  i9)
    {


        ((OverScroller) r0).fling(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public static int getCurrX(Object  r0)
    {


        return ((OverScroller) r0).getCurrX();
    }

    public static int getCurrY(Object  r0)
    {


        return ((OverScroller) r0).getCurrY();
    }

    public static int getFinalX(Object  r0)
    {


        return ((OverScroller) r0).getFinalX();
    }

    public static int getFinalY(Object  r0)
    {


        return ((OverScroller) r0).getFinalY();
    }

    public static boolean isFinished(Object  r0)
    {


        return ((OverScroller) r0).isFinished();
    }

    public static boolean isOverScrolled(Object  r0)
    {


        return ((OverScroller) r0).isOverScrolled();
    }

    public static void notifyHorizontalEdgeReached(Object  r0, int  i0, int  i1, int  i2)
    {


        ((OverScroller) r0).notifyHorizontalEdgeReached(i0, i1, i2);
    }

    public static void notifyVerticalEdgeReached(Object  r0, int  i0, int  i1, int  i2)
    {


        ((OverScroller) r0).notifyVerticalEdgeReached(i0, i1, i2);
    }

    public static void startScroll(Object  r0, int  i0, int  i1, int  i2, int  i3)
    {


        ((OverScroller) r0).startScroll(i0, i1, i2, i3);
    }

    public static void startScroll(Object  r0, int  i0, int  i1, int  i2, int  i3, int  i4)
    {


        ((OverScroller) r0).startScroll(i0, i1, i2, i3, i4);
    }
}
