package android.support.v4.content;

import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Process;

class ModernAsyncTask$2 extends ModernAsyncTask$WorkerRunnable
{
    final ModernAsyncTask this$0;

    ModernAsyncTask$2(ModernAsyncTask  r1)
    {
        super(null);


        this$0 = r1;
        this.<init>(null);
    }

    public Object call() throws java.lang.Exception
    {


        ModernAsyncTask.access$200(this$0).set(true);
        Process.setThreadPriority(10);
        return ModernAsyncTask.access$300(this$0, this$0.doInBackground(mParams));
    }
}
