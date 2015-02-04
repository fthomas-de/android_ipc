package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.pm.ActivityInfo;

abstract interface NavUtils$NavUtilsImpl
{

    public abstract android.content.Intent getParentActivityIntent(android.app.Activity  r0);

    public abstract java.lang.String getParentActivityName(android.content.Context  r0, android.content.pm.ActivityInfo  r1);

    public abstract void navigateUpTo(android.app.Activity  r0, android.content.Intent  r1);

    public abstract boolean shouldUpRecreateTask(android.app.Activity  r0, android.content.Intent  r1);
}
