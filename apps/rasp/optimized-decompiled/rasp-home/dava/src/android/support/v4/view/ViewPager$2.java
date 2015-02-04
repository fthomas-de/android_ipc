package android.support.v4.view;

import android.view.animation.Interpolator;

final class ViewPager$2 implements android.view.animation.Interpolator
{

    ViewPager$2()
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
