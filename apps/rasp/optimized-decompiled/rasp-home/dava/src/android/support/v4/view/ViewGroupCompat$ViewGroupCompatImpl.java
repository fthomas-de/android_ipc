package android.support.v4.view;

import android.view.ViewGroup;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

abstract interface ViewGroupCompat$ViewGroupCompatImpl
{

    public abstract int getLayoutMode(android.view.ViewGroup  r0);

    public abstract boolean onRequestSendAccessibilityEvent(android.view.ViewGroup  r0, android.view.View  r1, android.view.accessibility.AccessibilityEvent  r2);

    public abstract void setLayoutMode(android.view.ViewGroup  r0, int  i1);

    public abstract void setMotionEventSplittingEnabled(android.view.ViewGroup  r0, boolean  z1);
}
