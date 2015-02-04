package android.support.v4.print;

import android.graphics.Bitmap;
import android.net.Uri;

final class PrintHelper$PrintHelperStubImpl implements android.support.v4.print.PrintHelper$PrintHelperVersionImpl
{
    int mColorMode;
    int mOrientation;
    int mScaleMode;

    private PrintHelper$PrintHelperStubImpl()
    {


        this.<init>();
        mScaleMode = 2;
        mColorMode = 2;
        mOrientation = 1;
    }

    PrintHelper$PrintHelperStubImpl(PrintHelper$1  r1)
    {
        this();


        this.<init>();
    }

    public int getColorMode()
    {


        return mColorMode;
    }

    public int getOrientation()
    {


        return mOrientation;
    }

    public int getScaleMode()
    {


        return mScaleMode;
    }

    public void printBitmap(String  r1, Bitmap  r2)
    {

    }

    public void printBitmap(String  r1, Uri  r2)
    {

    }

    public void setColorMode(int  i0)
    {


        mColorMode = i0;
    }

    public void setOrientation(int  i0)
    {


        mOrientation = i0;
    }

    public void setScaleMode(int  i0)
    {


        mScaleMode = i0;
    }
}
