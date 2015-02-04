package android.support.v4.hardware.display;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

class DisplayManagerCompat$LegacyImpl extends DisplayManagerCompat
{
    private final WindowManager mWindowManager;

    public DisplayManagerCompat$LegacyImpl(Context  r1)
    {


        this.<init>();
        mWindowManager = (WindowManager) r1.getSystemService("window");
    }

    public Display getDisplay(int  i0)
    {

        Display r2;
        r2 = mWindowManager.getDefaultDisplay();

        if (r2.getDisplayId() != i0)
        {
            r2 = null;
        }

        return r2;
    }

    public Display[] getDisplays()
    {

        Display[] r2;
        r2 = new Display[1];
        r2[0] = mWindowManager.getDefaultDisplay();
        return r2;
    }

    public Display[] getDisplays(String  r1)
    {

        Display[] r2;
        if (r1 != null)
        {
            r2 = new Display[0];
        }
        else
        {
            r2 = this.getDisplays();
        }

        return r2;
    }
}
