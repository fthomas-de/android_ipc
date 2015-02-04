package android.support.v4.hardware.display;

import android.content.Context;
import android.view.Display;

class DisplayManagerCompat$JellybeanMr1Impl extends DisplayManagerCompat
{
    private final Object mDisplayManagerObj;

    public DisplayManagerCompat$JellybeanMr1Impl(Context  r1)
    {


        this.<init>();
        mDisplayManagerObj = DisplayManagerJellybeanMr1.getDisplayManager(r1);
    }

    public Display getDisplay(int  i0)
    {


        return DisplayManagerJellybeanMr1.getDisplay(mDisplayManagerObj, i0);
    }

    public Display[] getDisplays()
    {


        return DisplayManagerJellybeanMr1.getDisplays(mDisplayManagerObj);
    }

    public Display[] getDisplays(String  r1)
    {


        return DisplayManagerJellybeanMr1.getDisplays(mDisplayManagerObj, r1);
    }
}
