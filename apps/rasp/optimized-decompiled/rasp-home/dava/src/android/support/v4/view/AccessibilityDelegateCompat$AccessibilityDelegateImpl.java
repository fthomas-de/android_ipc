package android.support.v4.view;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup;
import android.os.Bundle;

abstract interface AccessibilityDelegateCompat$AccessibilityDelegateImpl
{

    public abstract boolean dispatchPopulateAccessibilityEvent(java.lang.Object  r0, android.view.View  r1, android.view.accessibility.AccessibilityEvent  r2);

    public abstract android.support.v4.view.accessibility.AccessibilityNodeProviderCompat getAccessibilityNodeProvider(java.lang.Object  r0, android.view.View  r1);

    public abstract java.lang.Object newAccessiblityDelegateBridge(android.support.v4.view.AccessibilityDelegateCompat  r0);

    public abstract java.lang.Object newAccessiblityDelegateDefaultImpl();

    public abstract void onInitializeAccessibilityEvent(java.lang.Object  r0, android.view.View  r1, android.view.accessibility.AccessibilityEvent  r2);

    public abstract void onInitializeAccessibilityNodeInfo(java.lang.Object  r0, android.view.View  r1, android.support.v4.view.accessibility.AccessibilityNodeInfoCompat  r2);

    public abstract void onPopulateAccessibilityEvent(java.lang.Object  r0, android.view.View  r1, android.view.accessibility.AccessibilityEvent  r2);

    public abstract boolean onRequestSendAccessibilityEvent(java.lang.Object  r0, android.view.ViewGroup  r1, android.view.View  r2, android.view.accessibility.AccessibilityEvent  r3);

    public abstract boolean performAccessibilityAction(java.lang.Object  r0, android.view.View  r1, int  i2, android.os.Bundle  r3);

    public abstract void sendAccessibilityEvent(java.lang.Object  r0, android.view.View  r1, int  i2);

    public abstract void sendAccessibilityEventUnchecked(java.lang.Object  r0, android.view.View  r1, android.view.accessibility.AccessibilityEvent  r2);
}
