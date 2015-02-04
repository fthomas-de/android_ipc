package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;

abstract interface ScrollerCompat$ScrollerCompatImpl
{

    public abstract void abortAnimation(java.lang.Object  r0);

    public abstract boolean computeScrollOffset(java.lang.Object  r0);

    public abstract java.lang.Object createScroller(android.content.Context  r0, android.view.animation.Interpolator  r1);

    public abstract void fling(java.lang.Object  r0, int  i1, int  i2, int  i3, int  i4, int  i5, int  i6, int  i7, int  i8);

    public abstract void fling(java.lang.Object  r0, int  i1, int  i2, int  i3, int  i4, int  i5, int  i6, int  i7, int  i8, int  i9, int  i10);

    public abstract float getCurrVelocity(java.lang.Object  r0);

    public abstract int getCurrX(java.lang.Object  r0);

    public abstract int getCurrY(java.lang.Object  r0);

    public abstract int getFinalX(java.lang.Object  r0);

    public abstract int getFinalY(java.lang.Object  r0);

    public abstract boolean isFinished(java.lang.Object  r0);

    public abstract boolean isOverScrolled(java.lang.Object  r0);

    public abstract void notifyHorizontalEdgeReached(java.lang.Object  r0, int  i1, int  i2, int  i3);

    public abstract void notifyVerticalEdgeReached(java.lang.Object  r0, int  i1, int  i2, int  i3);

    public abstract void startScroll(java.lang.Object  r0, int  i1, int  i2, int  i3, int  i4);

    public abstract void startScroll(java.lang.Object  r0, int  i1, int  i2, int  i3, int  i4, int  i5);
}
