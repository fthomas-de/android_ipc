package android.support.v4.view;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup;
import android.os.Bundle;

class AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl$1 implements android.support.v4.view.AccessibilityDelegateCompatJellyBean$AccessibilityDelegateBridgeJellyBean
{
    final AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl this$0;
    final AccessibilityDelegateCompat val$compat;

    AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl$1(AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl  r1, AccessibilityDelegateCompat  r2)
    {


        this$0 = r1;
        val$compat = r2;
        this.<init>();
    }

    public boolean dispatchPopulateAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        return val$compat.dispatchPopulateAccessibilityEvent(r1, r2);
    }

    public Object getAccessibilityNodeProvider(View  r1)
    {

        AccessibilityNodeProviderCompat r3;
        Object r4;
        r3 = val$compat.getAccessibilityNodeProvider(r1);

        if (r3 == null)
        {
            r4 = null;
        }
        else
        {
            r4 = r3.getProvider();
        }

        return r4;
    }

    public void onInitializeAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        val$compat.onInitializeAccessibilityEvent(r1, r2);
    }

    public void onInitializeAccessibilityNodeInfo(View  r1, Object  r2)
    {


        val$compat.onInitializeAccessibilityNodeInfo(r1, new AccessibilityNodeInfoCompat(r2));
    }

    public void onPopulateAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        val$compat.onPopulateAccessibilityEvent(r1, r2);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup  r1, View  r2, AccessibilityEvent  r3)
    {


        return val$compat.onRequestSendAccessibilityEvent(r1, r2, r3);
    }

    public boolean performAccessibilityAction(View  r1, int  i0, Bundle  r2)
    {


        return val$compat.performAccessibilityAction(r1, i0, r2);
    }

    public void sendAccessibilityEvent(View  r1, int  i0)
    {


        val$compat.sendAccessibilityEvent(r1, i0);
    }

    public void sendAccessibilityEventUnchecked(View  r1, AccessibilityEvent  r2)
    {


        val$compat.sendAccessibilityEventUnchecked(r1, r2);
    }
}
