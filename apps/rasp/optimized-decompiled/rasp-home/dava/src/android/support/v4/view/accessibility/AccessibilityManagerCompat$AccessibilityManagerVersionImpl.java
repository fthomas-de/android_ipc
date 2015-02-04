package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import java.util.List;

abstract interface AccessibilityManagerCompat$AccessibilityManagerVersionImpl
{

    public abstract boolean addAccessibilityStateChangeListener(android.view.accessibility.AccessibilityManager  r0, android.support.v4.view.accessibility.AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat  r1);

    public abstract java.util.List getEnabledAccessibilityServiceList(android.view.accessibility.AccessibilityManager  r0, int  i1);

    public abstract java.util.List getInstalledAccessibilityServiceList(android.view.accessibility.AccessibilityManager  r0);

    public abstract boolean isTouchExplorationEnabled(android.view.accessibility.AccessibilityManager  r0);

    public abstract java.lang.Object newAccessiblityStateChangeListener(android.support.v4.view.accessibility.AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat  r0);

    public abstract boolean removeAccessibilityStateChangeListener(android.view.accessibility.AccessibilityManager  r0, android.support.v4.view.accessibility.AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat  r1);
}
