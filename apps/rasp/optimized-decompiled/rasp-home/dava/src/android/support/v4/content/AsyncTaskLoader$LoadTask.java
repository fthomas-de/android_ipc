package android.support.v4.content;

import java.util.concurrent.CountDownLatch;

final class AsyncTaskLoader$LoadTask extends ModernAsyncTask implements java.lang.Runnable
{
    private CountDownLatch done;
    Object result;
    final AsyncTaskLoader this$0;
    boolean waiting;

    AsyncTaskLoader$LoadTask(AsyncTaskLoader  r1)
    {


        this$0 = r1;
        this.<init>();
        done = new CountDownLatch(1);
    }

    static CountDownLatch access$000(AsyncTaskLoader$LoadTask  r0)
    {


        return r0.done;
    }

    protected Object doInBackground(Object[]  r1)
    {


        return this.doInBackground((Void[]) r1);
    }

    protected Object doInBackground(Void[]  r1)
    {


        result = this$0.onLoadInBackground();
        return result;
    }

    protected void onCancelled()
    {

        AsyncTaskLoader r1;
        Object r2;
        label_1:
        {
            label_0:
            {
                try
                {
                    r1 = this$0;
                }
                catch (Throwable $r4)
                {
                    break label_0;
                }

                r2 = result;

                try
                {
                    r1.dispatchOnCancelled(this, r2);
                    break label_1;
                }
                catch (Throwable $r4)
                {
                }
            } //end label_0:


            done.countDown();
            throw $r4;
        } //end label_1:


        done.countDown();
    }

    protected void onPostExecute(Object  r1)
    {

        AsyncTaskLoader r2;
        try
        {
            r2 = this$0;
        }
        catch (Throwable $r5)
        {
            done.countDown();
            throw $r5;
        }

        r2.dispatchOnLoadComplete(this, r1);
        done.countDown();
    }

    public void run()
    {


        waiting = false;
        this$0.executePendingTask();
    }
}
