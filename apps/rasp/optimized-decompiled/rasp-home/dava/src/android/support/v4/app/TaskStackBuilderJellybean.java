package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.app.PendingIntent;

class TaskStackBuilderJellybean
{

    TaskStackBuilderJellybean()
    {


        this.<init>();
    }

    public static PendingIntent getActivitiesPendingIntent(Context  r0, int  i0, Intent[]  r1, int  i1, Bundle  r2)
    {


        return PendingIntent.getActivities(r0, i0, r1, i1, r2);
    }
}
