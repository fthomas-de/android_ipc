package android.support.v4.view;

import android.view.MenuItem$OnActionExpandListener;
import android.view.MenuItem;

class MenuItemCompatIcs$OnActionExpandListenerWrapper implements android.view.MenuItem$OnActionExpandListener
{
    private MenuItemCompatIcs$SupportActionExpandProxy mWrapped;

    public MenuItemCompatIcs$OnActionExpandListenerWrapper(MenuItemCompatIcs$SupportActionExpandProxy  r1)
    {


        this.<init>();
        mWrapped = r1;
    }

    public boolean onMenuItemActionCollapse(MenuItem  r1)
    {


        return mWrapped.onMenuItemActionCollapse(r1);
    }

    public boolean onMenuItemActionExpand(MenuItem  r1)
    {


        return mWrapped.onMenuItemActionExpand(r1);
    }
}
