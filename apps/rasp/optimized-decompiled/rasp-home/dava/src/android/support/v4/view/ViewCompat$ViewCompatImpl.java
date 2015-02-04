package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.os.Bundle;
import android.graphics.Paint;

abstract interface ViewCompat$ViewCompatImpl
{

    public abstract boolean canScrollHorizontally(android.view.View  r0, int  i1);

    public abstract boolean canScrollVertically(android.view.View  r0, int  i1);

    public abstract int getAccessibilityLiveRegion(android.view.View  r0);

    public abstract android.support.v4.view.accessibility.AccessibilityNodeProviderCompat getAccessibilityNodeProvider(android.view.View  r0);

    public abstract float getAlpha(android.view.View  r0);

    public abstract int getImportantForAccessibility(android.view.View  r0);

    public abstract int getLabelFor(android.view.View  r0);

    public abstract int getLayerType(android.view.View  r0);

    public abstract int getLayoutDirection(android.view.View  r0);

    public abstract int getMeasuredHeightAndState(android.view.View  r0);

    public abstract int getMeasuredState(android.view.View  r0);

    public abstract int getMeasuredWidthAndState(android.view.View  r0);

    public abstract int getOverScrollMode(android.view.View  r0);

    public abstract android.view.ViewParent getParentForAccessibility(android.view.View  r0);

    public abstract boolean hasTransientState(android.view.View  r0);

    public abstract boolean isOpaque(android.view.View  r0);

    public abstract void onInitializeAccessibilityEvent(android.view.View  r0, android.view.accessibility.AccessibilityEvent  r1);

    public abstract void onInitializeAccessibilityNodeInfo(android.view.View  r0, android.support.v4.view.accessibility.AccessibilityNodeInfoCompat  r1);

    public abstract void onPopulateAccessibilityEvent(android.view.View  r0, android.view.accessibility.AccessibilityEvent  r1);

    public abstract boolean performAccessibilityAction(android.view.View  r0, int  i1, android.os.Bundle  r2);

    public abstract void postInvalidateOnAnimation(android.view.View  r0);

    public abstract void postInvalidateOnAnimation(android.view.View  r0, int  i1, int  i2, int  i3, int  i4);

    public abstract void postOnAnimation(android.view.View  r0, java.lang.Runnable  r1);

    public abstract void postOnAnimationDelayed(android.view.View  r0, java.lang.Runnable  r1, long  l2);

    public abstract int resolveSizeAndState(int  i0, int  i1, int  i2);

    public abstract void setAccessibilityDelegate(android.view.View  r0, android.support.v4.view.AccessibilityDelegateCompat  r1);

    public abstract void setAccessibilityLiveRegion(android.view.View  r0, int  i1);

    public abstract void setHasTransientState(android.view.View  r0, boolean  z1);

    public abstract void setImportantForAccessibility(android.view.View  r0, int  i1);

    public abstract void setLabelFor(android.view.View  r0, int  i1);

    public abstract void setLayerPaint(android.view.View  r0, android.graphics.Paint  r1);

    public abstract void setLayerType(android.view.View  r0, int  i1, android.graphics.Paint  r2);

    public abstract void setLayoutDirection(android.view.View  r0, int  i1);

    public abstract void setOverScrollMode(android.view.View  r0, int  i1);
}
