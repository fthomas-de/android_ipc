package android.support.v4.widget;

import android.widget.SearchView;
import android.content.Context;

public class SearchViewCompatIcs$MySearchView extends SearchView
{

    public SearchViewCompatIcs$MySearchView(Context  r1)
    {
        super(r1);


        this.<init>(r1);
    }

    public void onActionViewCollapsed()
    {


        this.setQuery("", false);
        this.onActionViewCollapsed();
    }
}
