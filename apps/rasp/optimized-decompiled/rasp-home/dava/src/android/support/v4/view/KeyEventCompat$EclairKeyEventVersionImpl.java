package android.support.v4.view;

import android.view.KeyEvent;
import android.view.KeyEvent$Callback;
import android.view.View;

class KeyEventCompat$EclairKeyEventVersionImpl extends KeyEventCompat$BaseKeyEventVersionImpl
{

    KeyEventCompat$EclairKeyEventVersionImpl()
    {


        this.<init>();
    }

    public boolean dispatch(KeyEvent  r1, KeyEvent$Callback  r2, Object  r3, Object  r4)
    {


        return KeyEventCompatEclair.dispatch(r1, r2, r3, r4);
    }

    public Object getKeyDispatcherState(View  r1)
    {


        return KeyEventCompatEclair.getKeyDispatcherState(r1);
    }

    public boolean isTracking(KeyEvent  r1)
    {


        return KeyEventCompatEclair.isTracking(r1);
    }

    public void startTracking(KeyEvent  r1)
    {


        KeyEventCompatEclair.startTracking(r1);
    }
}
