package android.support.v4.view;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup;

class AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl extends AccessibilityDelegateCompat$AccessibilityDelegateStubImpl
{

    AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl()
    {


        this.<init>();
    }

    public boolean dispatchPopulateAccessibilityEvent(Object  r1, View  r2, AccessibilityEvent  r3)
    {


        return AccessibilityDelegateCompatIcs.dispatchPopulateAccessibilityEvent(r1, r2, r3);
    }

    public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat  r1)
    {


        return AccessibilityDelegateCompatIcs.newAccessibilityDelegateBridge(new AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl$1(this, r1));
    }

    public Object newAccessiblityDelegateDefaultImpl()
    {


        return AccessibilityDelegateCompatIcs.newAccessibilityDelegateDefaultImpl();
    }

    public void onInitializeAccessibilityEvent(Object  r1, View  r2, AccessibilityEvent  r3)
    {


        AccessibilityDelegateCompatIcs.onInitializeAccessibilityEvent(r1, r2, r3);
    }

    public void onInitializeAccessibilityNodeInfo(Object  r1, View  r2, AccessibilityNodeInfoCompat  r3)
    {


        AccessibilityDelegateCompatIcs.onInitializeAccessibilityNodeInfo(r1, r2, r3.getInfo());
    }

    public void onPopulateAccessibilityEvent(Object  r1, View  r2, AccessibilityEvent  r3)
    {


        AccessibilityDelegateCompatIcs.onPopulateAccessibilityEvent(r1, r2, r3);
    }

    public boolean onRequestSendAccessibilityEvent(Object  r1, ViewGroup  r2, View  r3, AccessibilityEvent  r4)
    {


        return AccessibilityDelegateCompatIcs.onRequestSendAccessibilityEvent(r1, r2, r3, r4);
    }

    public void sendAccessibilityEvent(Object  r1, View  r2, int  i0)
    {


        AccessibilityDelegateCompatIcs.sendAccessibilityEvent(r1, r2, i0);
    }

    public void sendAccessibilityEventUnchecked(Object  r1, View  r2, AccessibilityEvent  r3)
    {


        AccessibilityDelegateCompatIcs.sendAccessibilityEventUnchecked(r1, r2, r3);
    }
}
