package android.support.v4.net;

import java.net.Socket;
import java.net.SocketException;

class TrafficStatsCompat$IcsTrafficStatsCompatImpl implements android.support.v4.net.TrafficStatsCompat$TrafficStatsCompatImpl
{

    TrafficStatsCompat$IcsTrafficStatsCompatImpl()
    {


        this.<init>();
    }

    public void clearThreadStatsTag()
    {


        TrafficStatsCompatIcs.clearThreadStatsTag();
    }

    public int getThreadStatsTag()
    {


        return TrafficStatsCompatIcs.getThreadStatsTag();
    }

    public void incrementOperationCount(int  i0)
    {


        TrafficStatsCompatIcs.incrementOperationCount(i0);
    }

    public void incrementOperationCount(int  i0, int  i1)
    {


        TrafficStatsCompatIcs.incrementOperationCount(i0, i1);
    }

    public void setThreadStatsTag(int  i0)
    {


        TrafficStatsCompatIcs.setThreadStatsTag(i0);
    }

    public void tagSocket(Socket  r1) throws java.net.SocketException
    {


        TrafficStatsCompatIcs.tagSocket(r1);
    }

    public void untagSocket(Socket  r1) throws java.net.SocketException
    {


        TrafficStatsCompatIcs.untagSocket(r1);
    }
}
