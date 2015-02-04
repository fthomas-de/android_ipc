package android.support.v4.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class LocalBroadcastManager$1 extends Handler
{
    final LocalBroadcastManager this$0;

    LocalBroadcastManager$1(LocalBroadcastManager  r1, Looper  r2)
    {
        super(r2);


        this$0 = r1;
        this.<init>(r2);
    }

    public void handleMessage(Message  r1)
    {


        label_0:
        switch (r1.what)
        {
            case 1:
                LocalBroadcastManager.access$000(this$0);
                break label_0;

            default:
                this.handleMessage(r1);
                break label_0;
        }
    }
}
