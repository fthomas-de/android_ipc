package android.support.v4.media;

import android.os.SystemClock;
import android.view.KeyEvent;

public abstract class TransportPerformer
{
    static final int AUDIOFOCUS_GAIN = 1;
    static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    static final int AUDIOFOCUS_LOSS = -1;
    static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;
    static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;

    public TransportPerformer()
    {


        this.<init>();
    }

    public void onAudioFocusChange(int  i0)
    {

        byte b1;
        long l2;
        b1 = (byte) (byte) 0;

        switch (i0)
        {
            case -1:
                b1 = (byte) (byte) 127;

            default:
                if (b1 != (byte) 0)
                {
                    l2 = SystemClock.uptimeMillis();
                    this.onMediaButtonDown(b1, new KeyEvent(l2, l2, 0, b1, 0));
                    this.onMediaButtonUp(b1, new KeyEvent(l2, l2, 1, b1, 0));
                }

                return;
        }
    }

    public int onGetBufferPercentage()
    {


        return 100;
    }

    public abstract long onGetCurrentPosition();

    public abstract long onGetDuration();

    public int onGetTransportControlFlags()
    {


        return 60;
    }

    public abstract boolean onIsPlaying();

    public boolean onMediaButtonDown(int  i0, KeyEvent  r1)
    {


        label_0:
        switch (i0)
        {
            case 79:
            case 85:
                if (this.onIsPlaying() == false)
                {
                    this.onStart();
                    break label_0;
                }
                else
                {
                    this.onPause();
                    break label_0;
                }

            case 86:
                this.onStop();
                break label_0;

            case 126:
                this.onStart();
                break label_0;

            case 127:
                this.onPause();
                break label_0;

            default:
                break label_0;
        }

        return true;
    }

    public boolean onMediaButtonUp(int  i0, KeyEvent  r1)
    {


        return true;
    }

    public abstract void onPause();

    public abstract void onSeekTo(long  l0);

    public abstract void onStart();

    public abstract void onStop();
}
