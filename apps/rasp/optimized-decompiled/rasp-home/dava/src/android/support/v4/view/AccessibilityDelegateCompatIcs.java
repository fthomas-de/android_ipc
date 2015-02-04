package android.support.v4.view;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.View$AccessibilityDelegate;
import android.view.ViewGroup;

class AccessibilityDelegateCompatIcs
{

    AccessibilityDelegateCompatIcs()
    {


        this.<init>();
    }

    public static boolean dispatchPopulateAccessibilityEvent(Object  r0, View  r1, AccessibilityEvent  r2)
    {


        return ((View$AccessibilityDelegate) r0).dispatchPopulateAccessibilityEvent(r1, r2);
    }

    public static Object newAccessibilityDelegateBridge(AccessibilityDelegateCompatIcs$AccessibilityDelegateBridge  r0)
    {


        return new AccessibilityDelegateCompatIcs$1(r0);
    }

    public static Object newAccessibilityDelegateDefaultImpl()
    {


        return new View$AccessibilityDelegate();
    }

    public static void onInitializeAccessibilityEvent(Object  r0, View  r1, AccessibilityEvent  r2)
    {


        ((View$AccessibilityDelegate) r0).onInitializeAccessibilityEvent(r1, r2);
    }

    public static void onInitializeAccessibilityNodeInfo(Object  r0, View  r1, Object  r2)
    {


        ((View$AccessibilityDelegate) r0).onInitializeAccessibilityNodeInfo(r1, (AccessibilityNodeInfo) r2);
    }

    public static void onPopulateAccessibilityEvent(Object  r0, View  r1, AccessibilityEvent  r2)
    {


        ((View$AccessibilityDelegate) r0).onPopulateAccessibilityEvent(r1, r2);
    }

    public static boolean onRequestSendAccessibilityEvent(Object  r0, ViewGroup  r1, View  r2, AccessibilityEvent  r3)
    {


        return ((View$AccessibilityDelegate) r0).onRequestSendAccessibilityEvent(r1, r2, r3);
    }

    public static void sendAccessibilityEvent(Object  r0, View  r1, int  i0)
    {


        ((View$AccessibilityDelegate) r0).sendAccessibilityEvent(r1, i0);
    }

    public static void sendAccessibilityEventUnchecked(Object  r0, View  r1, AccessibilityEvent  r2)
    {


        ((View$AccessibilityDelegate) r0).sendAccessibilityEventUnchecked(r1, r2);
    }
}
