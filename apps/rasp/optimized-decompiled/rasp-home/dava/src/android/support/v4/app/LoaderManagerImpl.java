package android.support.v4.app;

import android.support.v4.util.SparseArrayCompat;
import android.os.Bundle;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import android.support.v4.content.Loader;
import android.support.v4.util.DebugUtils;

class LoaderManagerImpl extends LoaderManager
{
    static boolean DEBUG;
    static final String TAG = "LoaderManager";
    FragmentActivity mActivity;
    boolean mCreatingLoader;
    final SparseArrayCompat mInactiveLoaders;
    final SparseArrayCompat mLoaders;
    boolean mRetaining;
    boolean mRetainingStarted;
    boolean mStarted;
    final String mWho;

    static
    {


        DEBUG = false;
    }

    LoaderManagerImpl(String  r1, FragmentActivity  r2, boolean  z0)
    {


        this.<init>();
        mLoaders = new SparseArrayCompat();
        mInactiveLoaders = new SparseArrayCompat();
        mWho = r1;
        mActivity = r2;
        mStarted = z0;
    }

    private LoaderManagerImpl$LoaderInfo createAndInstallLoader(int  i0, Bundle  r1, LoaderManager$LoaderCallbacks  r2)
    {

        LoaderManagerImpl$LoaderInfo r3;
        label_1:
        {
            label_0:
            {
                try
                {
                    mCreatingLoader = true;
                }
                catch (Throwable $r4)
                {
                    break label_0;
                }

                r3 = this.createLoader(i0, r1, r2);

                try
                {
                    this.installLoader(r3);
                    break label_1;
                }
                catch (Throwable $r4)
                {
                }
            } //end label_0:


            mCreatingLoader = false;
            throw $r4;
        } //end label_1:


        mCreatingLoader = false;
        return r3;
    }

    private LoaderManagerImpl$LoaderInfo createLoader(int  i0, Bundle  r1, LoaderManager$LoaderCallbacks  r2)
    {

        LoaderManagerImpl$LoaderInfo r3;
        r3 = new LoaderManagerImpl$LoaderInfo(this, i0, r1, r2);
        r3.mLoader = r2.onCreateLoader(i0, r1);
        return r3;
    }

    public void destroyLoader(int  i0)
    {

        int i1, i3;
        LoaderManagerImpl$LoaderInfo r15, r20;
        if (mCreatingLoader == false)
        {
            if (DEBUG != false)
            {
                Log.v("LoaderManager", (new StringBuilder()).append("destroyLoader in ").append(this).append(" of ").append(i0).toString());
            }

            i1 = mLoaders.indexOfKey(i0);

            if (i1 >= 0)
            {
                r15 = (LoaderManagerImpl$LoaderInfo) mLoaders.valueAt(i1);
                mLoaders.removeAt(i1);
                r15.destroy();
            }

            i3 = mInactiveLoaders.indexOfKey(i0);

            if (i3 >= 0)
            {
                r20 = (LoaderManagerImpl$LoaderInfo) mInactiveLoaders.valueAt(i3);
                mInactiveLoaders.removeAt(i3);
                r20.destroy();
            }

            if (mActivity != null)
            {
                if (this.hasRunningLoaders() == false)
                {
                    mActivity.mFragments.startPendingDeferredFragments();
                }
            }

            return;
        }
        else
        {
            throw new IllegalStateException("Called while creating a loader");
        }
    }

    void doDestroy()
    {

        int i0, i5;
        if (mRetaining == false)
        {
            if (DEBUG != false)
            {
                Log.v("LoaderManager", (new StringBuilder()).append("Destroying Active in ").append(this).toString());
            }

            i0 = mLoaders.size() + -1;

            while (i0 >= 0)
            {
                ((LoaderManagerImpl$LoaderInfo) mLoaders.valueAt(i0)).destroy();
                i0 = i0 + -1;
            }

            mLoaders.clear();
        }

        if (DEBUG != false)
        {
            Log.v("LoaderManager", (new StringBuilder()).append("Destroying Inactive in ").append(this).toString());
        }

        i5 = mInactiveLoaders.size() + -1;

        while (i5 >= 0)
        {
            ((LoaderManagerImpl$LoaderInfo) mInactiveLoaders.valueAt(i5)).destroy();
            i5 = i5 + -1;
        }

        mInactiveLoaders.clear();
    }

    void doReportNextStart()
    {

        int i0;
        i0 = mLoaders.size() + -1;

        while (i0 >= 0)
        {
            ((LoaderManagerImpl$LoaderInfo) mLoaders.valueAt(i0)).mReportNextStart = true;
            i0 = i0 + -1;
        }
    }

    void doReportStart()
    {

        int i0;
        i0 = mLoaders.size() + -1;

        while (i0 >= 0)
        {
            ((LoaderManagerImpl$LoaderInfo) mLoaders.valueAt(i0)).reportStart();
            i0 = i0 + -1;
        }
    }

