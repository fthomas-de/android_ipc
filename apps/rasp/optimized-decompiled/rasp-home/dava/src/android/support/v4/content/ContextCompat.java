package android.support.v4.content;

import java.io.File;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.Environment;
import android.os.Bundle;

public class ContextCompat
{
    private static final String DIR_ANDROID = "Android";
    private static final String DIR_CACHE = "cache";
    private static final String DIR_DATA = "data";
    private static final String DIR_FILES = "files";
    private static final String DIR_OBB = "obb";

    public ContextCompat()
    {


        this.<init>();
    }

    private static File buildPath(File  r0, String[]  r1)
    {

        int i0, i1;
        File r4, r6;
        String r5;
        i0 = r1.length;
        i1 = 0;
        r4 = r0;

        while (i1 < i0)
        {
            r5 = r1[i1];

            if (r4 != null)
            {
                if (r5 == null)
                {
                    r6 = r4;
                }
                else
                {
                    r6 = new File(r4, r5);
                }
            }
            else
            {
                r6 = new File(r5);
            }

            i1 = i1 + 1;
            r4 = r6;
        }

        return r4;
    }

    public static File[] getExternalCacheDirs(Context  r0)
    {

        int i2;
        File r1, r5;
        File[] r4;
        String[] r6;
        i2 = Build$VERSION.SDK_INT;

        if (i2 < 19)
        {
            if (i2 < 8)
            {
                r5 = Environment.getExternalStorageDirectory();
                r6 = new String[4];
                r6[0] = "Android";
                r6[1] = "data";
                r6[2] = r0.getPackageName();
                r6[3] = "cache";
                r1 = ContextCompat.buildPath(r5, r6);
            }
            else
            {
                r1 = ContextCompatFroyo.getExternalCacheDir(r0);
            }

            r4 = new File[1];
            r4[0] = r1;
        }
        else
        {
            r4 = ContextCompatKitKat.getExternalCacheDirs(r0);
        }

        return r4;
    }

    public static File[] getExternalFilesDirs(Context  r0, String  r1)
    {

        int i2;
        File r2, r6;
        File[] r5;
        String[] r7;
        i2 = Build$VERSION.SDK_INT;

        if (i2 < 19)
        {
            if (i2 < 8)
            {
                r6 = Environment.getExternalStorageDirectory();
                r7 = new String[5];
                r7[0] = "Android";
                r7[1] = "data";
                r7[2] = r0.getPackageName();
                r7[3] = "files";
                r7[4] = r1;
                r2 = ContextCompat.buildPath(r6, r7);
            }
            else
            {
                r2 = ContextCompatFroyo.getExternalFilesDir(r0, r1);
            }

            r5 = new File[1];
            r5[0] = r2;
        }
        else
        {
            r5 = ContextCompatKitKat.getExternalFilesDirs(r0, r1);
        }

        return r5;
    }

    public static File[] getObbDirs(Context  r0)
    {

        int i2;
        File r1, r5;
        File[] r4;
        String[] r6;
        i2 = Build$VERSION.SDK_INT;

        if (i2 < 19)
        {
            if (i2 < 11)
            {
                r5 = Environment.getExternalStorageDirectory();
                r6 = new String[3];
                r6[0] = "Android";
                r6[1] = "obb";
                r6[2] = r0.getPackageName();
                r1 = ContextCompat.buildPath(r5, r6);
            }
            else
            {
                r1 = ContextCompatHoneycomb.getObbDir(r0);
            }

            r4 = new File[1];
            r4[0] = r1;
        }
        else
        {
            r4 = ContextCompatKitKat.getObbDirs(r0);
        }

        return r4;
    }

    public static boolean startActivities(Context  r0, Intent[]  r1)
    {


        return ContextCompat.startActivities(r0, r1, null);
    }

    public static boolean startActivities(Context  r0, Intent[]  r1, Bundle  r2)
    {

        boolean z0;
        int i0;
        z0 = true;
        i0 = Build$VERSION.SDK_INT;

        if (i0 < 16)
        {
            if (i0 < 11)
            {
                z0 = false;
            }
            else
            {
                ContextCompatHoneycomb.startActivities(r0, r1);
            }
        }
        else
        {
            ContextCompatJellybean.startActivities(r0, r1, r2);
        }

        return z0;
    }
}
