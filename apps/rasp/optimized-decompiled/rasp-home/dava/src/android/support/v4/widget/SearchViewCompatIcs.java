package android.support.v4.widget;

import android.content.Context;
import android.view.View;
import android.widget.SearchView;

class SearchViewCompatIcs
{

    SearchViewCompatIcs()
    {


        this.<init>();
    }

    public static View newSearchView(Context  r0)
    {


        return new SearchViewCompatIcs$MySearchView(r0);
    }

    public static void setImeOptions(View  r0, int  i0)
    {


        ((SearchView) r0).setImeOptions(i0);
    }

    public static void setInputType(View  r0, int  i0)
    {


        ((SearchView) r0).setInputType(i0);
    }
}
