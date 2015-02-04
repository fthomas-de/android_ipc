package android.support.v4.view;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;

class ViewCompatICS
{

    ViewCompatICS()
    {


        this.<init>();
    }

    public static boolean canScrollHorizontally(View  r0, int  i0)
    {


        return r0.canScrollHorizontally(i0);
    }

    public static boolean canScrollVertically(View  r0, int  i0)
    {


        return r0.canScrollVertically(i0);
    }

    public static void onInitializeAccessibilityEvent(View  r0, AccessibilityEvent  r1)
    {


        r0.onInitializeAccessibilityEvent(r1);
    }

    public static void onInitializeAccessibilityNodeInfo(View  r0, Object  r1)
    {


        r0.onInitializeAccessibilityNodeInfo((AccessibilityNodeInfo) r1);
    }

    public static void onPopulateAccessibilityEvent(View  r0, AccessibilityEvent  r1)
    {


        r0.onPopulateAccessibilityEvent(r1);
    }

    public static void setAccessibilityDelegate(View  r0, Object  r1)
    {


        r0.setAccessibilityDelegate((View$AccessibilityDelegate) r1);
    }
}
