package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class ConnectivityManagerCompatGingerbread
{

    ConnectivityManagerCompatGingerbread()
    {


        this.<init>();
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager  r0)
    {

        boolean z0;
        NetworkInfo r1;
        z0 = true;
        r1 = r0.getActiveNetworkInfo();

        label_0:
        if (r1 != null)
        {
            switch (r1.getType())
            {
                case 1:
                    z0 = false;
                    break label_0;

                case 0:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    break label_0;

                default:
                    break label_0;
            }
        }

        return z0;
    }
}