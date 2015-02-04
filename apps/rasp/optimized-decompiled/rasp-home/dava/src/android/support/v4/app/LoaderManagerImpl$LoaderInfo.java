package android.support.v4.app;

import android.support.v4.content.Loader$OnLoadCompleteListener;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import android.support.v4.util.SparseArrayCompat;
import java.lang.reflect.Modifier;
import android.support.v4.util.DebugUtils;

final class LoaderManagerImpl$LoaderInfo implements android.support.v4.content.Loader$OnLoadCompleteListener
{
    final Bundle mArgs;
    LoaderManager$LoaderCallbacks mCallbacks;
    Object mData;
    boolean mDeliveredData;
    boolean mDestroyed;
    boolean mHaveData;
    final int mId;
    boolean mListenerRegistered;
    Loader mLoader;
    LoaderManagerImpl$LoaderInfo mPendingLoader;
    boolean mReportNextStart;
    boolean mRetaining;
    boolean mRetainingStarted;
    boolean mStarted;
    final LoaderManagerImpl this$0;

    public LoaderManagerImpl$LoaderInfo(LoaderManagerImpl  r1, int  i0, Bundle  r2, LoaderManager$LoaderCallbacks  r3)
    {


        this$0 = r1;
        this.<init>();
        mId = i0;
        mArgs = r2;
        mCallbacks = r3;
    }

    void callOnLoadFinished(Loader  r1, Object  r2)
    {

        String r4, r6, r15, r21, $r23, $r27;
        boolean z0;
        StringBuilder r16, $r17, $r19, r22, $r25;
        LoaderManager$LoaderCallbacks r29;
        if (mCallbacks != null)
        {
            r4 = null;

            if (this$0.mActivity != null)
            {
                r4 = this$0.mActivity.mFragments.mNoTransactionsBecause;
                this$0.mActivity.mFragments.mNoTransactionsBecause = "onLoadFinished";
            }

            label_1:
            {
                label_0:
                {
                    try
                    {
                        z0 = LoaderManagerImpl.DEBUG;
                    }
                    catch (Throwable $r35)
                    {
                        break label_0;
                    }

                    if (z0 != false)
                    {
                        try
                        {
                            r15 = "LoaderManager";
                        }
                        catch (Throwable $r35)
                        {
                            break label_0;
                        }

                        try
                        {
                            r16 = new StringBuilder();
                        }
                        catch (Throwable $r35)
                        {
                            break label_0;
                        }

                        r6 = "  onLoadFinished in ";

                        try
                        {
                            $r17 = r16.append(r6);
                        }
                        catch (Throwable $r35)
                        {
                            break label_0;
                        }

                        try
                        {
                            $r19 = $r17.append(r1);
                        }
                        catch (Throwable $r35)
                        {
                            break label_0;
                        }

                        try
                        {
                            r21 = ": ";
                        }
                        catch (Throwable $r35)
                        {
                            break label_0;
                        }

                        r22 = $r19.append(r21);

                        try
                        {
                            $r23 = r1.dataToString(r2);
                        }
                        catch (Throwable $r35)
                        {
                            break label_0;
                        }

                        try
                        {
                            $r25 = r22.append($r23);
                        }
                        catch (Throwable $r35)
                        {
                            break label_0;
                        }

                        try
                        {
                            $r27 = $r25.toString();
                        }
                        catch (Throwable $r35)
                        {
                            break label_0;
                        }

                        try
                        {
                            Log.v(r15, $r27);
                        }
                        catch (Throwable $r35)
                        {
                            break label_0;
                        }
                    }

                    r29 = mCallbacks;

                    try
                    {
                        r29.onLoadFinished(r1, r2);
                        break label_1;
                    }
                    catch (Throwable $r35)
                    {
                    }
                } //end label_0:


                if (this$0.mActivity != null)
                {
                    this$0.mActivity.mFragments.mNoTransactionsBecause = r4;
                }

                throw $r35;
            } //end label_1:


            if (this$0.mActivity != null)
            {
                this$0.mActivity.mFragments.mNoTransactionsBecause = r4;
            }

            mDeliveredData = true;
        }
    }

