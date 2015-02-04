package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;
import android.os.Bundle;

final class AccessibilityNodeProviderCompatKitKat$1 extends AccessibilityNodeProvider
{
    final AccessibilityNodeProviderCompatKitKat$AccessibilityNodeInfoBridge val$bridge;

    AccessibilityNodeProviderCompatKitKat$1(AccessibilityNodeProviderCompatKitKat$AccessibilityNodeInfoBridge  r1)
    {


        val$bridge = r1;
        this.<init>();
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int  i0)
    {


        return (AccessibilityNodeInfo) val$bridge.createAccessibilityNodeInfo(i0);
    }

    public List findAccessibilityNodeInfosByText(String  r1, int  i0)
    {


        return val$bridge.findAccessibilityNodeInfosByText(r1, i0);
    }

    public AccessibilityNodeInfo findFocus(int  i0)
    {


        return (AccessibilityNodeInfo) val$bridge.findFocus(i0);
    }

    public boolean performAction(int  i0, int  i1, Bundle  r1)
    {


        return val$bridge.performAction(i0, i1, r1);
    }
}
