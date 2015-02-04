package android.support.v4.widget;

import android.widget.Scroller;
import android.content.Context;
import android.view.animation.Interpolator;

class ScrollerCompat$ScrollerCompatImplBase implements android.support.v4.widget.ScrollerCompat$ScrollerCompatImpl
{

    ScrollerCompat$ScrollerCompatImplBase()
    {


        this.<init>();
    }

    public void abortAnimation(Object  r1)
    {


        ((Scroller) r1).abortAnimation();
    }

    public boolean computeScrollOffset(Object  r1)
    {


        return ((Scroller) r1).computeScrollOffset();
    }

    public Object createScroller(Context  r1, Interpolator  r2)
    {

        Scroller r3;
        if (r2 == null)
        {
            r3 = new Scroller(r1);
        }
        else
        {
            r3 = new Scroller(r1, r2);
        }

        return r3;
    }

    public void fling(Object  r1, int  i0, int  i1, int  i2, int  i3, int  i4, int  i5, int  i6, int  i7)
    {


        ((Scroller) r1).fling(i0, i1, i2, i3, i4, i5, i6, i7);
    }

    public void fling(Object  r1, int  i0, int  i1, int  i2, int  i3, int  i4, int  i5, int  i6, int  i7, int  i8, int  i9)
    {


        ((Scroller) r1).fling(i0, i1, i2, i3, i4, i5, i6, i7);
    }

    public float getCurrVelocity(Object  r1)
    {


        return 0.0F;
    }

    public int getCurrX(Object  r1)
    {


        return ((Scroller) r1).getCurrX();
    }

    public int getCurrY(Object  r1)
    {


        return ((Scroller) r1).getCurrY();
    }

    public int getFinalX(Object  r1)
    {


        return ((Scroller) r1).getFinalX();
    }

    public int getFinalY(Object  r1)
    {


        return ((Scroller) r1).getFinalY();
    }

    public boolean isFinished(Object  r1)
    {


        return ((Scroller) r1).isFinished();
    }

    public boolean isOverScrolled(Object  r1)
    {


        return false;
    }

    public void notifyHorizontalEdgeReached(Object  r1, int  i0, int  i1, int  i2)
    {

    }

    public void notifyVerticalEdgeReached(Object  r1, int  i0, int  i1, int  i2)
    {

    }

    public void startScroll(Object  r1, int  i0, int  i1, int  i2, int  i3)
    {


        ((Scroller) r1).startScroll(i0, i1, i2, i3);
    }

    public void startScroll(Object  r1, int  i0, int  i1, int  i2, int  i3, int  i4)
    {


        ((Scroller) r1).startScroll(i0, i1, i2, i3, i4);
    }
}
