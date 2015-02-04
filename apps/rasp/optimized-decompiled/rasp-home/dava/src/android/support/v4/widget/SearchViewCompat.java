package android.support.v4.widget;

import android.os.Build$VERSION;
import android.content.Context;
import android.view.View;
import android.content.ComponentName;

public class SearchViewCompat
{
    private static final SearchViewCompat$SearchViewCompatImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 14)
        {
            if (Build$VERSION.SDK_INT < 11)
            {
                IMPL = new SearchViewCompat$SearchViewCompatStubImpl();
            }
            else
            {
                IMPL = new SearchViewCompat$SearchViewCompatHoneycombImpl();
            }
        }
        else
        {
            IMPL = new SearchViewCompat$SearchViewCompatIcsImpl();
        }
    }

    private SearchViewCompat(Context  r1)
    {


        this.<init>();
    }

    static SearchViewCompat$SearchViewCompatImpl access$000()
    {


        return IMPL;
    }

    public static CharSequence getQuery(View  r0)
    {


        return IMPL.getQuery(r0);
    }

    public static boolean isIconified(View  r0)
    {


        return IMPL.isIconified(r0);
    }

    public static boolean isQueryRefinementEnabled(View  r0)
    {


        return IMPL.isQueryRefinementEnabled(r0);
    }

    public static boolean isSubmitButtonEnabled(View  r0)
    {


        return IMPL.isSubmitButtonEnabled(r0);
    }

    public static View newSearchView(Context  r0)
    {


        return IMPL.newSearchView(r0);
    }

    public static void setIconified(View  r0, boolean  z0)
    {


        IMPL.setIconified(r0, z0);
    }

    public static void setImeOptions(View  r0, int  i0)
    {


        IMPL.setImeOptions(r0, i0);
    }

    public static void setInputType(View  r0, int  i0)
    {


        IMPL.setInputType(r0, i0);
    }

    public static void setMaxWidth(View  r0, int  i0)
    {


        IMPL.setMaxWidth(r0, i0);
    }

    public static void setOnCloseListener(View  r0, SearchViewCompat$OnCloseListenerCompat  r1)
    {


        IMPL.setOnCloseListener(r0, r1.mListener);
    }

    public static void setOnQueryTextListener(View  r0, SearchViewCompat$OnQueryTextListenerCompat  r1)
    {


        IMPL.setOnQueryTextListener(r0, r1.mListener);
    }

    public static void setQuery(View  r0, CharSequence  r1, boolean  z0)
    {


        IMPL.setQuery(r0, r1, z0);
    }

    public static void setQueryHint(View  r0, CharSequence  r1)
    {


        IMPL.setQueryHint(r0, r1);
    }

    public static void setQueryRefinementEnabled(View  r0, boolean  z0)
    {


        IMPL.setQueryRefinementEnabled(r0, z0);
    }

    public static void setSearchableInfo(View  r0, ComponentName  r1)
    {


        IMPL.setSearchableInfo(r0, r1);
    }

    public static void setSubmitButtonEnabled(View  r0, boolean  z0)
    {


        IMPL.setSubmitButtonEnabled(r0, z0);
    }
}
