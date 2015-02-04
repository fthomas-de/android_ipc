package android.support.v4.view;

import android.view.View;
import android.view.View$AccessibilityDelegate;
import android.os.Bundle;

class AccessibilityDelegateCompatJellyBean
{

    AccessibilityDelegateCompatJellyBean()
    {


        this.<init>();
    }

    public static Object getAccessibilityNodeProvider(Object  r0, View  r1)
    {


        return ((View$AccessibilityDelegate) r0).getAccessibilityNodeProvider(r1);
    }

    public static Object newAccessibilityDelegateBridge(AccessibilityDelegateCompatJellyBean$AccessibilityDelegateBridgeJellyBean  r0)
    {


        return new AccessibilityDelegateCompatJellyBean$1(r0);
    }

    public static boolean performAccessibilityAction(Object  r0, View  r1, int  i0, Bundle  r2)
    {


        return ((View$AccessibilityDelegate) r0).performAccessibilityAction(r1, i0, r2);
    }
}
