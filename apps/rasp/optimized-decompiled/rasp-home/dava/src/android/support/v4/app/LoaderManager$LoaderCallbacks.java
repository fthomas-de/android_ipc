package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.content.Loader;

public abstract interface LoaderManager$LoaderCallbacks
{

    public abstract android.support.v4.content.Loader onCreateLoader(int  i0, android.os.Bundle  r1);

    public abstract void onLoadFinished(android.support.v4.content.Loader  r0, java.lang.Object  r1);

    public abstract void onLoaderReset(android.support.v4.content.Loader  r0);
}
