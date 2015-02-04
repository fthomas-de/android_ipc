package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class ConnectivityManagerCompat$BaseConnectivityManagerCompatImpl implements android.support.v4.net.ConnectivityManagerCompat$ConnectivityManagerCompatImpl
{

    ConnectivityManagerCompat$BaseConnectivityManagerCompatImpl()
    {


        this.<init>();
    }

    public boolean isActiveNetworkMetered(ConnectivityManager  r1)
    {

        boolean z0;
        NetworkInfo r2;
        z0 = true;
        r2 = r1.getActiveNetworkInfo();

        label_0:
        if (r2 != null)
        {
            switch (r2.getType())
            {
                case 0:
                    break label_0;

                case 1:
                    z0 = false;
                    break label_0;

                default:
                    break label_0;
            }
        }

        return z0;
    }
}
