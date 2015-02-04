package android.support.v4.view;

import android.view.MenuItem;

class MenuItemCompatIcs
{

    MenuItemCompatIcs()
    {


        this.<init>();
    }

    public static boolean collapseActionView(MenuItem  r0)
    {


        return r0.collapseActionView();
    }

    public static boolean expandActionView(MenuItem  r0)
    {


        return r0.expandActionView();
    }

    public static boolean isActionViewExpanded(MenuItem  r0)
    {


        return r0.isActionViewExpanded();
    }

    public static MenuItem setOnActionExpandListener(MenuItem  r0, MenuItemCompatIcs$SupportActionExpandProxy  r1)
    {


        return r0.setOnActionExpandListener(new MenuItemCompatIcs$OnActionExpandListenerWrapper(r1));
    }
}
