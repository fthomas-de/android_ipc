package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.ViewParent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class ViewParentCompat
{
    static final ViewParentCompat$ViewParentCompatImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 14)
        {
            IMPL = new ViewParentCompat$ViewParentCompatStubImpl();
        }
        else
        {
            IMPL = new ViewParentCompat$ViewParentCompatICSImpl();
        }
    }

    private ViewParentCompat()
    {


        this.<init>();
    }

    public static boolean requestSendAccessibilityEvent(ViewParent  r0, View  r1, AccessibilityEvent  r2)
    {


        return IMPL.requestSendAccessibilityEvent(r0, r1, r2);
    }
}
