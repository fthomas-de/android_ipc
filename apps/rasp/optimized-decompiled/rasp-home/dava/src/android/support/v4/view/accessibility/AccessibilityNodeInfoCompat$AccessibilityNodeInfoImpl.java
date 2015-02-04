package android.support.v4.view.accessibility;

import android.view.View;
import java.util.List;
import android.graphics.Rect;
import android.os.Bundle;

abstract interface AccessibilityNodeInfoCompat$AccessibilityNodeInfoImpl
{

    public abstract void addAction(java.lang.Object  r0, int  i1);

    public abstract void addChild(java.lang.Object  r0, android.view.View  r1);

    public abstract void addChild(java.lang.Object  r0, android.view.View  r1, int  i2);

    public abstract java.util.List findAccessibilityNodeInfosByText(java.lang.Object  r0, java.lang.String  r1);

    public abstract java.lang.Object findFocus(java.lang.Object  r0, int  i1);

    public abstract java.lang.Object focusSearch(java.lang.Object  r0, int  i1);

    public abstract int getActions(java.lang.Object  r0);

    public abstract void getBoundsInParent(java.lang.Object  r0, android.graphics.Rect  r1);

    public abstract void getBoundsInScreen(java.lang.Object  r0, android.graphics.Rect  r1);

    public abstract java.lang.Object getChild(java.lang.Object  r0, int  i1);

    public abstract int getChildCount(java.lang.Object  r0);

    public abstract java.lang.CharSequence getClassName(java.lang.Object  r0);

    public abstract java.lang.CharSequence getContentDescription(java.lang.Object  r0);

    public abstract int getLiveRegion(java.lang.Object  r0);

    public abstract int getMovementGranularities(java.lang.Object  r0);

    public abstract java.lang.CharSequence getPackageName(java.lang.Object  r0);

    public abstract java.lang.Object getParent(java.lang.Object  r0);

    public abstract java.lang.CharSequence getText(java.lang.Object  r0);

    public abstract java.lang.String getViewIdResourceName(java.lang.Object  r0);

    public abstract int getWindowId(java.lang.Object  r0);

    public abstract boolean isAccessibilityFocused(java.lang.Object  r0);

    public abstract boolean isCheckable(java.lang.Object  r0);

    public abstract boolean isChecked(java.lang.Object  r0);

    public abstract boolean isClickable(java.lang.Object  r0);

    public abstract boolean isEnabled(java.lang.Object  r0);

    public abstract boolean isFocusable(java.lang.Object  r0);

    public abstract boolean isFocused(java.lang.Object  r0);

    public abstract boolean isLongClickable(java.lang.Object  r0);

    public abstract boolean isPassword(java.lang.Object  r0);

    public abstract boolean isScrollable(java.lang.Object  r0);

    public abstract boolean isSelected(java.lang.Object  r0);

    public abstract boolean isVisibleToUser(java.lang.Object  r0);

    public abstract java.lang.Object obtain();

    public abstract java.lang.Object obtain(android.view.View  r0);

    public abstract java.lang.Object obtain(android.view.View  r0, int  i1);

    public abstract java.lang.Object obtain(java.lang.Object  r0);

    public abstract boolean performAction(java.lang.Object  r0, int  i1);

    public abstract boolean performAction(java.lang.Object  r0, int  i1, android.os.Bundle  r2);

    public abstract void recycle(java.lang.Object  r0);

    public abstract void setAccessibilityFocused(java.lang.Object  r0, boolean  z1);

    public abstract void setBoundsInParent(java.lang.Object  r0, android.graphics.Rect  r1);

    public abstract void setBoundsInScreen(java.lang.Object  r0, android.graphics.Rect  r1);

    public abstract void setCheckable(java.lang.Object  r0, boolean  z1);

    public abstract void setChecked(java.lang.Object  r0, boolean  z1);

    public abstract void setClassName(java.lang.Object  r0, java.lang.CharSequence  r1);

    public abstract void setClickable(java.lang.Object  r0, boolean  z1);

    public abstract void setContentDescription(java.lang.Object  r0, java.lang.CharSequence  r1);

    public abstract void setEnabled(java.lang.Object  r0, boolean  z1);

    public abstract void setFocusable(java.lang.Object  r0, boolean  z1);

    public abstract void setFocused(java.lang.Object  r0, boolean  z1);

    public abstract void setLiveRegion(java.lang.Object  r0, int  i1);

    public abstract void setLongClickable(java.lang.Object  r0, boolean  z1);

    public abstract void setMovementGranularities(java.lang.Object  r0, int  i1);

    public abstract void setPackageName(java.lang.Object  r0, java.lang.CharSequence  r1);

    public abstract void setParent(java.lang.Object  r0, android.view.View  r1);

    public abstract void setParent(java.lang.Object  r0, android.view.View  r1, int  i2);

    public abstract void setPassword(java.lang.Object  r0, boolean  z1);

    public abstract void setScrollable(java.lang.Object  r0, boolean  z1);

    public abstract void setSelected(java.lang.Object  r0, boolean  z1);

    public abstract void setSource(java.lang.Object  r0, android.view.View  r1);

    public abstract void setSource(java.lang.Object  r0, android.view.View  r1, int  i2);

    public abstract void setText(java.lang.Object  r0, java.lang.CharSequence  r1);

    public abstract void setViewIdResourceName(java.lang.Object  r0, java.lang.String  r1);

    public abstract void setVisibleToUser(java.lang.Object  r0, boolean  z1);
}