    void doRetain()
    {

        RuntimeException r3;
        int i0;
        if (DEBUG != false)
        {
            Log.v("LoaderManager", (new StringBuilder()).append("Retaining in ").append(this).toString());
        }

        if (mStarted != false)
        {
            mRetaining = true;
            mStarted = false;
            i0 = mLoaders.size() + -1;

            while (i0 >= 0)
            {
                ((LoaderManagerImpl$LoaderInfo) mLoaders.valueAt(i0)).retain();
                i0 = i0 + -1;
            }
        }
        else
        {
            r3 = new RuntimeException("here");
            r3.fillInStackTrace();
            Log.w("LoaderManager", (new StringBuilder()).append("Called doRetain when not started: ").append(this).toString(), r3);
        }
    }

    void doStart()
    {

        RuntimeException r3;
        int i0;
        if (DEBUG != false)
        {
            Log.v("LoaderManager", (new StringBuilder()).append("Starting in ").append(this).toString());
        }

        if (mStarted == false)
        {
            mStarted = true;
            i0 = mLoaders.size() + -1;

            while (i0 >= 0)
            {
                ((LoaderManagerImpl$LoaderInfo) mLoaders.valueAt(i0)).start();
                i0 = i0 + -1;
            }
        }
        else
        {
            r3 = new RuntimeException("here");
            r3.fillInStackTrace();
            Log.w("LoaderManager", (new StringBuilder()).append("Called doStart when already started: ").append(this).toString(), r3);
        }
    }

    void doStop()
    {

        RuntimeException r3;
        int i0;
        if (DEBUG != false)
        {
            Log.v("LoaderManager", (new StringBuilder()).append("Stopping in ").append(this).toString());
        }

        if (mStarted != false)
        {
            i0 = mLoaders.size() + -1;

            while (i0 >= 0)
            {
                ((LoaderManagerImpl$LoaderInfo) mLoaders.valueAt(i0)).stop();
                i0 = i0 + -1;
            }

            mStarted = false;
        }
        else
        {
            r3 = new RuntimeException("here");
            r3.fillInStackTrace();
            Log.w("LoaderManager", (new StringBuilder()).append("Called doStop when not started: ").append(this).toString(), r3);
        }
    }

    public void dump(String  r1, FileDescriptor  r2, PrintWriter  r3, String[]  r4)
    {

        String r7, r26;
        int i0, i5;
        LoaderManagerImpl$LoaderInfo r15, r30;
        if (mLoaders.size() > 0)
        {
            r3.print(r1);
            r3.println("Active Loaders:");
            r7 = (new StringBuilder()).append(r1).append("    ").toString();
            i0 = 0;

            while (i0 < mLoaders.size())
            {
                r15 = (LoaderManagerImpl$LoaderInfo) mLoaders.valueAt(i0);
                r3.print(r1);
                r3.print("  #");
                r3.print(mLoaders.keyAt(i0));
                r3.print(": ");
                r3.println(r15.toString());
                r15.dump(r7, r2, r3, r4);
                i0 = i0 + 1;
            }
        }

        if (mInactiveLoaders.size() > 0)
        {
            r3.print(r1);
            r3.println("Inactive Loaders:");
            r26 = (new StringBuilder()).append(r1).append("    ").toString();
            i5 = 0;

            while (i5 < mInactiveLoaders.size())
            {
                r30 = (LoaderManagerImpl$LoaderInfo) mInactiveLoaders.valueAt(i5);
                r3.print(r1);
                r3.print("  #");
                r3.print(mInactiveLoaders.keyAt(i5));
                r3.print(": ");
                r3.println(r30.toString());
                r30.dump(r26, r2, r3, r4);
                i5 = i5 + 1;
            }
        }
    }

    void finishRetain()
    {

        int i0;
        if (mRetaining != false)
        {
            if (DEBUG != false)
            {
                Log.v("LoaderManager", (new StringBuilder()).append("Finished Retaining in ").append(this).toString());
            }

            mRetaining = false;
            i0 = mLoaders.size() + -1;

            while (i0 >= 0)
            {
                ((LoaderManagerImpl$LoaderInfo) mLoaders.valueAt(i0)).finishRetain();
                i0 = i0 + -1;
            }
        }
    }

    public Loader getLoader(int  i0)
    {

        LoaderManagerImpl$LoaderInfo r5;
        Loader r8;
        if (mCreatingLoader == false)
        {
            r5 = (LoaderManagerImpl$LoaderInfo) mLoaders.get(i0);

            if (r5 == null)
            {
                r8 = null;
            }
            else
            {
                if (r5.mPendingLoader == null)
                {
                    r8 = r5.mLoader;
                }
                else
                {
                    r8 = r5.mPendingLoader.mLoader;
                }
            }

            return r8;
        }
        else
        {
            throw new IllegalStateException("Called while creating a loader");
        }
    }

