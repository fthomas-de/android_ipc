package android.support.v4.media;

import android.view.ViewTreeObserver$OnWindowFocusChangeListener;

class TransportMediatorJellybeanMR2$2 implements android.view.ViewTreeObserver$OnWindowFocusChangeListener
{
    final TransportMediatorJellybeanMR2 this$0;

    TransportMediatorJellybeanMR2$2(TransportMediatorJellybeanMR2  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void onWindowFocusChanged(boolean  z0)
    {


        if (z0 == false)
        {
            this$0.loseFocus();
        }
        else
        {
            this$0.gainFocus();
        }
    }
}
