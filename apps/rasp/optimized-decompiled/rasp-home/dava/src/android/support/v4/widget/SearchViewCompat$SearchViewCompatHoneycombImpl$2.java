package android.support.v4.widget;


class SearchViewCompat$SearchViewCompatHoneycombImpl$2 implements android.support.v4.widget.SearchViewCompatHoneycomb$OnCloseListenerCompatBridge
{
    final SearchViewCompat$SearchViewCompatHoneycombImpl this$0;
    final SearchViewCompat$OnCloseListenerCompat val$listener;

    SearchViewCompat$SearchViewCompatHoneycombImpl$2(SearchViewCompat$SearchViewCompatHoneycombImpl  r1, SearchViewCompat$OnCloseListenerCompat  r2)
    {


        this$0 = r1;
        val$listener = r2;
        this.<init>();
    }

    public boolean onClose()
    {


        return val$listener.onClose();
    }
}
