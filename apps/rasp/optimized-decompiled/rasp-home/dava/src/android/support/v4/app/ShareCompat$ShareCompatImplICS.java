package android.support.v4.app;

import android.view.MenuItem;

class ShareCompat$ShareCompatImplICS extends ShareCompat$ShareCompatImplBase
{

    ShareCompat$ShareCompatImplICS()
    {


        this.<init>();
    }

    public void configureMenuItem(MenuItem  r1, ShareCompat$IntentBuilder  r2)
    {


        ShareCompatICS.configureMenuItem(r1, r2.getActivity(), r2.getIntent());

        if (this.shouldAddChooserIntent(r1) != false)
        {
            r1.setIntent(r2.createChooserIntent());
        }
    }

    boolean shouldAddChooserIntent(MenuItem  r1)
    {

        boolean z1;
        if (r1.hasSubMenu() != false)
        {
            z1 = false;
        }
        else
        {
            z1 = true;
        }

        return z1;
    }
}
