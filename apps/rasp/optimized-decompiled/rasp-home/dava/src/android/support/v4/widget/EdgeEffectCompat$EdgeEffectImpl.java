package android.support.v4.widget;

import android.graphics.Canvas;
import android.content.Context;

abstract interface EdgeEffectCompat$EdgeEffectImpl
{

    public abstract boolean draw(java.lang.Object  r0, android.graphics.Canvas  r1);

    public abstract void finish(java.lang.Object  r0);

    public abstract boolean isFinished(java.lang.Object  r0);

    public abstract java.lang.Object newEdgeEffect(android.content.Context  r0);

    public abstract boolean onAbsorb(java.lang.Object  r0, int  i1);

    public abstract boolean onPull(java.lang.Object  r0, float  f1);

    public abstract boolean onRelease(java.lang.Object  r0);

    public abstract void setSize(java.lang.Object  r0, int  i1, int  i2);
}
