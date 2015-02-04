package android.support.v4.widget;

import android.os.Build$VERSION;
import android.content.Context;
import android.view.animation.Interpolator;

public class ScrollerCompat
{
    static final ScrollerCompat$ScrollerCompatImpl IMPL;
    Object mScroller;

    static
    {

        int i0;
        i0 = Build$VERSION.SDK_INT;

        if (i0 < 14)
        {
            if (i0 < 9)
            {
                IMPL = new ScrollerCompat$ScrollerCompatImplBase();
            }
            else
            {
                IMPL = new ScrollerCompat$ScrollerCompatImplGingerbread();
            }
        }
        else
        {
            IMPL = new ScrollerCompat$ScrollerCompatImplIcs();
        }
    }

    ScrollerCompat(Context  r1, Interpolator  r2)
    {


        this.<init>();
        mScroller = IMPL.createScroller(r1, r2);
    }

    public void abortAnimation()
    {


        IMPL.abortAnimation(mScroller);
    }

    public boolean computeScrollOffset()
    {


        return IMPL.computeScrollOffset(mScroller);
    }

    public static ScrollerCompat create(Context  r0)
    {


        return ScrollerCompat.create(r0, null);
    }

    public static ScrollerCompat create(Context  r0, Interpolator  r1)
    {


        return new ScrollerCompat(r0, r1);
    }

    public void fling(int  i0, int  i1, int  i2, int  i3, int  i4, int  i5, int  i6, int  i7)
    {


        IMPL.fling(mScroller, i0, i1, i2, i3, i4, i5, i6, i7);
    }

    public void fling(int  i0, int  i1, int  i2, int  i3, int  i4, int  i5, int  i6, int  i7, int  i8, int  i9)
    {


        IMPL.fling(mScroller, i0, i1, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public float getCurrVelocity()
    {


        return IMPL.getCurrVelocity(mScroller);
    }

    public int getCurrX()
    {


        return IMPL.getCurrX(mScroller);
    }

    public int getCurrY()
    {


        return IMPL.getCurrY(mScroller);
    }

    public int getFinalX()
    {


        return IMPL.getFinalX(mScroller);
    }

    public int getFinalY()
    {


        return IMPL.getFinalY(mScroller);
    }

    public boolean isFinished()
    {


        return IMPL.isFinished(mScroller);
    }

    public boolean isOverScrolled()
    {


        return IMPL.isOverScrolled(mScroller);
    }

    public void notifyHorizontalEdgeReached(int  i0, int  i1, int  i2)
    {


        IMPL.notifyHorizontalEdgeReached(mScroller, i0, i1, i2);
    }

    public void notifyVerticalEdgeReached(int  i0, int  i1, int  i2)
    {


        IMPL.notifyVerticalEdgeReached(mScroller, i0, i1, i2);
    }

    public void startScroll(int  i0, int  i1, int  i2, int  i3)
    {


        IMPL.startScroll(mScroller, i0, i1, i2, i3);
    }

    public void startScroll(int  i0, int  i1, int  i2, int  i3, int  i4)
    {


        IMPL.startScroll(mScroller, i0, i1, i2, i3, i4);
    }
}
