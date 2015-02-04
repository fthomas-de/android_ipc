package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.KeyEvent;
import android.view.KeyEvent$Callback;
import android.view.View;

public class KeyEventCompat
{
    static final KeyEventCompat$KeyEventVersionImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 11)
        {
            IMPL = new KeyEventCompat$BaseKeyEventVersionImpl();
        }
        else
        {
            IMPL = new KeyEventCompat$HoneycombKeyEventVersionImpl();
        }
    }

    public KeyEventCompat()
    {


        this.<init>();
    }

    public static boolean dispatch(KeyEvent  r0, KeyEvent$Callback  r1, Object  r2, Object  r3)
    {


        return IMPL.dispatch(r0, r1, r2, r3);
    }

    public static Object getKeyDispatcherState(View  r0)
    {


        return IMPL.getKeyDispatcherState(r0);
    }

    public static boolean hasModifiers(KeyEvent  r0, int  i0)
    {


        return IMPL.metaStateHasModifiers(r0.getMetaState(), i0);
    }

    public static boolean hasNoModifiers(KeyEvent  r0)
    {


        return IMPL.metaStateHasNoModifiers(r0.getMetaState());
    }

    public static boolean isTracking(KeyEvent  r0)
    {


        return IMPL.isTracking(r0);
    }

    public static boolean metaStateHasModifiers(int  i0, int  i1)
    {


        return IMPL.metaStateHasModifiers(i0, i1);
    }

    public static boolean metaStateHasNoModifiers(int  i0)
    {


        return IMPL.metaStateHasNoModifiers(i0);
    }

    public static int normalizeMetaState(int  i0)
    {


        return IMPL.normalizeMetaState(i0);
    }

    public static void startTracking(KeyEvent  r0)
    {


        IMPL.startTracking(r0);
    }
}
