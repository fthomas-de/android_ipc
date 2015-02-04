package android.support.v4.net;

import android.os.Build$VERSION;
import java.net.Socket;
import java.net.SocketException;

public class TrafficStatsCompat
{
    private static final TrafficStatsCompat$TrafficStatsCompatImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 14)
        {
            IMPL = new TrafficStatsCompat$BaseTrafficStatsCompatImpl();
        }
        else
        {
            IMPL = new TrafficStatsCompat$IcsTrafficStatsCompatImpl();
        }
    }

    public TrafficStatsCompat()
    {


        this.<init>();
    }

    public static void clearThreadStatsTag()
    {


        IMPL.clearThreadStatsTag();
    }

    public static int getThreadStatsTag()
    {


        return IMPL.getThreadStatsTag();
    }

    public static void incrementOperationCount(int  i0)
    {


        IMPL.incrementOperationCount(i0);
    }

    public static void incrementOperationCount(int  i0, int  i1)
    {


        IMPL.incrementOperationCount(i0, i1);
    }

    public static void setThreadStatsTag(int  i0)
    {


        IMPL.setThreadStatsTag(i0);
    }

    public static void tagSocket(Socket  r0) throws java.net.SocketException
    {


        IMPL.tagSocket(r0);
    }

    public static void untagSocket(Socket  r0) throws java.net.SocketException
    {


        IMPL.untagSocket(r0);
    }
}
