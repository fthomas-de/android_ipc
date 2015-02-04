package android.support.v4.content;

import android.content.Context;
import java.io.File;

class ContextCompatFroyo
{

    ContextCompatFroyo()
    {


        this.<init>();
    }

    public static File getExternalCacheDir(Context  r0)
    {


        return r0.getExternalCacheDir();
    }

    public static File getExternalFilesDir(Context  r0, String  r1)
    {


        return r0.getExternalFilesDir(r1);
    }
}
