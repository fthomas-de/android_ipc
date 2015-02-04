package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.pm.ActivityInfo;

class NavUtils$NavUtilsImplJB extends NavUtils$NavUtilsImplBase
{

    NavUtils$NavUtilsImplJB()
    {


        this.<init>();
    }

    public Intent getParentActivityIntent(Activity  r1)
    {

        Intent r2;
        r2 = NavUtilsJB.getParentActivityIntent(r1);

        if (r2 == null)
        {
            r2 = this.superGetParentActivityIntent(r1);
        }

        return r2;
    }

    public String getParentActivityName(Context  r1, ActivityInfo  r2)
    {

        String r3;
        r3 = NavUtilsJB.getParentActivityName(r2);

        if (r3 == null)
        {
            r3 = this.getParentActivityName(r1, r2);
        }

        return r3;
    }

    public void navigateUpTo(Activity  r1, Intent  r2)
    {


        NavUtilsJB.navigateUpTo(r1, r2);
    }

    public boolean shouldUpRecreateTask(Activity  r1, Intent  r2)
    {


        return NavUtilsJB.shouldUpRecreateTask(r1, r2);
    }

    Intent superGetParentActivityIntent(Activity  r1)
    {


        return this.getParentActivityIntent(r1);
    }
}
