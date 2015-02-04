package android.support.v4.view;

import android.view.ScaleGestureDetector;

class ScaleGestureDetectorCompatKitKat
{

    private ScaleGestureDetectorCompatKitKat()
    {


        this.<init>();
    }

    public static boolean isQuickScaleEnabled(Object  r0)
    {


        return ((ScaleGestureDetector) r0).isQuickScaleEnabled();
    }

    public static void setQuickScaleEnabled(Object  r0, boolean  z0)
    {


        ((ScaleGestureDetector) r0).setQuickScaleEnabled(z0);
    }
}
