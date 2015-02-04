package android.support.v4.media;

import android.view.KeyEvent;

class TransportMediator$1 implements android.support.v4.media.TransportMediatorCallback
{
    final TransportMediator this$0;

    TransportMediator$1(TransportMediator  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public long getPlaybackPosition()
    {


        return this$0.mCallbacks.onGetCurrentPosition();
    }

    public void handleAudioFocusChange(int  i0)
    {


        this$0.mCallbacks.onAudioFocusChange(i0);
    }

    public void handleKey(KeyEvent  r1)
    {


        r1.dispatch(this$0.mKeyEventCallback);
    }

    public void playbackPositionUpdate(long  l0)
    {


        this$0.mCallbacks.onSeekTo(l0);
    }
}
