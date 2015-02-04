package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatKitKat
{

    AccessibilityNodeInfoCompatKitKat()
    {


        this.<init>();
    }

    public static int getLiveRegion(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).getLiveRegion();
    }

    public static void setLiveRegion(Object  r0, int  i0)
    {


        ((AccessibilityNodeInfo) r0).setLiveRegion(i0);
    }
}
