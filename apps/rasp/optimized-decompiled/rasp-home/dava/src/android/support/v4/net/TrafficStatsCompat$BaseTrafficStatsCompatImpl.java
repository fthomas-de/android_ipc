package android.support.v4.net;

import java.net.Socket;

class TrafficStatsCompat$BaseTrafficStatsCompatImpl implements android.support.v4.net.TrafficStatsCompat$TrafficStatsCompatImpl
{
    private ThreadLocal mThreadSocketTags;

    TrafficStatsCompat$BaseTrafficStatsCompatImpl()
    {


        this.<init>();
        mThreadSocketTags = new TrafficStatsCompat$BaseTrafficStatsCompatImpl$1(this);
    }

    public void clearThreadStatsTag()
    {


        ((TrafficStatsCompat$BaseTrafficStatsCompatImpl$SocketTags) mThreadSocketTags.get()).statsTag = -1;
    }

    public int getThreadStatsTag()
    {


        return ((TrafficStatsCompat$BaseTrafficStatsCompatImpl$SocketTags) mThreadSocketTags.get()).statsTag;
    }

    public void incrementOperationCount(int  i0)
    {

    }

    public void incrementOperationCount(int  i0, int  i1)
    {

    }

    public void setThreadStatsTag(int  i0)
    {


        ((TrafficStatsCompat$BaseTrafficStatsCompatImpl$SocketTags) mThreadSocketTags.get()).statsTag = i0;
    }

    public void tagSocket(Socket  r1)
    {

    }

    public void untagSocket(Socket  r1)
    {

    }
}
