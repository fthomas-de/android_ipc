package android.support.v4.view.accessibility;

import java.util.List;
import java.util.ArrayList;
import android.os.Bundle;

class AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl$1 implements android.support.v4.view.accessibility.AccessibilityNodeProviderCompatJellyBean$AccessibilityNodeInfoBridge
{
    final AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl this$0;
    final AccessibilityNodeProviderCompat val$compat;

    AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl$1(AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl  r1, AccessibilityNodeProviderCompat  r2)
    {


        this$0 = r1;
        val$compat = r2;
        this.<init>();
    }

    public Object createAccessibilityNodeInfo(int  i0)
    {

        AccessibilityNodeInfoCompat r2;
        Object r3;
        r2 = val$compat.createAccessibilityNodeInfo(i0);

        if (r2 != null)
        {
            r3 = r2.getInfo();
        }
        else
        {
            r3 = null;
        }

        return r3;
    }

    public List findAccessibilityNodeInfosByText(String  r1, int  i0)
    {

        List r3;
        ArrayList r4;
        int i1, i2;
        r3 = val$compat.findAccessibilityNodeInfosByText(r1, i0);
        r4 = new ArrayList();
        i1 = r3.size();
        i2 = 0;

        while (i2 < i1)
        {
            r4.add(((AccessibilityNodeInfoCompat) r3.get(i2)).getInfo());
            i2 = i2 + 1;
        }

        return r4;
    }

    public boolean performAction(int  i0, int  i1, Bundle  r1)
    {


        return val$compat.performAction(i0, i1, r1);
    }
}
