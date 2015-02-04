package android.support.v4.widget;

import android.os.Build$VERSION;
import android.view.View;
import android.view.View$OnTouchListener;

public class ListPopupWindowCompat
{
    static final ListPopupWindowCompat$ListPopupWindowImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 19)
        {
            IMPL = new ListPopupWindowCompat$BaseListPopupWindowImpl();
        }
        else
        {
            IMPL = new ListPopupWindowCompat$KitKatListPopupWindowImpl();
        }
    }

    private ListPopupWindowCompat()
    {


        this.<init>();
    }

    public static View$OnTouchListener createDragToOpenListener(Object  r0, View  r1)
    {


        return IMPL.createDragToOpenListener(r0, r1);
    }
}
