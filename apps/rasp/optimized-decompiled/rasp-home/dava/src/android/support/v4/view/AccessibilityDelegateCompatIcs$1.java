package android.support.v4.view;

import android.view.View$AccessibilityDelegate;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.ViewGroup;

final class AccessibilityDelegateCompatIcs$1 extends View$AccessibilityDelegate
{
    final AccessibilityDelegateCompatIcs$AccessibilityDelegateBridge val$bridge;

    AccessibilityDelegateCompatIcs$1(AccessibilityDelegateCompatIcs$AccessibilityDelegateBridge  r1)
    {


        val$bridge = r1;
        this.<init>();
    }

    public boolean dispatchPopulateAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        return val$bridge.dispatchPopulateAccessibilityEvent(r1, r2);
    }

    public void onInitializeAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        val$bridge.onInitializeAccessibilityEvent(r1, r2);
    }

    public void onInitializeAccessibilityNodeInfo(View  r1, AccessibilityNodeInfo  r2)
    {


        val$bridge.onInitializeAccessibilityNodeInfo(r1, r2);
    }

    public void onPopulateAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        val$bridge.onPopulateAccessibilityEvent(r1, r2);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup  r1, View  r2, AccessibilityEvent  r3)
    {


        return val$bridge.onRequestSendAccessibilityEvent(r1, r2, r3);
    }

    public void sendAccessibilityEvent(View  r1, int  i0)
    {


        val$bridge.sendAccessibilityEvent(r1, i0);
    }

    public void sendAccessibilityEventUnchecked(View  r1, AccessibilityEvent  r2)
    {


        val$bridge.sendAccessibilityEventUnchecked(r1, r2);
    }
}
