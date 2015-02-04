package android.support.v4.app;

import android.os.Handler;
import android.os.Message;

class FragmentActivity$1 extends Handler
{
    final FragmentActivity this$0;

    FragmentActivity$1(FragmentActivity  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void handleMessage(Message  r1)
    {


        label_0:
        switch (r1.what)
        {
            case 1:
                if (this$0.mStopped == false)
                {
                    break label_0;
                }
                else
                {
                    this$0.doReallyStop(false);
                    break label_0;
                }

            case 2:
                this$0.onResumeFragments();
                this$0.mFragments.execPendingActions();
                break label_0;

            default:
                this.handleMessage(r1);
                break label_0;
        }
    }
}