    void destroy()
    {

        Object n0;
        boolean z2;
        String r3;
        LoaderManager$LoaderCallbacks r25;
        Loader r26;
        n0 = null;

        if (LoaderManagerImpl.DEBUG != false)
        {
            Log.v("LoaderManager", (new StringBuilder()).append("  Destroying: ").append(this).toString());
        }

        mDestroyed = true;
        z2 = mDeliveredData;
        mDeliveredData = false;

        if (mCallbacks != null)
        {
            if (mLoader != null)
            {
                if (mHaveData != false)
                {
                    if (z2 != false)
                    {
                        if (LoaderManagerImpl.DEBUG != false)
                        {
                            Log.v("LoaderManager", (new StringBuilder()).append("  Reseting: ").append(this).toString());
                        }

                        r3 = null;

                        if (this$0.mActivity != null)
                        {
                            r3 = this$0.mActivity.mFragments.mNoTransactionsBecause;
                            this$0.mActivity.mFragments.mNoTransactionsBecause = "onLoaderReset";
                        }

                        label_3:
                        {
                            label_2:
                            {
                                try
                                {
                                    r25 = mCallbacks;
                                }
                                catch (Throwable $r37)
                                {
                                    break label_2;
                                }

                                r26 = mLoader;

                                try
                                {
                                    r25.onLoaderReset(r26);
                                    break label_3;
                                }
                                catch (Throwable $r37)
                                {
                                }
                            } //end label_2:


                            if (this$0.mActivity != null)
                            {
                                this$0.mActivity.mFragments.mNoTransactionsBecause = r3;
                            }

                            throw $r37;
                        } //end label_3:


                        if (this$0.mActivity != null)
                        {
                            this$0.mActivity.mFragments.mNoTransactionsBecause = r3;
                        }
                    }
                }
            }
        }

        mCallbacks = n0;
        mData = n0;
        mHaveData = false;

        if (mLoader != null)
        {
            if (mListenerRegistered != false)
            {
                mListenerRegistered = false;
                mLoader.unregisterListener(this);
            }

            mLoader.reset();
        }

        if (mPendingLoader != null)
        {
            mPendingLoader.destroy();
        }
    }

    public void dump(String  r1, FileDescriptor  r2, PrintWriter  r3, String[]  r4)
    {


        r3.print(r1);
        r3.print("mId=");
        r3.print(mId);
        r3.print(" mArgs=");
        r3.println(mArgs);
        r3.print(r1);
        r3.print("mCallbacks=");
        r3.println(mCallbacks);
        r3.print(r1);
        r3.print("mLoader=");
        r3.println(mLoader);

        if (mLoader != null)
        {
            mLoader.dump((new StringBuilder()).append(r1).append("  ").toString(), r2, r3, r4);
        }

        label_4:
        {
            if (mHaveData == false)
            {
                if (mDeliveredData == false)
                {
                    break label_4;
                }
            }

            r3.print(r1);
            r3.print("mHaveData=");
            r3.print(mHaveData);
            r3.print("  mDeliveredData=");
            r3.println(mDeliveredData);
            r3.print(r1);
            r3.print("mData=");
            r3.println(mData);
        } //end label_4:


        r3.print(r1);
        r3.print("mStarted=");
        r3.print(mStarted);
        r3.print(" mReportNextStart=");
        r3.print(mReportNextStart);
        r3.print(" mDestroyed=");
        r3.println(mDestroyed);
        r3.print(r1);
        r3.print("mRetaining=");
        r3.print(mRetaining);
        r3.print(" mRetainingStarted=");
        r3.print(mRetainingStarted);
        r3.print(" mListenerRegistered=");
        r3.println(mListenerRegistered);

        if (mPendingLoader != null)
        {
            r3.print(r1);
            r3.println("Pending Loader ");
            r3.print(mPendingLoader);
            r3.println(":");
            mPendingLoader.dump((new StringBuilder()).append(r1).append("  ").toString(), r2, r3, r4);
        }
    }

    void finishRetain()
    {


        if (mRetaining != false)
        {
            if (LoaderManagerImpl.DEBUG != false)
            {
                Log.v("LoaderManager", (new StringBuilder()).append("  Finished Retaining: ").append(this).toString());
            }

            mRetaining = false;

            if (mStarted != mRetainingStarted)
            {
                if (mStarted == false)
                {
                    this.stop();
                }
            }
        }

        if (mStarted != false)
        {
            if (mHaveData != false)
            {
                if (mReportNextStart == false)
                {
                    this.callOnLoadFinished(mLoader, mData);
                }
            }
        }
    }

