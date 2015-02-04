package android.support.v4.view;

import android.view.MenuItem;

class MenuItemCompat$IcsMenuVersionImpl$1 implements android.support.v4.view.MenuItemCompatIcs$SupportActionExpandProxy
{
    final MenuItemCompat$IcsMenuVersionImpl this$0;
    final MenuItemCompat$OnActionExpandListener val$listener;

    MenuItemCompat$IcsMenuVersionImpl$1(MenuItemCompat$IcsMenuVersionImpl  r1, MenuItemCompat$OnActionExpandListener  r2)
    {


        this$0 = r1;
        val$listener = r2;
        this.<init>();
    }

    public boolean onMenuItemActionCollapse(MenuItem  r1)
    {


        return val$listener.onMenuItemActionCollapse(r1);
    }

    public boolean onMenuItemActionExpand(MenuItem  r1)
    {


        return val$listener.onMenuItemActionExpand(r1);
    }
}
