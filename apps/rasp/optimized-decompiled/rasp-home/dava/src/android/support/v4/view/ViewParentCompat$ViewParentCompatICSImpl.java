package android.support.v4.view;

import android.view.ViewParent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

class ViewParentCompat$ViewParentCompatICSImpl extends ViewParentCompat$ViewParentCompatStubImpl
{

    ViewParentCompat$ViewParentCompatICSImpl()
    {


        this.<init>();
    }

    public boolean requestSendAccessibilityEvent(ViewParent  r1, View  r2, AccessibilityEvent  r3)
    {


        return ViewParentCompatICS.requestSendAccessibilityEvent(r1, r2, r3);
    }
}
