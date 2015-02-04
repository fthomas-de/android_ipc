package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener;

final class AccessibilityManagerCompatIcs$1 implements android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener
{
    final AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge val$bridge;

    AccessibilityManagerCompatIcs$1(AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge  r1)
    {


        val$bridge = r1;
        this.<init>();
    }

    public void onAccessibilityStateChanged(boolean  z0)
    {


        val$bridge.onAccessibilityStateChanged(z0);
    }
}
