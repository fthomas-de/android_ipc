package android.support.v4.view;

import android.view.KeyEvent;
import android.view.KeyEvent$Callback;
import android.view.View;

abstract interface KeyEventCompat$KeyEventVersionImpl
{

    public abstract boolean dispatch(android.view.KeyEvent  r0, android.view.KeyEvent$Callback  r1, java.lang.Object  r2, java.lang.Object  r3);

    public abstract java.lang.Object getKeyDispatcherState(android.view.View  r0);

    public abstract boolean isTracking(android.view.KeyEvent  r0);

    public abstract boolean metaStateHasModifiers(int  i0, int  i1);

    public abstract boolean metaStateHasNoModifiers(int  i0);

    public abstract int normalizeMetaState(int  i0);

    public abstract void startTracking(android.view.KeyEvent  r0);
}