    public boolean hasRunningLoaders()
    {

        boolean z0, z3;
        int i0, i1;
        LoaderManagerImpl$LoaderInfo r4;
        z0 = false;
        i0 = mLoaders.size();
        i1 = 0;

        while (i1 < i0)
        {
            r4 = (LoaderManagerImpl$LoaderInfo) mLoaders.valueAt(i1);

            label_2:
            {
                if (r4.mStarted != false)
                {
                    if (r4.mDeliveredData == false)
                    {
                        z3 = true;
                        break label_2;
                    }
                }

                z3 = false;
            } //end label_2:


            z0 = z0 | z3;
            i1 = i1 + 1;
        }

        return z0;
    }

    public Loader initLoader(int  i0, Bundle  r1, LoaderManager$LoaderCallbacks  r2)
    {

        LoaderManagerImpl$LoaderInfo r8;
        if (mCreatingLoader == false)
        {
            r8 = (LoaderManagerImpl$LoaderInfo) mLoaders.get(i0);

            if (DEBUG != false)
            {
                Log.v("LoaderManager", (new StringBuilder()).append("initLoader in ").append(this).append(": args=").append(r1).toString());
            }

            if (r8 != null)
            {
                if (DEBUG != false)
                {
                    Log.v("LoaderManager", (new StringBuilder()).append("  Re-using existing loader ").append(r8).toString());
                }

                r8.mCallbacks = r2;
            }
            else
            {
                r8 = this.createAndInstallLoader(i0, r1, r2);

                if (DEBUG != false)
                {
                    Log.v("LoaderManager", (new StringBuilder()).append("  Created new loader ").append(r8).toString());
                }
            }

            if (r8.mHaveData != false)
            {
                if (mStarted != false)
                {
                    r8.callOnLoadFinished(r8.mLoader, r8.mData);
                }
            }

            return r8.mLoader;
        }
        else
        {
            throw new IllegalStateException("Called while creating a loader");
        }
    }

    void installLoader(LoaderManagerImpl$LoaderInfo  r1)
    {


        mLoaders.put(r1.mId, r1);

        if (mStarted != false)
        {
            r1.start();
        }
    }

    public Loader restartLoader(int  i0, Bundle  r1, LoaderManager$LoaderCallbacks  r2)
    {

        Object n0;
        LoaderManagerImpl$LoaderInfo r9, r19;
        Loader r29;
        n0 = null;

        if (mCreatingLoader == false)
        {
            r9 = (LoaderManagerImpl$LoaderInfo) mLoaders.get(i0);

            if (DEBUG != false)
            {
                Log.v("LoaderManager", (new StringBuilder()).append("restartLoader in ").append(this).append(": args=").append(r1).toString());
            }

            label_3:
            {
                if (r9 != null)
                {
                    r19 = (LoaderManagerImpl$LoaderInfo) mInactiveLoaders.get(i0);

                    if (r19 == null)
                    {
                        if (DEBUG != false)
                        {
                            Log.v("LoaderManager", (new StringBuilder()).append("  Making last loader inactive: ").append(r9).toString());
                        }

                        r9.mLoader.abandon();
                        mInactiveLoaders.put(i0, r9);
                    }
                    else
                    {
                        if (r9.mHaveData == false)
                        {
                            if (r9.mStarted != false)
                            {
                                if (r9.mPendingLoader != null)
                                {
                                    if (DEBUG != false)
                                    {
                                        Log.v("LoaderManager", (new StringBuilder()).append("  Removing pending loader: ").append(r9.mPendingLoader).toString());
                                    }

                                    r9.mPendingLoader.destroy();
                                    r9.mPendingLoader = n0;
                                }

                                if (DEBUG != false)
                                {
                                    Log.v("LoaderManager", "  Enqueuing as new pending loader");
                                }

                                r9.mPendingLoader = this.createLoader(i0, r1, r2);
                                r29 = r9.mPendingLoader.mLoader;
                                break label_3;
                            }
                            else
                            {
                                if (DEBUG != false)
                                {
                                    Log.v("LoaderManager", "  Current loader is stopped; replacing");
                                }

                                mLoaders.put(i0, n0);
                                r9.destroy();
                            }
                        }
                        else
                        {
                            if (DEBUG != false)
                            {
                                Log.v("LoaderManager", (new StringBuilder()).append("  Removing last inactive loader: ").append(r9).toString());
                            }

                            r19.mDeliveredData = false;
                            r19.destroy();
                            r9.mLoader.abandon();
                            mInactiveLoaders.put(i0, r9);
                        }
                    }
                }

                r29 = this.createAndInstallLoader(i0, r1, r2).mLoader;
            } //end label_3:


            return r29;
        }
        else
        {
            throw new IllegalStateException("Called while creating a loader");
        }
    }

    public String toString()
    {

        StringBuilder r1;
        r1 = new StringBuilder(128);
        r1.append("LoaderManager{");
        r1.append(Integer.toHexString(System.identityHashCode(this)));
        r1.append(" in ");
        DebugUtils.buildShortClassTag(mActivity, r1);
        r1.append("}}");
        return r1.toString();
    }

    void updateActivity(FragmentActivity  r1)
    {


        mActivity = r1;
    }
}
