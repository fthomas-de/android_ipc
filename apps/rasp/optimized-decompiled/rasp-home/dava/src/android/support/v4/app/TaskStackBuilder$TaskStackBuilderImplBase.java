package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.app.PendingIntent;

class TaskStackBuilder$TaskStackBuilderImplBase implements android.support.v4.app.TaskStackBuilder$TaskStackBuilderImpl
{

    TaskStackBuilder$TaskStackBuilderImplBase()
    {


        this.<init>();
    }

    public PendingIntent getPendingIntent(Context  r1, Intent[]  r2, int  i0, int  i1, Bundle  r3)
    {

        Intent r4;
        r4 = new Intent(r2[r2.length + -1]);
        r4.addFlags(268435456);
        return PendingIntent.getActivity(r1, i0, r4, i1);
    }
}
