package android.support.v4.net;

import java.net.SocketException;
import java.net.Socket;

abstract interface TrafficStatsCompat$TrafficStatsCompatImpl
{

    public abstract void clearThreadStatsTag();

    public abstract int getThreadStatsTag();

    public abstract void incrementOperationCount(int  i0);

    public abstract void incrementOperationCount(int  i0, int  i1);

    public abstract void setThreadStatsTag(int  i0);

    public abstract void tagSocket(java.net.Socket  r0) throws java.net.SocketException;

    public abstract void untagSocket(java.net.Socket  r0) throws java.net.SocketException;
}
