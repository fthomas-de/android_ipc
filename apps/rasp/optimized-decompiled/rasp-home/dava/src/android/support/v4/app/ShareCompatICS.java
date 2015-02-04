package android.support.v4.app;

import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.view.ActionProvider;
import android.widget.ShareActionProvider;

class ShareCompatICS
{
    private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";

    ShareCompatICS()
    {


        this.<init>();
    }

    public static void configureMenuItem(MenuItem  r0, Activity  r1, Intent  r2)
    {

        ActionProvider r3;
        ShareActionProvider r5;
        r3 = r0.getActionProvider();

        if (r3 instanceof ShareActionProvider != false)
        {
            r5 = (ShareActionProvider) r3;
        }
        else
        {
            r5 = new ShareActionProvider(r1);
        }

        r5.setShareHistoryFileName((new StringBuilder()).append(".sharecompat_").append(r1.getClass().getName()).toString());
        r5.setShareIntent(r2);
        r0.setActionProvider(r5);
    }
}
