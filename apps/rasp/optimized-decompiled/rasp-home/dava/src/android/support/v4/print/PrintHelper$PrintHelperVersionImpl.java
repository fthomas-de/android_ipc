package android.support.v4.print;

import android.graphics.Bitmap;
import java.io.FileNotFoundException;
import android.net.Uri;

abstract interface PrintHelper$PrintHelperVersionImpl
{

    public abstract int getColorMode();

    public abstract int getOrientation();

    public abstract int getScaleMode();

    public abstract void printBitmap(java.lang.String  r0, android.graphics.Bitmap  r1);

    public abstract void printBitmap(java.lang.String  r0, android.net.Uri  r1) throws java.io.FileNotFoundException;

    public abstract void setColorMode(int  i0);

    public abstract void setOrientation(int  i0);

    public abstract void setScaleMode(int  i0);
}
