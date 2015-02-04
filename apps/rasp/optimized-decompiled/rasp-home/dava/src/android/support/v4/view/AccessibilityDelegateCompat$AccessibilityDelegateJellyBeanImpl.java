package android.support.v4.view;

import android.view.View;
import android.os.Bundle;

class AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl extends AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl
{

    AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl()
    {


        this.<init>();
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object  r1, View  r2)
    {

        Object r3;
        AccessibilityNodeProviderCompat r4;
        r3 = AccessibilityDelegateCompatJellyBean.getAccessibilityNodeProvider(r1, r2);

        if (r3 == null)
        {
            r4 = null;
        }
        else
        {
            r4 = new AccessibilityNodeProviderCompat(r3);
        }

        return r4;
    }

    public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat  r1)
    {


        return AccessibilityDelegateCompatJellyBean.newAccessibilityDelegateBridge(new AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl$1(this, r1));
    }

    public boolean performAccessibilityAction(Object  r1, View  r2, int  i0, Bundle  r3)
    {


        return AccessibilityDelegateCompatJellyBean.performAccessibilityAction(r1, r2, i0, r3);
    }
}
