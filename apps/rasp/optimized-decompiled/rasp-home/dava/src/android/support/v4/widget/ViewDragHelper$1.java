package android.support.v4.widget;

import android.view.animation.Interpolator;

final class ViewDragHelper$1 implements android.view.animation.Interpolator
{

    ViewDragHelper$1()
    {


        this.<init>();
    }

    public float getInterpolation(float  f0)
    {

        float f3;
        f3 = f0 - 1.0F;
        return f3 * f3 * f3 * f3 * f3 + 1.0F;
    }
}
