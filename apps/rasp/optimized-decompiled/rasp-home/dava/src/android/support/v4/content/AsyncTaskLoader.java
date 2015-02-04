package android.support.v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import android.support.v4.util.TimeUtils;
import java.util.concurrent.CountDownLatch;

public abstract class AsyncTaskLoader extends Loader
{
    static final boolean DEBUG = false;
    static final String TAG = "AsyncTaskLoader";
    volatile AsyncTaskLoader$LoadTask mCancellingTask;
    Handler mHandler;
    long mLastLoadCompleteTime;
    volatile AsyncTaskLoader$LoadTask mTask;
    long mUpdateThrottle;

    public AsyncTaskLoader(Context  r1)
    {
        super(r1);


        this.<init>(r1);
        mLastLoadCompleteTime = 4294957296L;
    }

    public boolean cancelLoad()
    {

        Object n0;
        boolean z0;
        n0 = null;
        z0 = false;

        if (mTask != null)
        {
            if (mCancellingTask == null)
            {
                if (mTask.waiting == false)
                {
                    z0 = mTask.cancel(false);

                    if (z0 != false)
                    {
                        mCancellingTask = mTask;
                    }

                    mTask = n0;
                }
                else
                {
                    mTask.waiting = false;
                    mHandler.removeCallbacks(mTask);
                    mTask = n0;
                }
            }
            else
            {
                if (mTask.waiting != false)
                {
                    mTask.waiting = false;
                    mHandler.removeCallbacks(mTask);
                }

                mTask = n0;
            }
        }

        return z0;
    }

    void dispatchOnCancelled(AsyncTaskLoader$LoadTask  r1, Object  r2)
    {


        this.onCanceled(r2);

        if (mCancellingTask == r1)
        {
            this.rollbackContentChanged();
            mLastLoadCompleteTime = SystemClock.uptimeMillis();
            mCancellingTask = null;
            this.executePendingTask();
        }
    }

    void dispatchOnLoadComplete(AsyncTaskLoader$LoadTask  r1, Object  r2)
    {


        if (mTask == r1)
        {
            if (this.isAbandoned() == false)
            {
                this.commitContentChanged();
                mLastLoadCompleteTime = SystemClock.uptimeMillis();
                mTask = null;
                this.deliverResult(r2);
            }
            else
            {
                this.onCanceled(r2);
            }
        }
        else
        {
            this.dispatchOnCancelled(r1, r2);
        }
    }

    public void dump(String  r1, FileDescriptor  r2, PrintWriter  r3, String[]  r4)
    {


        this.dump(r1, r2, r3, r4);

        if (mTask != null)
        {
            r3.print(r1);
            r3.print("mTask=");
            r3.print(mTask);
            r3.print(" waiting=");
            r3.println(mTask.waiting);
        }

        if (mCancellingTask != null)
        {
            r3.print(r1);
            r3.print("mCancellingTask=");
            r3.print(mCancellingTask);
            r3.print(" waiting=");
            r3.println(mCancellingTask.waiting);
        }

        if (mUpdateThrottle - 0L != 0)
        {
            r3.print(r1);
            r3.print("mUpdateThrottle=");
            TimeUtils.formatDuration(mUpdateThrottle, r3);
            r3.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(mLastLoadCompleteTime, SystemClock.uptimeMillis(), r3);
            r3.println();
        }
    }

    void executePendingTask()
    {


        label_0:
        if (mCancellingTask == null)
        {
            if (mTask != null)
            {
                if (mTask.waiting != false)
                {
                    mTask.waiting = false;
                    mHandler.removeCallbacks(mTask);
                }

                if (mUpdateThrottle - 0L > 0)
                {
                    if (SystemClock.uptimeMillis() - (mLastLoadCompleteTime + mUpdateThrottle) < 0)
                    {
                        mTask.waiting = true;
                        mHandler.postAtTime(mTask, mLastLoadCompleteTime + mUpdateThrottle);
                        break label_0;
                    }
                }

                mTask.executeOnExecutor(ModernAsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
            }
        }
    }

    public abstract java.lang.Object loadInBackground();

    public void onCanceled(Object  r1)
    {

    }

    protected void onForceLoad()
    {


        this.onForceLoad();
        this.cancelLoad();
        mTask = new AsyncTaskLoader$LoadTask(this);
        this.executePendingTask();
    }

    protected Object onLoadInBackground()
    {


        return this.loadInBackground();
    }

    public void setUpdateThrottle(long  l0)
    {


        mUpdateThrottle = l0;

        if (l0 - 0L != 0)
        {
            mHandler = new Handler();
        }
    }

    public void waitForLoader()
    {

        AsyncTaskLoader$LoadTask r1;
        CountDownLatch $r3;
        r1 = mTask;

        label_2:
        if (r1 != null)
        {
            label_1:
            {
                $r3 = AsyncTaskLoader$LoadTask.access$000(r1);

                try
                {
                    $r3.await();
                    break label_2;
                }
                catch (InterruptedException $r4)
                {
                }
            } //end label_1:

        }
    }
}
