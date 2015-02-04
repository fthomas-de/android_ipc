package android.support.v4.media;


public abstract class TransportController
{

    public TransportController()
    {


        this.<init>();
    }

    public abstract int getBufferPercentage();

    public abstract long getCurrentPosition();

    public abstract long getDuration();

    public abstract int getTransportControlFlags();

    public abstract boolean isPlaying();

    public abstract void pausePlaying();

    public abstract void registerStateListener(android.support.v4.media.TransportStateListener  r0);

    public abstract void seekTo(long  l0);

    public abstract void startPlaying();

    public abstract void stopPlaying();

    public abstract void unregisterStateListener(android.support.v4.media.TransportStateListener  r0);
}
