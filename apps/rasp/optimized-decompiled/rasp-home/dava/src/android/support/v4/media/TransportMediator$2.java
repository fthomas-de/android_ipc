package android.support.v4.media;

import android.view.KeyEvent$Callback;
import android.view.KeyEvent;

class TransportMediator$2 implements android.view.KeyEvent$Callback
{
    final TransportMediator this$0;

    TransportMediator$2(TransportMediator  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public boolean onKeyDown(int  i0, KeyEvent  r1)
    {

        boolean z1;
        if (TransportMediator.isMediaKey(i0) == false)
        {
            z1 = false;
        }
        else
        {
            z1 = this$0.mCallbacks.onMediaButtonDown(i0, r1);
        }

        return z1;
    }

    public boolean onKeyLongPress(int  i0, KeyEvent  r1)
    {


        return false;
    }

    public boolean onKeyMultiple(int  i0, int  i1, KeyEvent  r1)
    {


        return false;
    }

    public boolean onKeyUp(int  i0, KeyEvent  r1)
    {

        boolean z1;
        if (TransportMediator.isMediaKey(i0) == false)
        {
            z1 = false;
        }
        else
        {
            z1 = this$0.mCallbacks.onMediaButtonUp(i0, r1);
        }

        return z1;
    }
}
