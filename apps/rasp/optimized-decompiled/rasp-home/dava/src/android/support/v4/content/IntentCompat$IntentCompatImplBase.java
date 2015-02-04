package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;

class IntentCompat$IntentCompatImplBase implements android.support.v4.content.IntentCompat$IntentCompatImpl
{

    IntentCompat$IntentCompatImplBase()
    {


        this.<init>();
    }

    public Intent makeMainActivity(ComponentName  r1)
    {

        Intent r2;
        r2 = new Intent("android.intent.action.MAIN");
        r2.setComponent(r1);
        r2.addCategory("android.intent.category.LAUNCHER");
        return r2;
    }

    public Intent makeMainSelectorActivity(String  r1, String  r2)
    {

        Intent r3;
        r3 = new Intent(r1);
        r3.addCategory(r2);
        return r3;
    }

    public Intent makeRestartActivityTask(ComponentName  r1)
    {

        Intent r2;
        r2 = this.makeMainActivity(r1);
        r2.addFlags(268468224);
        return r2;
    }
}
