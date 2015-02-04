package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import java.util.List;

class AccessibilityManagerCompatIcs
{

    AccessibilityManagerCompatIcs()
    {


        this.<init>();
    }

    public static boolean addAccessibilityStateChangeListener(AccessibilityManager  r0, Object  r1)
    {


        return r0.addAccessibilityStateChangeListener((AccessibilityManager$AccessibilityStateChangeListener) r1);
    }

    public static List getEnabledAccessibilityServiceList(AccessibilityManager  r0, int  i0)
    {


        return r0.getEnabledAccessibilityServiceList(i0);
    }

    public static List getInstalledAccessibilityServiceList(AccessibilityManager  r0)
    {


        return r0.getInstalledAccessibilityServiceList();
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager  r0)
    {


        return r0.isTouchExplorationEnabled();
    }

    public static Object newAccessibilityStateChangeListener(AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge  r0)
    {


        return new AccessibilityManagerCompatIcs$1(r0);
    }

    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager  r0, Object  r1)
    {


        return r0.removeAccessibilityStateChangeListener((AccessibilityManager$AccessibilityStateChangeListener) r1);
    }
}
