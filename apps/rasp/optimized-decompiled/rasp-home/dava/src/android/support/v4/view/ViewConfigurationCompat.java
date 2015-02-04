package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.ViewConfiguration;

public class ViewConfigurationCompat
{
    static final ViewConfigurationCompat$ViewConfigurationVersionImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 11)
        {
            IMPL = new ViewConfigurationCompat$BaseViewConfigurationVersionImpl();
        }
        else
        {
            IMPL = new ViewConfigurationCompat$FroyoViewConfigurationVersionImpl();
        }
    }

    public ViewConfigurationCompat()
    {


        this.<init>();
    }

    public static int getScaledPagingTouchSlop(ViewConfiguration  r0)
    {


        return IMPL.getScaledPagingTouchSlop(r0);
    }
}
