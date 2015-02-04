package android.support.v4.widget;

import android.view.View;

public abstract class ViewDragHelper$Callback
{

    public ViewDragHelper$Callback()
    {


        this.<init>();
    }

    public int clampViewPositionHorizontal(View  r1, int  i0, int  i1)
    {


        return 0;
    }

    public int clampViewPositionVertical(View  r1, int  i0, int  i1)
    {


        return 0;
    }

    public int getOrderedChildIndex(int  i0)
    {


        return i0;
    }

    public int getViewHorizontalDragRange(View  r1)
    {


        return 0;
    }

    public int getViewVerticalDragRange(View  r1)
    {


        return 0;
    }

    public void onEdgeDragStarted(int  i0, int  i1)
    {

    }

    public boolean onEdgeLock(int  i0)
    {


        return false;
    }

    public void onEdgeTouched(int  i0, int  i1)
    {

    }

    public void onViewCaptured(View  r1, int  i0)
    {

    }

    public void onViewDragStateChanged(int  i0)
    {

    }

    public void onViewPositionChanged(View  r1, int  i0, int  i1, int  i2, int  i3)
    {

    }

    public void onViewReleased(View  r1, float  f0, float  f1)
    {

    }

    public abstract boolean tryCaptureView(android.view.View  r0, int  i1);
}
