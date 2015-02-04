package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import java.util.List;

class AccessibilityManagerCompat$AccessibilityManagerIcsImpl extends AccessibilityManagerCompat$AccessibilityManagerStubImpl
{

    AccessibilityManagerCompat$AccessibilityManagerIcsImpl()
    {


        this.<init>();
    }

    public boolean addAccessibilityStateChangeListener(AccessibilityManager  r1, AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat  r2)
    {


        return AccessibilityManagerCompatIcs.addAccessibilityStateChangeListener(r1, r2.mListener);
    }

    public List getEnabledAccessibilityServiceList(AccessibilityManager  r1, int  i0)
    {


        return AccessibilityManagerCompatIcs.getEnabledAccessibilityServiceList(r1, i0);
    }

    public List getInstalledAccessibilityServiceList(AccessibilityManager  r1)
    {


        return AccessibilityManagerCompatIcs.getInstalledAccessibilityServiceList(r1);
    }

    public boolean isTouchExplorationEnabled(AccessibilityManager  r1)
    {


        return AccessibilityManagerCompatIcs.isTouchExplorationEnabled(r1);
    }

    public Object newAccessiblityStateChangeListener(AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat  r1)
    {


        return AccessibilityManagerCompatIcs.newAccessibilityStateChangeListener(new AccessibilityManagerCompat$AccessibilityManagerIcsImpl$1(this, r1));
    }

    public boolean removeAccessibilityStateChangeListener(AccessibilityManager  r1, AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat  r2)
    {


        return AccessibilityManagerCompatIcs.removeAccessibilityStateChangeListener(r1, r2.mListener);
    }
}
