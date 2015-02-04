package android.support.v4.content;

import android.content.Context;

class ContextCompatKitKat
{

    ContextCompatKitKat()
    {


        this.<init>();
    }

    public static File[] getExternalCacheDirs(Context  r0)
    {


        return r0.getExternalCacheDirs();
    }

    public static File[] getExternalFilesDirs(Context  r0, String  r1)
    {


        return r0.getExternalFilesDirs(r1);
    }

    public static File[] getObbDirs(Context  r0)
    {


        return r0.getObbDirs();
    }
}
