package android.support.v4.app;

import android.app.Activity;
import java.io.FileDescriptor;
import java.io.PrintWriter;

class ActivityCompatHoneycomb
{

    ActivityCompatHoneycomb()
    {


        this.<init>();
    }

    static void dump(Activity  r0, String  r1, FileDescriptor  r2, PrintWriter  r3, String[]  r4)
    {


        r0.dump(r1, r2, r3, r4);
    }

    static void invalidateOptionsMenu(Activity  r0)
    {


        r0.invalidateOptionsMenu();
    }
}
