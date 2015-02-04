package android.support.v4.app;

import android.support.v4.content.ContextCompat;
import android.app.Activity;
import android.os.Build$VERSION;
import android.content.Intent;
import android.os.Bundle;

public class ActivityCompat extends ContextCompat
{

    public ActivityCompat()
    {


        this.<init>();
    }

    public static void finishAffinity(Activity  r0)
    {


        if (Build$VERSION.SDK_INT < 16)
        {
            r0.finish();
        }
        else
        {
            ActivityCompatJB.finishAffinity(r0);
        }
    }

    public static boolean invalidateOptionsMenu(Activity  r0)
    {

        boolean z0;
        if (Build$VERSION.SDK_INT < 11)
        {
            z0 = false;
        }
        else
        {
            ActivityCompatHoneycomb.invalidateOptionsMenu(r0);
            z0 = true;
        }

        return z0;
    }

    public static void startActivity(Activity  r0, Intent  r1, Bundle  r2)
    {


        if (Build$VERSION.SDK_INT < 16)
        {
            r0.startActivity(r1);
        }
        else
        {
            ActivityCompatJB.startActivity(r0, r1, r2);
        }
    }

    public static void startActivityForResult(Activity  r0, Intent  r1, int  i0, Bundle  r2)
    {


        if (Build$VERSION.SDK_INT < 16)
        {
            r0.startActivityForResult(r1, i0);
        }
        else
        {
            ActivityCompatJB.startActivityForResult(r0, r1, i0, r2);
        }
    }
}
