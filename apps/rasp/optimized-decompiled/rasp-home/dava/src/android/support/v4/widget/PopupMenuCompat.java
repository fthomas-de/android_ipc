package android.support.v4.widget;

import android.os.Build$VERSION;
import android.view.View$OnTouchListener;

public class PopupMenuCompat
{
    static final PopupMenuCompat$PopupMenuImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 19)
        {
            IMPL = new PopupMenuCompat$BasePopupMenuImpl();
        }
        else
        {
            IMPL = new PopupMenuCompat$KitKatPopupMenuImpl();
        }
    }

    private PopupMenuCompat()
    {


        this.<init>();
    }

    public static View$OnTouchListener getDragToOpenListener(Object  r0)
    {


        return IMPL.getDragToOpenListener(r0);
    }
}
