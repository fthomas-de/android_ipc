package android.support.v4.view;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup;
import android.os.Bundle;

public abstract interface AccessibilityDelegateCompatJellyBean$AccessibilityDelegateBridgeJellyBean
{

    public abstract boolean dispatchPopulateAccessibilityEvent(android.view.View  r0, android.view.accessibility.AccessibilityEvent  r1);

    public abstract java.lang.Object getAccessibilityNodeProvider(android.view.View  r0);

    public abstract void onInitializeAccessibilityEvent(android.view.View  r0, android.view.accessibility.AccessibilityEvent  r1);

    public abstract void onInitializeAccessibilityNodeInfo(android.view.View  r0, java.lang.Object  r1);

    public abstract void onPopulateAccessibilityEvent(android.view.View  r0, android.view.accessibility.AccessibilityEvent  r1);

    public abstract boolean onRequestSendAccessibilityEvent(android.view.ViewGroup  r0, android.view.View  r1, android.view.accessibility.AccessibilityEvent  r2);

    public abstract boolean performAccessibilityAction(android.view.View  r0, int  i1, android.os.Bundle  r2);

    public abstract void sendAccessibilityEvent(android.view.View  r0, int  i1);

    public abstract void sendAccessibilityEventUnchecked(android.view.View  r0, android.view.accessibility.AccessibilityEvent  r1);
}
