package android.support.v4.media;

import android.media.AudioManager$OnAudioFocusChangeListener;

class TransportMediatorJellybeanMR2$4 implements android.media.AudioManager$OnAudioFocusChangeListener
{
    final TransportMediatorJellybeanMR2 this$0;

    TransportMediatorJellybeanMR2$4(TransportMediatorJellybeanMR2  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void onAudioFocusChange(int  i0)
    {


        this$0.mTransportCallback.handleAudioFocusChange(i0);
    }
}
