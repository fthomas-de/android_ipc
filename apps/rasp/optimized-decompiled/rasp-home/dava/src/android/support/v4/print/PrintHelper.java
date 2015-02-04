package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.FileNotFoundException;
import android.os.Build$VERSION;

public final class PrintHelper
{
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    PrintHelper$PrintHelperVersionImpl mImpl;

    public PrintHelper(Context  r1)
    {


        this.<init>();

        if (PrintHelper.systemSupportsPrint() == false)
        {
            mImpl = new PrintHelper$PrintHelperStubImpl(null);
        }
        else
        {
            mImpl = new PrintHelper$PrintHelperKitkatImpl(r1);
        }
    }

    public int getColorMode()
    {


        return mImpl.getColorMode();
    }

    public int getOrientation()
    {


        return mImpl.getOrientation();
    }

    public int getScaleMode()
    {


        return mImpl.getScaleMode();
    }

    public void printBitmap(String  r1, Bitmap  r2)
    {


        mImpl.printBitmap(r1, r2);
    }

    public void printBitmap(String  r1, Uri  r2) throws java.io.FileNotFoundException
    {


        mImpl.printBitmap(r1, r2);
    }

    public void setColorMode(int  i0)
    {


        mImpl.setColorMode(i0);
    }

    public void setOrientation(int  i0)
    {


        mImpl.setOrientation(i0);
    }

    public void setScaleMode(int  i0)
    {


        mImpl.setScaleMode(i0);
    }

    public static boolean systemSupportsPrint()
    {

        boolean z0;
        if (Build$VERSION.SDK_INT < 19)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }
}
