package android.support.v4.view;

import android.view.KeyEvent;
import android.view.KeyEvent$Callback;
import android.view.View;

class KeyEventCompat$BaseKeyEventVersionImpl implements android.support.v4.view.KeyEventCompat$KeyEventVersionImpl
{
    private static final int META_ALL_MASK = 247;
    private static final int META_MODIFIER_MASK = 247;

    KeyEventCompat$BaseKeyEventVersionImpl()
    {


        this.<init>();
    }

    public boolean dispatch(KeyEvent  r1, KeyEvent$Callback  r2, Object  r3, Object  r4)
    {


        return r1.dispatch(r2);
    }

    public Object getKeyDispatcherState(View  r1)
    {


        return null;
    }

    public boolean isTracking(KeyEvent  r1)
    {


        return false;
    }

    private static int metaStateFilterDirectionalModifiers(int  i0, int  i1, int  i2, int  i3, int  i4)
    {

        boolean z0, z2;
        int i6;
        z0 = true;

        if ((i1 & i2) == 0)
        {
            z2 = false;
        }
        else
        {
            z2 = true;
        }

        i6 = i3 | i4;

        if ((i1 & i6) == 0)
        {
            z0 = false;
        }

        if (z2 == false)
        {
            if (z0 != false)
            {
                i0 = i0 & (i2 ^ -1);
            }
        }
        else
        {
            if (z0 == false)
            {
                i0 = i0 & (i6 ^ -1);
            }
            else
            {
                throw new IllegalArgumentException("bad arguments");
            }
        }

        return i0;
    }

    public boolean metaStateHasModifiers(int  i0, int  i1)
    {

        boolean z0;
        z0 = true;

        if (KeyEventCompat$BaseKeyEventVersionImpl.metaStateFilterDirectionalModifiers(KeyEventCompat$BaseKeyEventVersionImpl.metaStateFilterDirectionalModifiers(this.normalizeMetaState(i0) + 247, i1, (int) 1, 64, 128), i1, 2, 16, 32) != i1)
        {
            z0 = false;
        }

        return z0;
    }

    public boolean metaStateHasNoModifiers(int  i0)
    {

        boolean z0;
        if (this.normalizeMetaState(i0) + 247 != 0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public int normalizeMetaState(int  i0)
    {


        if (i0 + 192 != 0)
        {
            i0 = i0 | 1;
        }

        if ((i0 & 48) != 0)
        {
            i0 = i0 | 2;
        }

        return i0 + 247;
    }

    public void startTracking(KeyEvent  r1)
    {

    }
}
