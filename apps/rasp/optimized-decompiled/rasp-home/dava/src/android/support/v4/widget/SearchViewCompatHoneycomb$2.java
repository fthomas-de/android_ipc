package android.support.v4.widget;

import android.widget.SearchView$OnCloseListener;

final class SearchViewCompatHoneycomb$2 implements android.widget.SearchView$OnCloseListener
{
    final SearchViewCompatHoneycomb$OnCloseListenerCompatBridge val$listener;

    SearchViewCompatHoneycomb$2(SearchViewCompatHoneycomb$OnCloseListenerCompatBridge  r1)
    {


        val$listener = r1;
        this.<init>();
    }

    public boolean onClose()
    {


        return val$listener.onClose();
    }
}
