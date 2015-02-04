package android.support.v4.view;

import android.view.KeyEvent;
import android.view.KeyEvent$Callback;
import android.view.View;

class KeyEventCompatEclair
{

    KeyEventCompatEclair()
    {


        this.<init>();
    }

    public static boolean dispatch(KeyEvent  r0, KeyEvent$Callback  r1, Object  r2, Object  r3)
    {


        return r0.dispatch(r1, (KeyEvent$DispatcherState) r2, r3);
    }

    public static Object getKeyDispatcherState(View  r0)
    {


        return r0.getKeyDispatcherState();
    }

    public static boolean isTracking(KeyEvent  r0)
    {


        return r0.isTracking();
    }

    public static void startTracking(KeyEvent  r0)
    {


        r0.startTracking();
    }
}
