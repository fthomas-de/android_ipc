package android.support.v4.widget;

import android.view.View;
import android.content.Context;
import android.content.ComponentName;

abstract interface SearchViewCompat$SearchViewCompatImpl
{

    public abstract java.lang.CharSequence getQuery(android.view.View  r0);

    public abstract boolean isIconified(android.view.View  r0);

    public abstract boolean isQueryRefinementEnabled(android.view.View  r0);

    public abstract boolean isSubmitButtonEnabled(android.view.View  r0);

    public abstract java.lang.Object newOnCloseListener(android.support.v4.widget.SearchViewCompat$OnCloseListenerCompat  r0);

    public abstract java.lang.Object newOnQueryTextListener(android.support.v4.widget.SearchViewCompat$OnQueryTextListenerCompat  r0);

    public abstract android.view.View newSearchView(android.content.Context  r0);

    public abstract void setIconified(android.view.View  r0, boolean  z1);

    public abstract void setImeOptions(android.view.View  r0, int  i1);

    public abstract void setInputType(android.view.View  r0, int  i1);

    public abstract void setMaxWidth(android.view.View  r0, int  i1);

    public abstract void setOnCloseListener(java.lang.Object  r0, java.lang.Object  r1);

    public abstract void setOnQueryTextListener(java.lang.Object  r0, java.lang.Object  r1);

    public abstract void setQuery(android.view.View  r0, java.lang.CharSequence  r1, boolean  z2);

    public abstract void setQueryHint(android.view.View  r0, java.lang.CharSequence  r1);

    public abstract void setQueryRefinementEnabled(android.view.View  r0, boolean  z1);

    public abstract void setSearchableInfo(android.view.View  r0, android.content.ComponentName  r1);

    public abstract void setSubmitButtonEnabled(android.view.View  r0, boolean  z1);
}
