package android.support.v4.view.accessibility;


class AccessibilityManagerCompat$AccessibilityManagerIcsImpl$1 implements android.support.v4.view.accessibility.AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge
{
    final AccessibilityManagerCompat$AccessibilityManagerIcsImpl this$0;
    final AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat val$listener;

    AccessibilityManagerCompat$AccessibilityManagerIcsImpl$1(AccessibilityManagerCompat$AccessibilityManagerIcsImpl  r1, AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat  r2)
    {


        this$0 = r1;
        val$listener = r2;
        this.<init>();
    }

    public void onAccessibilityStateChanged(boolean  z0)
    {


        val$listener.onAccessibilityStateChanged(z0);
    }
}
