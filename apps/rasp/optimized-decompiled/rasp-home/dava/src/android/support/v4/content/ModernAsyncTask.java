package android.support.v4.content;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import android.os.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;

abstract class ModernAsyncTask
{
    private static final int CORE_POOL_SIZE = 5;
    private static final int KEEP_ALIVE = 1;
    private static final String LOG_TAG = "AsyncTask";
    private static final int MAXIMUM_POOL_SIZE = 128;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    public static final Executor THREAD_POOL_EXECUTOR;
    private static volatile Executor sDefaultExecutor;
    private static final ModernAsyncTask$InternalHandler sHandler;
    private static final BlockingQueue sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;
    private final FutureTask mFuture;
    private volatile ModernAsyncTask$Status mStatus;
    private final AtomicBoolean mTaskInvoked;
    private final ModernAsyncTask$WorkerRunnable mWorker;

    static
    {


        sThreadFactory = new ModernAsyncTask$1();
        sPoolWorkQueue = new LinkedBlockingQueue(10);
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
        sHandler = new ModernAsyncTask$InternalHandler(null);
        sDefaultExecutor = THREAD_POOL_EXECUTOR;
    }

    public ModernAsyncTask()
    {


        this.<init>();
        mStatus = ModernAsyncTask$Status.PENDING;
        mTaskInvoked = new AtomicBoolean();
        mWorker = new ModernAsyncTask$2(this);
        mFuture = new ModernAsyncTask$3(this, mWorker);
    }

    static AtomicBoolean access$200(ModernAsyncTask  r0)
    {


        return r0.mTaskInvoked;
    }

    static Object access$300(ModernAsyncTask  r0, Object  r1)
    {


        return r0.postResult(r1);
    }

    static void access$400(ModernAsyncTask  r0, Object  r1)
    {


        r0.postResultIfNotInvoked(r1);
    }

    static void access$500(ModernAsyncTask  r0, Object  r1)
    {


        r0.finish(r1);
    }

    public final boolean cancel(boolean  z0)
    {


        return mFuture.cancel(z0);
    }

    protected abstract java.lang.Object doInBackground(java.lang.Object[]  r0);

    public final ModernAsyncTask execute(Object[]  r1)
    {


        return this.executeOnExecutor(sDefaultExecutor, r1);
    }

    public static void execute(Runnable  r0)
    {


        sDefaultExecutor.execute(r0);
    }

    public final ModernAsyncTask executeOnExecutor(Executor  r1, Object[]  r2)
    {


        label_0:
        if (mStatus != ModernAsyncTask$Status.PENDING)
        {
            switch (ModernAsyncTask$4.$SwitchMap$android$support$v4$content$ModernAsyncTask$Status[mStatus.ordinal()])
            {
                case 1:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");

                case 2:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");

                default:
                    break label_0;
            }
        }

        mStatus = ModernAsyncTask$Status.RUNNING;
        this.onPreExecute();
        mWorker.mParams = r2;
        r1.execute(mFuture);
        return this;
    }

    private void finish(Object  r1)
    {


        if (this.isCancelled() == false)
        {
            this.onPostExecute(r1);
        }
        else
        {
            this.onCancelled(r1);
        }

        mStatus = ModernAsyncTask$Status.FINISHED;
    }

    public final Object get() throws java.lang.InterruptedException, java.util.concurrent.ExecutionException
    {


        return mFuture.get();
    }

    public final Object get(long  l0, TimeUnit  r1) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException
    {


        return mFuture.get(l0, r1);
    }

    public final ModernAsyncTask$Status getStatus()
    {


        return mStatus;
    }

    public static void init()
    {


        sHandler.getLooper();
    }

    public final boolean isCancelled()
    {


        return mFuture.isCancelled();
    }

    protected void onCancelled()
    {

    }

    protected void onCancelled(Object  r1)
    {


        this.onCancelled();
    }

    protected void onPostExecute(Object  r1)
    {

    }

    protected void onPreExecute()
    {

    }

    protected void onProgressUpdate(Object[]  r1)
    {

    }

    private Object postResult(Object  r1)
    {

        Object[] r4;
        r4 = new Object[1];
        r4[0] = r1;
        sHandler.obtainMessage(1, new ModernAsyncTask$AsyncTaskResult(this, r4)).sendToTarget();
        return r1;
    }

    private void postResultIfNotInvoked(Object  r1)
    {


        if (mTaskInvoked.get() == false)
        {
            this.postResult(r1);
        }
    }

    protected final void publishProgress(Object[]  r1)
    {


        if (this.isCancelled() == false)
        {
            sHandler.obtainMessage(2, new ModernAsyncTask$AsyncTaskResult(this, r1)).sendToTarget();
        }
    }

    public static void setDefaultExecutor(Executor  r0)
    {


        sDefaultExecutor = r0;
    }
}
