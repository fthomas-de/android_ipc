package android.support.v13.app;

import android.os.Build$VERSION;
import android.app.Fragment;

public class FragmentCompat
{
    static final FragmentCompat$FragmentCompatImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 15)
        {
            if (Build$VERSION.SDK_INT < 14)
            {
                IMPL = new FragmentCompat$BaseFragmentCompatImpl();
            }
            else
            {
                IMPL = new FragmentCompat$ICSFragmentCompatImpl();
            }
        }
        else
        {
            IMPL = new FragmentCompat$ICSMR1FragmentCompatImpl();
        }
    }

    public FragmentCompat()
    {


        this.<init>();
    }

    public static void setMenuVisibility(Fragment  r0, boolean  z0)
    {


        IMPL.setMenuVisibility(r0, z0);
    }

    public static void setUserVisibleHint(Fragment  r0, boolean  z0)
    {


        IMPL.setUserVisibleHint(r0, z0);
    }
}
