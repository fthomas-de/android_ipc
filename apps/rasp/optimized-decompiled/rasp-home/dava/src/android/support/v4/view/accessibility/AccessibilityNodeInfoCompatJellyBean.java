package android.support.v4.view.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.os.Bundle;

class AccessibilityNodeInfoCompatJellyBean
{

    AccessibilityNodeInfoCompatJellyBean()
    {


        this.<init>();
    }

    public static void addChild(Object  r0, View  r1, int  i0)
    {


        ((AccessibilityNodeInfo) r0).addChild(r1, i0);
    }

    public static Object findFocus(Object  r0, int  i0)
    {


        return ((AccessibilityNodeInfo) r0).findFocus(i0);
    }

    public static Object focusSearch(Object  r0, int  i0)
    {


        return ((AccessibilityNodeInfo) r0).focusSearch(i0);
    }

    public static int getMovementGranularities(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).getMovementGranularities();
    }

    public static boolean isAccessibilityFocused(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isAccessibilityFocused();
    }

    public static boolean isVisibleToUser(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isVisibleToUser();
    }

    public static Object obtain(View  r0, int  i0)
    {


        return AccessibilityNodeInfo.obtain(r0, i0);
    }

    public static boolean performAction(Object  r0, int  i0, Bundle  r1)
    {


        return ((AccessibilityNodeInfo) r0).performAction(i0, r1);
    }

    public static void setAccesibilityFocused(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setAccessibilityFocused(z0);
    }

    public static void setMovementGranularities(Object  r0, int  i0)
    {


        ((AccessibilityNodeInfo) r0).setMovementGranularities(i0);
    }

    public static void setParent(Object  r0, View  r1, int  i0)
    {


        ((AccessibilityNodeInfo) r0).setParent(r1, i0);
    }

    public static void setSource(Object  r0, View  r1, int  i0)
    {


        ((AccessibilityNodeInfo) r0).setSource(r1, i0);
    }

    public static void setVisibleToUser(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setVisibleToUser(z0);
    }
}
