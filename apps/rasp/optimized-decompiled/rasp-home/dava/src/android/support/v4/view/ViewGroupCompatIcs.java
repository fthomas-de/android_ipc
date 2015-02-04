package android.support.v4.view;

import android.view.ViewGroup;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

class ViewGroupCompatIcs
{

    ViewGroupCompatIcs()
    {


        this.<init>();
    }

    public static boolean onRequestSendAccessibilityEvent(ViewGroup  r0, View  r1, AccessibilityEvent  r2)
    {


        return r0.onRequestSendAccessibilityEvent(r1, r2);
    }
}