    public void onLoadComplete(Loader  r1, Object  r2)
    {

        Object n0;
        LoaderManagerImpl$LoaderInfo r5, r30;
        n0 = null;

        if (LoaderManagerImpl.DEBUG != false)
        {
            Log.v("LoaderManager", (new StringBuilder()).append("onLoadComplete: ").append(this).toString());
        }

        if (mDestroyed == false)
        {
            if (this$0.mLoaders.get(mId) == this)
            {
                r5 = mPendingLoader;

                if (r5 == null)
                {
                    label_5:
                    {
                        if (mData == r2)
                        {
                            if (mHaveData != false)
                            {
                                break label_5;
                            }
                        }

                        mData = r2;
                        mHaveData = true;

                        if (mStarted != false)
                        {
                            this.callOnLoadFinished(r1, r2);
                        }
                    } //end label_5:


                    r30 = (LoaderManagerImpl$LoaderInfo) this$0.mInactiveLoaders.get(mId);

                    if (r30 != null)
                    {
                        if (r30 != this)
                        {
                            r30.mDeliveredData = false;
                            r30.destroy();
                            this$0.mInactiveLoaders.remove(mId);
                        }
                    }

                    if (this$0.mActivity != null)
                    {
                        if (this$0.hasRunningLoaders() == false)
                        {
                            this$0.mActivity.mFragments.startPendingDeferredFragments();
                        }
                    }
                }
                else
                {
                    if (LoaderManagerImpl.DEBUG != false)
                    {
                        Log.v("LoaderManager", (new StringBuilder()).append("  Switching to pending loader: ").append(r5).toString());
                    }

                    mPendingLoader = n0;
                    this$0.mLoaders.put(mId, n0);
                    this.destroy();
                    this$0.installLoader(r5);
                }
            }
            else
            {
                if (LoaderManagerImpl.DEBUG != false)
                {
                    Log.v("LoaderManager", "  Ignoring load complete -- not active");
                }
            }
        }
        else
        {
            if (LoaderManagerImpl.DEBUG != false)
            {
                Log.v("LoaderManager", "  Ignoring load complete -- destroyed");
            }
        }
    }

    void reportStart()
    {


        if (mStarted != false)
        {
            if (mReportNextStart != false)
            {
                mReportNextStart = false;

                if (mHaveData != false)
                {
                    this.callOnLoadFinished(mLoader, mData);
                }
            }
        }
    }

    void retain()
    {


        if (LoaderManagerImpl.DEBUG != false)
        {
            Log.v("LoaderManager", (new StringBuilder()).append("  Retaining: ").append(this).toString());
        }

        mRetaining = true;
        mRetainingStarted = mStarted;
        mStarted = false;
        mCallbacks = null;
    }

    void start()
    {


        label_6:
        {
            if (mRetaining != false)
            {
                if (mRetainingStarted != false)
                {
                    mStarted = true;
                    break label_6;
                }
            }

            if (mStarted == false)
            {
                mStarted = true;

                if (LoaderManagerImpl.DEBUG != false)
                {
                    Log.v("LoaderManager", (new StringBuilder()).append("  Starting: ").append(this).toString());
                }

                if (mLoader == null)
                {
                    if (mCallbacks != null)
                    {
                        mLoader = mCallbacks.onCreateLoader(mId, mArgs);
                    }
                }

                if (mLoader != null)
                {
                    if (mLoader.getClass().isMemberClass() != false)
                    {
                        if (Modifier.isStatic(mLoader.getClass().getModifiers()) == false)
                        {
                            throw new IllegalArgumentException((new StringBuilder()).append("Object returned from onCreateLoader must not be a non-static inner member class: ").append(mLoader).toString());
                        }
                    }

                    if (mListenerRegistered == false)
                    {
                        mLoader.registerListener(mId, this);
                        mListenerRegistered = true;
                    }

                    mLoader.startLoading();
                }
            }
        } //end label_6:

    }

    void stop()
    {


        if (LoaderManagerImpl.DEBUG != false)
        {
            Log.v("LoaderManager", (new StringBuilder()).append("  Stopping: ").append(this).toString());
        }

        mStarted = false;

        if (mRetaining == false)
        {
            if (mLoader != null)
            {
                if (mListenerRegistered != false)
                {
                    mListenerRegistered = false;
                    mLoader.unregisterListener(this);
                    mLoader.stopLoading();
                }
            }
        }
    }

    public String toString()
    {

        StringBuilder r1;
        r1 = new StringBuilder(64);
        r1.append("LoaderInfo{");
        r1.append(Integer.toHexString(System.identityHashCode(this)));
        r1.append(" #");
        r1.append(mId);
        r1.append(" : ");
        DebugUtils.buildShortClassTag(mLoader, r1);
        r1.append("}}");
        return r1.toString();
    }
}
