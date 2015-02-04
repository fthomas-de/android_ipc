package android.support.v4.net;

import android.os.Build$VERSION;
import android.net.ConnectivityManager;
import android.content.Intent;
import android.net.NetworkInfo;

public class ConnectivityManagerCompat
{
    private static final ConnectivityManagerCompat$ConnectivityManagerCompatImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 16)
        {
            if (Build$VERSION.SDK_INT < 13)
            {
                if (Build$VERSION.SDK_INT < 8)
                {
                    IMPL = new ConnectivityManagerCompat$BaseConnectivityManagerCompatImpl();
                }
                else
                {
                    IMPL = new ConnectivityManagerCompat$GingerbreadConnectivityManagerCompatImpl();
                }
            }
            else
            {
                IMPL = new ConnectivityManagerCompat$HoneycombMR2ConnectivityManagerCompatImpl();
            }
        }
        else
        {
            IMPL = new ConnectivityManagerCompat$JellyBeanConnectivityManagerCompatImpl();
        }
    }

    public ConnectivityManagerCompat()
    {


        this.<init>();
    }

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager  r0, Intent  r1)
    {


        return r0.getNetworkInfo(((NetworkInfo) r1.getParcelableExtra("networkInfo")).getType());
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager  r0)
    {


        return IMPL.isActiveNetworkMetered(r0);
    }
}
