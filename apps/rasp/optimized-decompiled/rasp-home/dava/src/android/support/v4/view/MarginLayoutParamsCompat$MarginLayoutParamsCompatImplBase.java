package android.support.v4.view;

import android.view.ViewGroup$MarginLayoutParams;

class MarginLayoutParamsCompat$MarginLayoutParamsCompatImplBase implements android.support.v4.view.MarginLayoutParamsCompat$MarginLayoutParamsCompatImpl
{

    MarginLayoutParamsCompat$MarginLayoutParamsCompatImplBase()
    {


        this.<init>();
    }

    public int getLayoutDirection(ViewGroup$MarginLayoutParams  r1)
    {


        return 0;
    }

    public int getMarginEnd(ViewGroup$MarginLayoutParams  r1)
    {


        return r1.rightMargin;
    }

    public int getMarginStart(ViewGroup$MarginLayoutParams  r1)
    {


        return r1.leftMargin;
    }

    public boolean isMarginRelative(ViewGroup$MarginLayoutParams  r1)
    {


        return false;
    }

    public void resolveLayoutDirection(ViewGroup$MarginLayoutParams  r1, int  i0)
    {

    }

    public void setLayoutDirection(ViewGroup$MarginLayoutParams  r1, int  i0)
    {

    }

    public void setMarginEnd(ViewGroup$MarginLayoutParams  r1, int  i0)
    {


        r1.rightMargin = i0;
    }

    public void setMarginStart(ViewGroup$MarginLayoutParams  r1, int  i0)
    {


        r1.leftMargin = i0;
    }
}
