package android.support.v4.media;

import android.app.Activity;
import android.view.View;
import android.content.Context;
import java.util.ArrayList;
import android.view.Window;
import android.support.v4.view.KeyEventCompat;
import android.os.Build$VERSION;
import android.view.KeyEvent;
import android.media.AudioManager;
import android.view.KeyEvent$Callback;

public class TransportMediator extends TransportController
{
    public static final int FLAG_KEY_MEDIA_FAST_FORWARD = 64;
    public static final int FLAG_KEY_MEDIA_NEXT = 128;
    public static final int FLAG_KEY_MEDIA_PAUSE = 16;
    public static final int FLAG_KEY_MEDIA_PLAY = 4;
    public static final int FLAG_KEY_MEDIA_PLAY_PAUSE = 8;
    public static final int FLAG_KEY_MEDIA_PREVIOUS = 1;
    public static final int FLAG_KEY_MEDIA_REWIND = 2;
    public static final int FLAG_KEY_MEDIA_STOP = 32;
    public static final int KEYCODE_MEDIA_PAUSE = 127;
    public static final int KEYCODE_MEDIA_PLAY = 126;
    public static final int KEYCODE_MEDIA_RECORD = 130;
    final AudioManager mAudioManager;
    final TransportPerformer mCallbacks;
    final Context mContext;
    final TransportMediatorJellybeanMR2 mController;
    final Object mDispatcherState;
    final KeyEvent$Callback mKeyEventCallback;
    final ArrayList mListeners;
    final TransportMediatorCallback mTransportKeyCallback;
    final View mView;

    public TransportMediator(Activity  r1, TransportPerformer  r2)
    {
        this(r1, null, r2);


        this.<init>(r1, null, r2);
    }

    private TransportMediator(Activity  r1, View  r2, TransportPerformer  r3)
    {

        Context r11;
        this.<init>();
        mListeners = new ArrayList();
        mTransportKeyCallback = new TransportMediator$1(this);
        mKeyEventCallback = new TransportMediator$2(this);

        if (r1 == null)
        {
            r11 = r2.getContext();
        }
        else
        {
            r11 = r1;
        }

        mContext = r11;
        mCallbacks = r3;
        mAudioManager = (AudioManager) mContext.getSystemService("audio");

        if (r1 != null)
        {
            r2 = r1.getWindow().getDecorView();
        }

        mView = r2;
        mDispatcherState = KeyEventCompat.getKeyDispatcherState(mView);

        if (Build$VERSION.SDK_INT < 18)
        {
            mController = null;
        }
        else
        {
            mController = new TransportMediatorJellybeanMR2(mContext, mAudioManager, mView, mTransportKeyCallback);
        }
    }

    public TransportMediator(View  r1, TransportPerformer  r2)
    {
        this(null, r1, r2);


        this.<init>(null, r1, r2);
    }

    public void destroy()
    {


        mController.destroy();
    }

    public boolean dispatchKeyEvent(KeyEvent  r1)
    {


        return KeyEventCompat.dispatch(r1, mKeyEventCallback, mDispatcherState, this);
    }

    public int getBufferPercentage()
    {


        return mCallbacks.onGetBufferPercentage();
    }

    public long getCurrentPosition()
    {


        return mCallbacks.onGetCurrentPosition();
    }

    public long getDuration()
    {


        return mCallbacks.onGetDuration();
    }

    private TransportStateListener[] getListeners()
    {

        TransportStateListener[] r2;
        if (mListeners.size() > 0)
        {
            r2 = new TransportStateListener[mListeners.size()];
            mListeners.toArray(r2);
        }
        else
        {
            r2 = null;
        }

        return r2;
    }

    public Object getRemoteControlClient()
    {

        Object r3;
        if (mController == null)
        {
            r3 = null;
        }
        else
        {
            r3 = mController.getRemoteControlClient();
        }

        return r3;
    }

    public int getTransportControlFlags()
    {


        return mCallbacks.onGetTransportControlFlags();
    }

    static boolean isMediaKey(int  i0)
    {

        boolean z0;
        label_0:
        switch (i0)
        {
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 126:
            case 127:
            case 130:
                z0 = true;
                break label_0;

            default:
                z0 = false;
                break label_0;
        }

        return z0;
    }

    public boolean isPlaying()
    {


        return mCallbacks.onIsPlaying();
    }

    public void pausePlaying()
    {


        if (mController != null)
        {
            mController.pausePlaying();
        }

        mCallbacks.onPause();
        this.pushControllerState();
        this.reportPlayingChanged();
    }

    private void pushControllerState()
    {


        if (mController != null)
        {
            mController.refreshState(mCallbacks.onIsPlaying(), mCallbacks.onGetCurrentPosition(), mCallbacks.onGetTransportControlFlags());
        }
    }

    public void refreshState()
    {


        this.pushControllerState();
        this.reportPlayingChanged();
        this.reportTransportControlsChanged();
    }

    public void registerStateListener(TransportStateListener  r1)
    {


        mListeners.add(r1);
    }

    private void reportPlayingChanged()
    {

        TransportStateListener[] r1;
        int i0, i1;
        r1 = this.getListeners();

        if (r1 != null)
        {
            i0 = r1.length;
            i1 = 0;

            while (i1 < i0)
            {
                r1[i1].onPlayingChanged(this);
                i1 = i1 + 1;
            }
        }
    }

    private void reportTransportControlsChanged()
    {

        TransportStateListener[] r1;
        int i0, i1;
        r1 = this.getListeners();

        if (r1 != null)
        {
            i0 = r1.length;
            i1 = 0;

            while (i1 < i0)
            {
                r1[i1].onTransportControlsChanged(this);
                i1 = i1 + 1;
            }
        }
    }

    public void seekTo(long  l0)
    {


        mCallbacks.onSeekTo(l0);
    }

    public void startPlaying()
    {


        if (mController != null)
        {
            mController.startPlaying();
        }

        mCallbacks.onStart();
        this.pushControllerState();
        this.reportPlayingChanged();
    }

    public void stopPlaying()
    {


        if (mController != null)
        {
            mController.stopPlaying();
        }

        mCallbacks.onStop();
        this.pushControllerState();
        this.reportPlayingChanged();
    }

    public void unregisterStateListener(TransportStateListener  r1)
    {


        mListeners.remove(r1);
    }
}
