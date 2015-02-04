package android.support.v4.content;

import android.os.Handler;
import android.os.Message;

class ModernAsyncTask$InternalHandler extends Handler
{

    private ModernAsyncTask$InternalHandler()
    {


        this.<init>();
    }

    ModernAsyncTask$InternalHandler(ModernAsyncTask$1  r1)
    {
        this();


        this.<init>();
    }

    public void handleMessage(Message  r1)
    {

        ModernAsyncTask$AsyncTaskResult r4;
        r4 = (ModernAsyncTask$AsyncTaskResult) r1.obj;

        label_0:
        switch (r1.what)
        {
            case 1:
                ModernAsyncTask.access$500(r4.mTask, r4.mData[0]);
                break label_0;

            case 2:
                r4.mTask.onProgressUpdate(r4.mData);
                break label_0;

            default:
                break label_0;
        }
    }
}
