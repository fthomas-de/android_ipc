package android.support.v4.view;

import android.view.MenuItem;
import android.view.View;

class MenuItemCompat$HoneycombMenuVersionImpl implements android.support.v4.view.MenuItemCompat$MenuVersionImpl
{

    MenuItemCompat$HoneycombMenuVersionImpl()
    {


        this.<init>();
    }

    public boolean collapseActionView(MenuItem  r1)
    {


        return false;
    }

    public boolean expandActionView(MenuItem  r1)
    {


        return false;
    }

    public View getActionView(MenuItem  r1)
    {


        return MenuItemCompatHoneycomb.getActionView(r1);
    }

    public boolean isActionViewExpanded(MenuItem  r1)
    {


        return false;
    }

    public MenuItem setActionView(MenuItem  r1, int  i0)
    {


        return MenuItemCompatHoneycomb.setActionView(r1, i0);
    }

    public MenuItem setActionView(MenuItem  r1, View  r2)
    {


        return MenuItemCompatHoneycomb.setActionView(r1, r2);
    }

    public MenuItem setOnActionExpandListener(MenuItem  r1, MenuItemCompat$OnActionExpandListener  r2)
    {


        return r1;
    }

    public void setShowAsAction(MenuItem  r1, int  i0)
    {


        MenuItemCompatHoneycomb.setShowAsAction(r1, i0);
    }
}
