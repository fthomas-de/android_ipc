package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.MenuItem;
import android.support.v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.View;

public class MenuItemCompat
{
    static final MenuItemCompat$MenuVersionImpl IMPL;
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    static
    {

        int i0;
        i0 = Build$VERSION.SDK_INT;

        if (i0 < 14)
        {
            if (i0 < 11)
            {
                IMPL = new MenuItemCompat$BaseMenuVersionImpl();
            }
            else
            {
                IMPL = new MenuItemCompat$HoneycombMenuVersionImpl();
            }
        }
        else
        {
            IMPL = new MenuItemCompat$IcsMenuVersionImpl();
        }
    }

    public MenuItemCompat()
    {


        this.<init>();
    }

    public static boolean collapseActionView(MenuItem  r0)
    {

        boolean z1;
        if (r0 instanceof SupportMenuItem == false)
        {
            z1 = IMPL.collapseActionView(r0);
        }
        else
        {
            z1 = ((SupportMenuItem) r0).collapseActionView();
        }

        return z1;
    }

    public static boolean expandActionView(MenuItem  r0)
    {

        boolean z1;
        if (r0 instanceof SupportMenuItem == false)
        {
            z1 = IMPL.expandActionView(r0);
        }
        else
        {
            z1 = ((SupportMenuItem) r0).expandActionView();
        }

        return z1;
    }

    public static ActionProvider getActionProvider(MenuItem  r0)
    {

        ActionProvider r3;
        if (r0 instanceof SupportMenuItem == false)
        {
            Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
            r3 = null;
        }
        else
        {
            r3 = ((SupportMenuItem) r0).getSupportActionProvider();
        }

        return r3;
    }

    public static View getActionView(MenuItem  r0)
    {

        View r2;
        if (r0 instanceof SupportMenuItem == false)
        {
            r2 = IMPL.getActionView(r0);
        }
        else
        {
            r2 = ((SupportMenuItem) r0).getActionView();
        }

        return r2;
    }

    public static boolean isActionViewExpanded(MenuItem  r0)
    {

        boolean z1;
        if (r0 instanceof SupportMenuItem == false)
        {
            z1 = IMPL.isActionViewExpanded(r0);
        }
        else
        {
            z1 = ((SupportMenuItem) r0).isActionViewExpanded();
        }

        return z1;
    }

    public static MenuItem setActionProvider(MenuItem  r0, ActionProvider  r1)
    {


        if (r0 instanceof SupportMenuItem == false)
        {
            Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        }
        else
        {
            r0 = ((SupportMenuItem) r0).setSupportActionProvider(r1);
        }

        return r0;
    }

    public static MenuItem setActionView(MenuItem  r0, int  i0)
    {

        MenuItem r2;
        if (r0 instanceof SupportMenuItem == false)
        {
            r2 = IMPL.setActionView(r0, i0);
        }
        else
        {
            r2 = ((SupportMenuItem) r0).setActionView(i0);
        }

        return r2;
    }

    public static MenuItem setActionView(MenuItem  r0, View  r1)
    {

        MenuItem r3;
        if (r0 instanceof SupportMenuItem == false)
        {
            r3 = IMPL.setActionView(r0, r1);
        }
        else
        {
            r3 = ((SupportMenuItem) r0).setActionView(r1);
        }

        return r3;
    }

    public static MenuItem setOnActionExpandListener(MenuItem  r0, MenuItemCompat$OnActionExpandListener  r1)
    {

        MenuItem r3;
        if (r0 instanceof SupportMenuItem == false)
        {
            r3 = IMPL.setOnActionExpandListener(r0, r1);
        }
        else
        {
            r3 = ((SupportMenuItem) r0).setSupportOnActionExpandListener(r1);
        }

        return r3;
    }

    public static void setShowAsAction(MenuItem  r0, int  i0)
    {


        if (r0 instanceof SupportMenuItem == false)
        {
            IMPL.setShowAsAction(r0, i0);
        }
        else
        {
            ((SupportMenuItem) r0).setShowAsAction(i0);
        }
    }
}
