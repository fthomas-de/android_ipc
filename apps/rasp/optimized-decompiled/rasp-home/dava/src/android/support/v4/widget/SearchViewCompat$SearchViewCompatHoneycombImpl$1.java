package android.support.v4.widget;


class SearchViewCompat$SearchViewCompatHoneycombImpl$1 implements android.support.v4.widget.SearchViewCompatHoneycomb$OnQueryTextListenerCompatBridge
{
    final SearchViewCompat$SearchViewCompatHoneycombImpl this$0;
    final SearchViewCompat$OnQueryTextListenerCompat val$listener;

    SearchViewCompat$SearchViewCompatHoneycombImpl$1(SearchViewCompat$SearchViewCompatHoneycombImpl  r1, SearchViewCompat$OnQueryTextListenerCompat  r2)
    {


        this$0 = r1;
        val$listener = r2;
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
