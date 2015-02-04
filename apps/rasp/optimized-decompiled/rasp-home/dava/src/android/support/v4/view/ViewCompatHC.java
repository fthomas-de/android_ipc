package android.support.v4.view;

import android.view.View;
import android.animation.ValueAnimator;
import android.graphics.Paint;

class ViewCompatHC
{

    ViewCompatHC()
    {


        this.<init>();
    }

    public static float getAlpha(View  r0)
    {


        return r0.getAlpha();
    }

    static long getFrameTime()
    {


        return ValueAnimator.getFrameDelay();
    }

    public static int getLayerType(View  r0)
    {


        return r0.getLayerType();
    }

    public static int getMeasuredHeightAndState(View  r0)
    {


        return r0.getMeasuredHeightAndState();
    }

    public static int getMeasuredState(View  r0)
    {


        return r0.getMeasuredState();
    }

    public static int getMeasuredWidthAndState(View  r0)
    {


        return r0.getMeasuredWidthAndState();
    }

    public static int resolveSizeAndState(int  i0, int  i1, int  i2)
    {


        return View.resolveSizeAndState(i0, i1, i2);
    }

    public static void setLayerType(View  r0, int  i0, Paint  r1)
    {


        r0.setLayerType(i0, r1);
    }
}
