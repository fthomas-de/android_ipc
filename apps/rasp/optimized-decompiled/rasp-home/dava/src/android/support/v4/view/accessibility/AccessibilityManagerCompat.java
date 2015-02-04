package android.support.v4.view.accessibility;

import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityManager;
import java.util.List;

public class AccessibilityManagerCompat
{
    private static final AccessibilityManagerCompat$AccessibilityManagerVersionImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 14)
        {
            IMPL = new AccessibilityManagerCompat$AccessibilityManagerStubImpl();
        }
        else
        {
            IMPL = new AccessibilityManagerCompat$AccessibilityManagerIcsImpl();
        }
    }

    public AccessibilityManagerCompat()
    {


        this.<init>();
    }

    static AccessibilityManagerCompat$AccessibilityManagerVersionImpl access$000()
    {


        return IMPL;
    }

    public static boolean addAccessibilityStateChangeListener(AccessibilityManager  r0, AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat  r1)
    {


        return IMPL.addAccessibilityStateChangeListener(r0, r1);
    }

    public static List getEnabledAccessibilityServiceList(AccessibilityManager  r0, int  i0)
    {


        return IMPL.getEnabledAccessibilityServiceList(r0, i0);
    }

    public static List getInstalledAccessibilityServiceList(AccessibilityManager  r0)
    {


        return IMPL.getInstalledAccessibilityServiceList(r0);
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager  r0)
    {


        return IMPL.isTouchExplorationEnabled(r0);
    }

    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager  r0, AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat  r1)
    {


        return IMPL.removeAccessibilityStateChangeListener(r0, r1);
    }
}
