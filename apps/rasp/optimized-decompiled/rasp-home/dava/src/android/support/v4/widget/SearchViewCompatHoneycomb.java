package android.support.v4.widget;

import android.view.View;
import android.widget.SearchView;
import android.content.Context;
import android.content.ComponentName;
import android.app.SearchManager;

class SearchViewCompatHoneycomb
{

    SearchViewCompatHoneycomb()
    {


        this.<init>();
    }

    public static CharSequence getQuery(View  r0)
    {


        return ((SearchView) r0).getQuery();
    }

    public static boolean isIconified(View  r0)
    {


        return ((SearchView) r0).isIconified();
    }

    public static boolean isQueryRefinementEnabled(View  r0)
    {


        return ((SearchView) r0).isQueryRefinementEnabled();
    }

    public static boolean isSubmitButtonEnabled(View  r0)
    {


        return ((SearchView) r0).isSubmitButtonEnabled();
    }

    public static Object newOnCloseListener(SearchViewCompatHoneycomb$OnCloseListenerCompatBridge  r0)
    {


        return new SearchViewCompatHoneycomb$2(r0);
    }

    public static Object newOnQueryTextListener(SearchViewCompatHoneycomb$OnQueryTextListenerCompatBridge  r0)
    {


        return new SearchViewCompatHoneycomb$1(r0);
    }

    public static View newSearchView(Context  r0)
    {


        return new SearchView(r0);
    }

    public static void setIconified(View  r0, boolean  z0)
    {


        ((SearchView) r0).setIconified(z0);
    }

    public static void setMaxWidth(View  r0, int  i0)
    {


        ((SearchView) r0).setMaxWidth(i0);
    }

    public static void setOnCloseListener(Object  r0, Object  r1)
    {


        ((SearchView) r0).setOnCloseListener((SearchView$OnCloseListener) r1);
    }

    public static void setOnQueryTextListener(Object  r0, Object  r1)
    {


        ((SearchView) r0).setOnQueryTextListener((SearchView$OnQueryTextListener) r1);
    }

    public static void setQuery(View  r0, CharSequence  r1, boolean  z0)
    {


        ((SearchView) r0).setQuery(r1, z0);
    }

    public static void setQueryHint(View  r0, CharSequence  r1)
    {


        ((SearchView) r0).setQueryHint(r1);
    }

    public static void setQueryRefinementEnabled(View  r0, boolean  z0)
    {


        ((SearchView) r0).setQueryRefinementEnabled(z0);
    }

    public static void setSearchableInfo(View  r0, ComponentName  r1)
    {

        SearchView r6;
        r6 = (SearchView) r0;
        r6.setSearchableInfo(((SearchManager) r6.getContext().getSystemService("search")).getSearchableInfo(r1));
    }

    public static void setSubmitButtonEnabled(View  r0, boolean  z0)
    {


        ((SearchView) r0).setSubmitButtonEnabled(z0);
    }
}
