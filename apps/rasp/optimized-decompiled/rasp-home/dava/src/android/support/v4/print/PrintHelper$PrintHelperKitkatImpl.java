package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.FileNotFoundException;

final class PrintHelper$PrintHelperKitkatImpl implements android.support.v4.print.PrintHelper$PrintHelperVersionImpl
{
    private final PrintHelperKitkat mPrintHelper;

    PrintHelper$PrintHelperKitkatImpl(Context  r1)
    {


        this.<init>();
        mPrintHelper = new PrintHelperKitkat(r1);
    }

    public int getColorMode()
    {


        return mPrintHelper.getColorMode();
    }

    public int getOrientation()
    {


        return mPrintHelper.getOrientation();
    }

    public int getScaleMode()
    {


        return mPrintHelper.getScaleMode();
    }

    public void printBitmap(String  r1, Bitmap  r2)
    {


        mPrintHelper.printBitmap(r1, r2);
    }

    public void printBitmap(String  r1, Uri  r2) throws java.io.FileNotFoundException
    {


        mPrintHelper.printBitmap(r1, r2);
    }

    public void setColorMode(int  i0)
    {


        mPrintHelper.setColorMode(i0);
    }

    public void setOrientation(int  i0)
    {


        mPrintHelper.setOrientation(i0);
    }

    public void setScaleMode(int  i0)
    {


        mPrintHelper.setScaleMode(i0);
    }
}
