package android.support.v4.view;

import android.view.ViewParent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.content.Context;

class ViewParentCompat$ViewParentCompatStubImpl implements android.support.v4.view.ViewParentCompat$ViewParentCompatImpl
{

    ViewParentCompat$ViewParentCompatStubImpl()
    {


        this.<init>();
    }

    public boolean requestSendAccessibilityEvent(ViewParent  r1, View  r2, AccessibilityEvent  r3)
    {

        boolean z0;
        if (r2 != null)
        {
            ((AccessibilityManager) r2.getContext().getSystemService("accessibility")).sendAccessibilityEvent(r3);
            z0 = true;
        }
        else
        {
            z0 = false;
        }

        return z0;
    }
}
