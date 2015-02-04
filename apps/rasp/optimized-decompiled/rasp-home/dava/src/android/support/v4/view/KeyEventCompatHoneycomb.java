package android.support.v4.view;

import android.view.KeyEvent;

class KeyEventCompatHoneycomb
{

    KeyEventCompatHoneycomb()
    {


        this.<init>();
    }

    public static boolean metaStateHasModifiers(int  i0, int  i1)
    {


        return KeyEvent.metaStateHasModifiers(i0, i1);
    }

    public static boolean metaStateHasNoModifiers(int  i0)
    {


        return KeyEvent.metaStateHasNoModifiers(i0);
    }

    public static int normalizeMetaState(int  i0)
    {


        return KeyEvent.normalizeMetaState(i0);
    }
}
