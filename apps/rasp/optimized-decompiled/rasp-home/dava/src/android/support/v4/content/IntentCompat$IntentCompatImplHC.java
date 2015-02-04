package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;

class IntentCompat$IntentCompatImplHC extends IntentCompat$IntentCompatImplBase
{

    IntentCompat$IntentCompatImplHC()
    {


        this.<init>();
    }

    public Intent makeMainActivity(ComponentName  r1)
    {


        return IntentCompatHoneycomb.makeMainActivity(r1);
    }

    public Intent makeRestartActivityTask(ComponentName  r1)
    {


        return IntentCompatHoneycomb.makeRestartActivityTask(r1);
    }
}
