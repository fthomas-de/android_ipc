package android.support.v4.app;

import android.os.Bundle;

class ActivityOptionsCompat$ActivityOptionsImplJB extends ActivityOptionsCompat
{
    private final ActivityOptionsCompatJB mImpl;

    ActivityOptionsCompat$ActivityOptionsImplJB(ActivityOptionsCompatJB  r1)
    {


        this.<init>();
        mImpl = r1;
    }

    public Bundle toBundle()
    {


        return mImpl.toBundle();
    }

    public void update(ActivityOptionsCompat  r1)
    {


        if (r1 instanceof ActivityOptionsCompat$ActivityOptionsImplJB != false)
        {
            mImpl.update(((ActivityOptionsCompat$ActivityOptionsImplJB) r1).mImpl);
        }
    }
}
