package android.support.v4.view;

import android.view.ViewGroup;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

class ViewGroupCompat$ViewGroupCompatIcsImpl extends ViewGroupCompat$ViewGroupCompatHCImpl
{

    ViewGroupCompat$ViewGroupCompatIcsImpl()
    {


        this.<init>();
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup  r1, View  r2, AccessibilityEvent  r3)
    {


        return ViewGroupCompatIcs.onRequestSendAccessibilityEvent(r1, r2, r3);
    }
}
