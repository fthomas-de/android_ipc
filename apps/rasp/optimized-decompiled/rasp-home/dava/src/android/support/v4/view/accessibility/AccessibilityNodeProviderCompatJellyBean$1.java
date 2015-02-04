package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;
import android.os.Bundle;

final class AccessibilityNodeProviderCompatJellyBean$1 extends AccessibilityNodeProvider
{
    final AccessibilityNodeProviderCompatJellyBean$AccessibilityNodeInfoBridge val$bridge;

    AccessibilityNodeProviderCompatJellyBean$1(AccessibilityNodeProviderCompatJellyBean$AccessibilityNodeInfoBridge  r1)
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

    public boolean performAction(int  i0, int  i1, Bundle  r1)
    {


        return val$bridge.performAction(i0, i1, r1);
    }
}
