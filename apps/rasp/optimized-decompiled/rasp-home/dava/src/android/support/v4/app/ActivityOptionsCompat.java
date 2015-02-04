package android.support.v4.app;

import android.content.Context;
import android.os.Build$VERSION;
import android.view.View;
import android.graphics.Bitmap;
import android.os.Bundle;

public class ActivityOptionsCompat
{

    protected ActivityOptionsCompat()
    {


        this.<init>();
    }

    public static ActivityOptionsCompat makeCustomAnimation(Context  r0, int  i0, int  i1)
    {

        ActivityOptionsCompat r1, r4;
        ActivityOptionsCompat$ActivityOptionsImplJB r3;
        if (Build$VERSION.SDK_INT < 16)
        {
            r1 = r4;
            r4 = new ActivityOptionsCompat();
        }
        else
        {
            r1 = r3;
            r3 = new ActivityOptionsCompat$ActivityOptionsImplJB(ActivityOptionsCompatJB.makeCustomAnimation(r0, i0, i1));
        }

        return r1;
    }

    public static ActivityOptionsCompat makeScaleUpAnimation(View  r0, int  i0, int  i1, int  i2, int  i3)
    {

        ActivityOptionsCompat r1, r4;
        ActivityOptionsCompat$ActivityOptionsImplJB r3;
        if (Build$VERSION.SDK_INT < 16)
        {
            r1 = r4;
            r4 = new ActivityOptionsCompat();
        }
        else
        {
            r1 = r3;
            r3 = new ActivityOptionsCompat$ActivityOptionsImplJB(ActivityOptionsCompatJB.makeScaleUpAnimation(r0, i0, i1, i2, i3));
        }

        return r1;
    }

    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View  r0, Bitmap  r1, int  i0, int  i1)
    {

        ActivityOptionsCompat r2, r5;
        ActivityOptionsCompat$ActivityOptionsImplJB r4;
        if (Build$VERSION.SDK_INT < 16)
        {
            r2 = r5;
            r5 = new ActivityOptionsCompat();
        }
        else
        {
            r2 = r4;
            r4 = new ActivityOptionsCompat$ActivityOptionsImplJB(ActivityOptionsCompatJB.makeThumbnailScaleUpAnimation(r0, r1, i0, i1));
        }

        return r2;
    }

    public Bundle toBundle()
    {


        return null;
    }

    public void update(ActivityOptionsCompat  r1)
    {

    }
}
