package android.support.v4.internal.view;

import android.view.MenuItem;
import android.view.View;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat$OnActionExpandListener;

public abstract interface SupportMenuItem extends android.view.MenuItem
{
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;

    public abstract boolean collapseActionView();

    public abstract boolean expandActionView();

    public abstract android.view.View getActionView();

    public abstract android.support.v4.view.ActionProvider getSupportActionProvider();

    public abstract boolean isActionViewExpanded();

    public abstract android.view.MenuItem setActionView(int  i0);

    public abstract android.view.MenuItem setActionView(android.view.View  r0);

    public abstract void setShowAsAction(int  i0);

    public abstract android.view.MenuItem setShowAsActionFlags(int  i0);

    public abstract android.support.v4.internal.view.SupportMenuItem setSupportActionProvider(android.support.v4.view.ActionProvider  r0);

    public abstract android.support.v4.internal.view.SupportMenuItem setSupportOnActionExpandListener(android.support.v4.view.MenuItemCompat$OnActionExpandListener  r0);
}
