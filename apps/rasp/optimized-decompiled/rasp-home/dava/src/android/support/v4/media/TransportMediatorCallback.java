package android.support.v4.media;

import android.view.KeyEvent;

abstract interface TransportMediatorCallback
{

    public abstract long getPlaybackPosition();

    public abstract void handleAudioFocusChange(int  i0);

    public abstract void handleKey(android.view.KeyEvent  r0);

    public abstract void playbackPositionUpdate(long  l0);
}
