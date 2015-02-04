package android.support.v4.content;

import android.content.Context;
import java.io.File;

class ContextCompatHoneycomb
{

    ContextCompatHoneycomb()
    {


        this.<init>();
    }

    public static File getObbDir(Context  r0)
    {


        return r0.getObbDir();
    }

    static void startActivities(Context  r0, Intent[]  r1)
    {


        r0.startActivities(r1);
    }
}
