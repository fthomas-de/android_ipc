package android.support.v4.view.accessibility;

import android.os.Parcelable;
import java.util.List;
import android.view.View;

abstract interface AccessibilityRecordCompat$AccessibilityRecordImpl
{

    public abstract int getAddedCount(java.lang.Object  r0);

    public abstract java.lang.CharSequence getBeforeText(java.lang.Object  r0);

    public abstract java.lang.CharSequence getClassName(java.lang.Object  r0);

    public abstract java.lang.CharSequence getContentDescription(java.lang.Object  r0);

    public abstract int getCurrentItemIndex(java.lang.Object  r0);

    public abstract int getFromIndex(java.lang.Object  r0);

    public abstract int getItemCount(java.lang.Object  r0);

    public abstract int getMaxScrollX(java.lang.Object  r0);

    public abstract int getMaxScrollY(java.lang.Object  r0);

    public abstract android.os.Parcelable getParcelableData(java.lang.Object  r0);

    public abstract int getRemovedCount(java.lang.Object  r0);

    public abstract int getScrollX(java.lang.Object  r0);

    public abstract int getScrollY(java.lang.Object  r0);

    public abstract android.support.v4.view.accessibility.AccessibilityNodeInfoCompat getSource(java.lang.Object  r0);

    public abstract java.util.List getText(java.lang.Object  r0);

    public abstract int getToIndex(java.lang.Object  r0);

    public abstract int getWindowId(java.lang.Object  r0);

    public abstract boolean isChecked(java.lang.Object  r0);

    public abstract boolean isEnabled(java.lang.Object  r0);

    public abstract boolean isFullScreen(java.lang.Object  r0);

    public abstract boolean isPassword(java.lang.Object  r0);

    public abstract boolean isScrollable(java.lang.Object  r0);

    public abstract java.lang.Object obtain();

    public abstract java.lang.Object obtain(java.lang.Object  r0);

    public abstract void recycle(java.lang.Object  r0);

    public abstract void setAddedCount(java.lang.Object  r0, int  i1);

    public abstract void setBeforeText(java.lang.Object  r0, java.lang.CharSequence  r1);

    public abstract void setChecked(java.lang.Object  r0, boolean  z1);

    public abstract void setClassName(java.lang.Object  r0, java.lang.CharSequence  r1);

    public abstract void setContentDescription(java.lang.Object  r0, java.lang.CharSequence  r1);

    public abstract void setCurrentItemIndex(java.lang.Object  r0, int  i1);

    public abstract void setEnabled(java.lang.Object  r0, boolean  z1);

    public abstract void setFromIndex(java.lang.Object  r0, int  i1);

    public abstract void setFullScreen(java.lang.Object  r0, boolean  z1);

    public abstract void setItemCount(java.lang.Object  r0, int  i1);

    public abstract void setMaxScrollX(java.lang.Object  r0, int  i1);

    public abstract void setMaxScrollY(java.lang.Object  r0, int  i1);

    public abstract void setParcelableData(java.lang.Object  r0, android.os.Parcelable  r1);

    public abstract void setPassword(java.lang.Object  r0, boolean  z1);

    public abstract void setRemovedCount(java.lang.Object  r0, int  i1);

    public abstract void setScrollX(java.lang.Object  r0, int  i1);

    public abstract void setScrollY(java.lang.Object  r0, int  i1);

    public abstract void setScrollable(java.lang.Object  r0, boolean  z1);

    public abstract void setSource(java.lang.Object  r0, android.view.View  r1);

    public abstract void setSource(java.lang.Object  r0, android.view.View  r1, int  i2);

    public abstract void setToIndex(java.lang.Object  r0, int  i1);
}
