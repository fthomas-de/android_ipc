package android.support.v4.media;

import android.media.RemoteControlClient$OnGetPlaybackPositionListener;
import android.media.RemoteControlClient$OnPlaybackPositionUpdateListener;
import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.ViewTreeObserver;
import android.media.RemoteControlClient;
import android.app.PendingIntent;
import android.media.AudioManager$OnAudioFocusChangeListener;
import android.content.BroadcastReceiver;
import android.view.ViewTreeObserver$OnWindowAttachListener;
import android.view.ViewTreeObserver$OnWindowFocusChangeListener;

class TransportMediatorJellybeanMR2 implements android.media.RemoteControlClient$OnGetPlaybackPositionListener, android.media.RemoteControlClient$OnPlaybackPositionUpdateListener
{
    AudioManager$OnAudioFocusChangeListener mAudioFocusChangeListener;
    boolean mAudioFocused;
    final AudioManager mAudioManager;
    final Context mContext;
    boolean mFocused;
    final Intent mIntent;
    final BroadcastReceiver mMediaButtonReceiver;
    PendingIntent mPendingIntent;
    int mPlayState;
    final String mReceiverAction;
    final IntentFilter mReceiverFilter;
    RemoteControlClient mRemoteControl;
    final View mTargetView;
    final TransportMediatorCallback mTransportCallback;
    final ViewTreeObserver$OnWindowAttachListener mWindowAttachListener;
    final ViewTreeObserver$OnWindowFocusChangeListener mWindowFocusListener;

    public TransportMediatorJellybeanMR2(Context  r1, AudioManager  r2, View  r3, TransportMediatorCallback  r4)
    {


        this.<init>();
        mWindowAttachListener = (ViewTreeObserver$OnWindowAttachListener) new TransportMediatorJellybeanMR2$1(this);
        mWindowFocusListener = (ViewTreeObserver$OnWindowFocusChangeListener) new TransportMediatorJellybeanMR2$2(this);
        mMediaButtonReceiver = new TransportMediatorJellybeanMR2$3(this);
        mAudioFocusChangeListener = new TransportMediatorJellybeanMR2$4(this);
        mPlayState = 0;
        mContext = r1;
        mAudioManager = r2;
        mTargetView = r3;
        mTransportCallback = r4;
        mReceiverAction = (new StringBuilder()).append(r1.getPackageName()).append(":transport:").append(System.identityHashCode(this)).toString();
        mIntent = new Intent(mReceiverAction);
        mIntent.setPackage(r1.getPackageName());
        mReceiverFilter = new IntentFilter();
        mReceiverFilter.addAction(mReceiverAction);
        mTargetView.getViewTreeObserver().addOnWindowAttachListener(mWindowAttachListener);
        mTargetView.getViewTreeObserver().addOnWindowFocusChangeListener(mWindowFocusListener);
    }

    public void destroy()
    {


        this.windowDetached();
        mTargetView.getViewTreeObserver().removeOnWindowAttachListener(mWindowAttachListener);
        mTargetView.getViewTreeObserver().removeOnWindowFocusChangeListener(mWindowFocusListener);
    }

    void dropAudioFocus()
    {


        if (mAudioFocused != false)
        {
            mAudioFocused = false;
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }

    void gainFocus()
    {


        if (mFocused == false)
        {
            mFocused = true;
            mAudioManager.registerMediaButtonEventReceiver(mPendingIntent);
            mAudioManager.registerRemoteControlClient(mRemoteControl);

            if (mPlayState == 3)
            {
                this.takeAudioFocus();
            }
        }
    }

    public Object getRemoteControlClient()
    {


        return mRemoteControl;
    }

    void loseFocus()
    {


        this.dropAudioFocus();

        if (mFocused != false)
        {
            mFocused = false;
            mAudioManager.unregisterRemoteControlClient(mRemoteControl);
            mAudioManager.unregisterMediaButtonEventReceiver(mPendingIntent);
        }
    }

    public long onGetPlaybackPosition()
    {


        return mTransportCallback.getPlaybackPosition();
    }

    public void onPlaybackPositionUpdate(long  l0)
    {


        mTransportCallback.playbackPositionUpdate(l0);
    }

    public void pausePlaying()
    {


        if (mPlayState == 3)
        {
            mPlayState = 2;
            mRemoteControl.setPlaybackState(2);
        }

        this.dropAudioFocus();
    }

    public void refreshState(boolean  z0, long  l0, int  i1)
    {

        RemoteControlClient r2;
        byte b2;
        float f0;
        if (mRemoteControl != null)
        {
            r2 = mRemoteControl;

            if (z0 == false)
            {
                b2 = (byte) (byte) 1;
            }
            else
            {
                b2 = (byte) (byte) 3;
            }

            if (z0 == false)
            {
                f0 = 0.0F;
            }
            else
            {
                f0 = 1.0F;
            }

            r2.setPlaybackState(b2, l0, f0);
            mRemoteControl.setTransportControlFlags(i1);
        }
    }

    public void startPlaying()
    {


        if (mPlayState != 3)
        {
            mPlayState = 3;
            mRemoteControl.setPlaybackState(3);
        }

        if (mFocused != false)
        {
            this.takeAudioFocus();
        }
    }

    public void stopPlaying()
    {


        if (mPlayState != 1)
        {
            mPlayState = 1;
            mRemoteControl.setPlaybackState(1);
        }

        this.dropAudioFocus();
    }

    void takeAudioFocus()
    {


        if (mAudioFocused == false)
        {
            mAudioFocused = true;
            mAudioManager.requestAudioFocus(mAudioFocusChangeListener, 3, (int) 1);
        }
    }

    void windowAttached()
    {


        mContext.registerReceiver(mMediaButtonReceiver, mReceiverFilter);
        mPendingIntent = PendingIntent.getBroadcast(mContext, 0, mIntent, 268435456);
        mRemoteControl = new RemoteControlClient(mPendingIntent);
        mRemoteControl.setOnGetPlaybackPositionListener((RemoteControlClient$OnGetPlaybackPositionListener) this);
        mRemoteControl.setPlaybackPositionUpdateListener((RemoteControlClient$OnPlaybackPositionUpdateListener) this);
    }

    void windowDetached()
    {

        Object n0;
        n0 = null;
        this.loseFocus();

        if (mPendingIntent != null)
        {
            mContext.unregisterReceiver(mMediaButtonReceiver);
            mPendingIntent.cancel();
            mPendingIntent = n0;
            mRemoteControl = n0;
        }
    }
}
