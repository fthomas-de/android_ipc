package android.support.v4.hardware.display;

import android.hardware.display.DisplayManager;
import android.view.Display;
import android.content.Context;

final class DisplayManagerJellybeanMr1
{

    DisplayManagerJellybeanMr1()
    {


        this.<init>();
    }

    public static Display getDisplay(Object  r0, int  i0)
    {


        return ((DisplayManager) r0).getDisplay(i0);
    }

    public static Object getDisplayManager(Context  r0)
    {


        return r0.getSystemService("display");
    }

    public static Display[] getDisplays(Object  r0)
    {


        return ((DisplayManager) r0).getDisplays();
    }

    public static Display[] getDisplays(Object  r0, String  r1)
    {


        return ((DisplayManager) r0).getDisplays(r1);
    }
}
