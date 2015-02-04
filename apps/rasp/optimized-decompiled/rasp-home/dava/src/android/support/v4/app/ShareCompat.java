package android.support.v4.app;

import android.os.Build$VERSION;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

public class ShareCompat
{
    public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    private static ShareCompat$ShareCompatImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 16)
        {
            if (Build$VERSION.SDK_INT < 14)
            {
                IMPL = new ShareCompat$ShareCompatImplBase();
            }
            else
            {
                IMPL = new ShareCompat$ShareCompatImplICS();
            }
        }
        else
        {
            IMPL = new ShareCompat$ShareCompatImplJB();
        }
    }

    public ShareCompat()
    {


        this.<init>();
    }

    static ShareCompat$ShareCompatImpl access$000()
    {


        return IMPL;
    }

    public static void configureMenuItem(Menu  r0, int  i0, ShareCompat$IntentBuilder  r1)
    {

        MenuItem r2;
        r2 = r0.findItem(i0);

        if (r2 != null)
        {
            ShareCompat.configureMenuItem(r2, r1);
            return;
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Could not find menu item with id ").append(i0).append(" in the supplied menu").toString());
        }
    }

    public static void configureMenuItem(MenuItem  r0, ShareCompat$IntentBuilder  r1)
    {


        IMPL.configureMenuItem(r0, r1);
    }

    public static ComponentName getCallingActivity(Activity  r0)
    {

        ComponentName r1;
        r1 = r0.getCallingActivity();

        if (r1 == null)
        {
            r1 = (ComponentName) r0.getIntent().getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY");
        }

        return r1;
    }

    public static String getCallingPackage(Activity  r0)
    {

        String r1;
        r1 = r0.getCallingPackage();

        if (r1 == null)
        {
            r1 = r0.getIntent().getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE");
        }

        return r1;
    }
}
