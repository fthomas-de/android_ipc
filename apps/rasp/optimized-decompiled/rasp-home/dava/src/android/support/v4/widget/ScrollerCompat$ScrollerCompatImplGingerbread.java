package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;

class ScrollerCompat$ScrollerCompatImplGingerbread implements android.support.v4.widget.ScrollerCompat$ScrollerCompatImpl
{

    ScrollerCompat$ScrollerCompatImplGingerbread()
    {


        this.<init>();
    }

    public void abortAnimation(Object  r1)
    {


        ScrollerCompatGingerbread.abortAnimation(r1);
    }

    public boolean computeScrollOffset(Object  r1)
    {


        return ScrollerCompatGingerbread.computeScrollOffset(r1);
    }

    public Object createScroller(Context  r1, Interpolator  r2)
    {


        return ScrollerCompatGingerbread.createScroller(r1, r2);
    }

    public void fling(Object  r1, int  i0, int  i1, int  i2, int  i3, int  i4, int  i5, int  i6, int  i7)
    {


        ScrollerCompatGingerbread.fling(r1, i0, i1, i2, i3, i4, i5, i6, i7);
    }

    public void fling(Object  r1, int  i0, int  i1, int  i2, int  i3, int  i4, int  i5, int  i6, int  i7, int  i8, int  i9)
    {


        ScrollerCompatGingerbread.fling(r1, i0, i1, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public float getCurrVelocity(Object  r1)
    {


        return 0.0F;
    }

    public int getCurrX(Object  r1)
    {


        return ScrollerCompatGingerbread.getCurrX(r1);
    }

    public int getCurrY(Object  r1)
    {


        return ScrollerCompatGingerbread.getCurrY(r1);
    }

    public int getFinalX(Object  r1)
    {


        return ScrollerCompatGingerbread.getFinalX(r1);
    }

    public int getFinalY(Object  r1)
    {


        return ScrollerCompatGingerbread.getFinalY(r1);
    }

    public boolean isFinished(Object  r1)
    {


        return ScrollerCompatGingerbread.isFinished(r1);
    }

    public boolean isOverScrolled(Object  r1)
    {


        return ScrollerCompatGingerbread.isOverScrolled(r1);
    }

    public void notifyHorizontalEdgeReached(Object  r1, int  i0, int  i1, int  i2)
    {


        ScrollerCompatGingerbread.notifyHorizontalEdgeReached(r1, i0, i1, i2);
    }

    public void notifyVerticalEdgeReached(Object  r1, int  i0, int  i1, int  i2)
    {


        ScrollerCompatGingerbread.notifyVerticalEdgeReached(r1, i0, i1, i2);
    }

    public void startScroll(Object  r1, int  i0, int  i1, int  i2, int  i3)
    {


        ScrollerCompatGingerbread.startScroll(r1, i0, i1, i2, i3);
    }

    public void startScroll(Object  r1, int  i0, int  i1, int  i2, int  i3, int  i4)
    {


        ScrollerCompatGingerbread.startScroll(r1, i0, i1, i2, i3, i4);
    }
}
