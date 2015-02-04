package android.support.v4.view;

import android.view.MenuItem;
import android.view.View;

abstract interface MenuItemCompat$MenuVersionImpl
{

    public abstract boolean collapseActionView(android.view.MenuItem  r0);

    public abstract boolean expandActionView(android.view.MenuItem  r0);

    public abstract android.view.View getActionView(android.view.MenuItem  r0);

    public abstract boolean isActionViewExpanded(android.view.MenuItem  r0);

    public abstract android.view.MenuItem setActionView(android.view.MenuItem  r0, int  i1);

    public abstract android.view.MenuItem setActionView(android.view.MenuItem  r0, android.view.View  r1);

    public abstract android.view.MenuItem setOnActionExpandListener(android.view.MenuItem  r0, android.support.v4.view.MenuItemCompat$OnActionExpandListener  r1);

    public abstract void setShowAsAction(android.view.MenuItem  r0, int  i1);
}
