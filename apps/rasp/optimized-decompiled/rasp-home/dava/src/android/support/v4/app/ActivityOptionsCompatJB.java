package android.support.v4.app;

import android.app.ActivityOptions;
import android.content.Context;
import android.view.View;
import android.graphics.Bitmap;
import android.os.Bundle;

class ActivityOptionsCompatJB
{
    private final ActivityOptions mActivityOptions;

    private ActivityOptionsCompatJB(ActivityOptions  r1)
    {


        this.<init>();
        mActivityOptions = r1;
    }

    public static ActivityOptionsCompatJB makeCustomAnimation(Context  r0, int  i0, int  i1)
    {


        return new ActivityOptionsCompatJB(ActivityOptions.makeCustomAnimation(r0, i0, i1));
    }

    public static ActivityOptionsCompatJB makeScaleUpAnimation(View  r0, int  i0, int  i1, int  i2, int  i3)
    {


        return new ActivityOptionsCompatJB(ActivityOptions.makeScaleUpAnimation(r0, i0, i1, i2, i3));
    }

    public static ActivityOptionsCompatJB makeThumbnailScaleUpAnimation(View  r0, Bitmap  r1, int  i0, int  i1)
    {


        return new ActivityOptionsCompatJB(ActivityOptions.makeThumbnailScaleUpAnimation(r0, r1, i0, i1));
    }

    public Bundle toBundle()
    {


        return mActivityOptions.toBundle();
    }

    public void update(ActivityOptionsCompatJB  r1)
    {


        mActivityOptions.update(r1.mActivityOptions);
    }
}
