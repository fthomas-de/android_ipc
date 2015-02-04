package android.support.v4.net;

import android.net.TrafficStats;
import java.net.Socket;
import java.net.SocketException;

class TrafficStatsCompatIcs
{

    TrafficStatsCompatIcs()
    {


        this.<init>();
    }

    public static void clearThreadStatsTag()
    {


        TrafficStats.clearThreadStatsTag();
    }

    public static int getThreadStatsTag()
    {


        return TrafficStats.getThreadStatsTag();
    }

    public static void incrementOperationCount(int  i0)
    {


        TrafficStats.incrementOperationCount(i0);
    }

    public static void incrementOperationCount(int  i0, int  i1)
    {


        TrafficStats.incrementOperationCount(i0, i1);
    }

    public static void setThreadStatsTag(int  i0)
    {


        TrafficStats.setThreadStatsTag(i0);
    }

    public static void tagSocket(Socket  r0) throws java.net.SocketException
    {


        TrafficStats.tagSocket(r0);
    }

    public static void untagSocket(Socket  r0) throws java.net.SocketException
    {


        TrafficStats.untagSocket(r0);
    }
}
