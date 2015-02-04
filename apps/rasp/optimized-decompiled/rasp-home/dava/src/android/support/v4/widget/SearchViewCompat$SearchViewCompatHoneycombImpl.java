package android.support.v4.widget;

import android.view.View;
import android.content.Context;
import android.content.ComponentName;

class SearchViewCompat$SearchViewCompatHoneycombImpl extends SearchViewCompat$SearchViewCompatStubImpl
{

    SearchViewCompat$SearchViewCompatHoneycombImpl()
    {


        this.<init>();
    }

    public CharSequence getQuery(View  r1)
    {


        return SearchViewCompatHoneycomb.getQuery(r1);
    }

    public boolean isIconified(View  r1)
    {


        return SearchViewCompatHoneycomb.isIconified(r1);
    }

    public boolean isQueryRefinementEnabled(View  r1)
    {


        return SearchViewCompatHoneycomb.isQueryRefinementEnabled(r1);
    }

    public boolean isSubmitButtonEnabled(View  r1)
    {


        return SearchViewCompatHoneycomb.isSubmitButtonEnabled(r1);
    }

    public Object newOnCloseListener(SearchViewCompat$OnCloseListenerCompat  r1)
    {


        return SearchViewCompatHoneycomb.newOnCloseListener(new SearchViewCompat$SearchViewCompatHoneycombImpl$2(this, r1));
    }

    public Object newOnQueryTextListener(SearchViewCompat$OnQueryTextListenerCompat  r1)
    {


        return SearchViewCompatHoneycomb.newOnQueryTextListener(new SearchViewCompat$SearchViewCompatHoneycombImpl$1(this, r1));
    }

    public View newSearchView(Context  r1)
    {


        return SearchViewCompatHoneycomb.newSearchView(r1);
    }

    public void setIconified(View  r1, boolean  z0)
    {


        SearchViewCompatHoneycomb.setIconified(r1, z0);
    }

    public void setMaxWidth(View  r1, int  i0)
    {


        SearchViewCompatHoneycomb.setMaxWidth(r1, i0);
    }

    public void setOnCloseListener(Object  r1, Object  r2)
    {


        SearchViewCompatHoneycomb.setOnCloseListener(r1, r2);
    }

    public void setOnQueryTextListener(Object  r1, Object  r2)
    {


        SearchViewCompatHoneycomb.setOnQueryTextListener(r1, r2);
    }

    public void setQuery(View  r1, CharSequence  r2, boolean  z0)
    {


        SearchViewCompatHoneycomb.setQuery(r1, r2, z0);
    }

    public void setQueryHint(View  r1, CharSequence  r2)
    {


        SearchViewCompatHoneycomb.setQueryHint(r1, r2);
    }

    public void setQueryRefinementEnabled(View  r1, boolean  z0)
    {


        SearchViewCompatHoneycomb.setQueryRefinementEnabled(r1, z0);
    }

    public void setSearchableInfo(View  r1, ComponentName  r2)
    {


        SearchViewCompatHoneycomb.setSearchableInfo(r1, r2);
    }

    public void setSubmitButtonEnabled(View  r1, boolean  z0)
    {


        SearchViewCompatHoneycomb.setSubmitButtonEnabled(r1, z0);
    }
}
