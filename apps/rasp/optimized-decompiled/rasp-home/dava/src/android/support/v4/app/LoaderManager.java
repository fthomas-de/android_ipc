package android.support.v4.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import android.support.v4.content.Loader;
import android.os.Bundle;

public abstract class LoaderManager
{

    public LoaderManager()
    {


        this.<init>();
    }

    public abstract void destroyLoader(int  i0);

    public abstract void dump(java.lang.String  r0, java.io.FileDescriptor  r1, java.io.PrintWriter  r2, java.lang.String[]  r3);

    public static void enableDebugLogging(boolean  z0)
    {


        LoaderManagerImpl.DEBUG = z0;
    }

    public abstract android.support.v4.content.Loader getLoader(int  i0);

    public boolean hasRunningLoaders()
    {


        return false;
    }

    public abstract android.support.v4.content.Loader initLoader(int  i0, android.os.Bundle  r1, android.support.v4.app.LoaderManager$LoaderCallbacks  r2);

    public abstract android.support.v4.content.Loader restartLoader(int  i0, android.os.Bundle  r1, android.support.v4.app.LoaderManager$LoaderCallbacks  r2);
}
