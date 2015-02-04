package android.support.v4.view;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;

class ViewCompat$ICSViewCompatImpl extends ViewCompat$HCViewCompatImpl
{

    ViewCompat$ICSViewCompatImpl()
    {


        this.<init>();
    }

    public boolean canScrollHorizontally(View  r1, int  i0)
    {


        return ViewCompatICS.canScrollHorizontally(r1, i0);
    }

    public boolean canScrollVertically(View  r1, int  i0)
    {


        return ViewCompatICS.canScrollVertically(r1, i0);
    }

    public void onInitializeAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        ViewCompatICS.onInitializeAccessibilityEvent(r1, r2);
    }

    public void onInitializeAccessibilityNodeInfo(View  r1, AccessibilityNodeInfoCompat  r2)
    {


        ViewCompatICS.onInitializeAccessibilityNodeInfo(r1, r2.getInfo());
    }

    public void onPopulateAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        ViewCompatICS.onPopulateAccessibilityEvent(r1, r2);
    }

    public void setAccessibilityDelegate(View  r1, AccessibilityDelegateCompat  r2)
    {


        ViewCompatICS.setAccessibilityDelegate(r1, r2.getBridge());
    }
}
