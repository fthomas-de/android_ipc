package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.app.PendingIntent;

class TaskStackBuilder$TaskStackBuilderImplJellybean implements android.support.v4.app.TaskStackBuilder$TaskStackBuilderImpl
{

    TaskStackBuilder$TaskStackBuilderImplJellybean()
    {


        this.<init>();
    }

    public PendingIntent getPendingIntent(Context  r1, Intent[]  r2, int  i0, int  i1, Bundle  r3)
    {


        r2[0] = (new Intent(r2[0])).addFlags(268484608);
        return TaskStackBuilderJellybean.getActivitiesPendingIntent(r1, i0, r2, i1, r3);
    }
}
