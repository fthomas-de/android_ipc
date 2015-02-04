package android.support.v4.view;

import android.view.MenuItem;

class MenuItemCompat$IcsMenuVersionImpl extends MenuItemCompat$HoneycombMenuVersionImpl
{

    MenuItemCompat$IcsMenuVersionImpl()
    {


        this.<init>();
    }

    public boolean collapseActionView(MenuItem  r1)
    {


        return MenuItemCompatIcs.collapseActionView(r1);
    }

    public boolean expandActionView(MenuItem  r1)
    {


        return MenuItemCompatIcs.expandActionView(r1);
    }

    public boolean isActionViewExpanded(MenuItem  r1)
    {


        return MenuItemCompatIcs.isActionViewExpanded(r1);
    }

    public MenuItem setOnActionExpandListener(MenuItem  r1, MenuItemCompat$OnActionExpandListener  r2)
    {

        MenuItem r3;
        if (r2 != null)
        {
            r3 = MenuItemCompatIcs.setOnActionExpandListener(r1, new MenuItemCompat$IcsMenuVersionImpl$1(this, r2));
        }
        else
        {
            r3 = MenuItemCompatIcs.setOnActionExpandListener(r1, null);
        }

        return r3;
    }
}
