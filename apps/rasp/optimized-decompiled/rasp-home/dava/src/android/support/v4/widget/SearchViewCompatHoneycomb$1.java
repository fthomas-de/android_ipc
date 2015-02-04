package android.support.v4.widget;

import android.widget.SearchView$OnQueryTextListener;

final class SearchViewCompatHoneycomb$1 implements android.widget.SearchView$OnQueryTextListener
{
    final SearchViewCompatHoneycomb$OnQueryTextListenerCompatBridge val$listener;

    SearchViewCompatHoneycomb$1(SearchViewCompatHoneycomb$OnQueryTextListenerCompatBridge  r1)
    {


        val$listener = r1;
        this.<init>();
    }

    public boolean onQueryTextChange(String  r1)
    {


        return val$listener.onQueryTextChange(r1);
    }

    public boolean onQueryTextSubmit(String  r1)
    {


        return val$listener.onQueryTextSubmit(r1);
    }
}
