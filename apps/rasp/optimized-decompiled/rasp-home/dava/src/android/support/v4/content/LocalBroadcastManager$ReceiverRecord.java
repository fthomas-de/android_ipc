package android.support.v4.content;

import android.content.IntentFilter;
import android.content.BroadcastReceiver;

class LocalBroadcastManager$ReceiverRecord
{
    boolean broadcasting;
    final IntentFilter filter;
    final BroadcastReceiver receiver;

    LocalBroadcastManager$ReceiverRecord(IntentFilter  r1, BroadcastReceiver  r2)
    {


        this.<init>();
        filter = r1;
        receiver = r2;
    }

    public String toString()
    {

        StringBuilder r1;
        r1 = new StringBuilder(128);
        r1.append("Receiver{");
        r1.append(receiver);
        r1.append(" filter=");
        r1.append(filter);
        r1.append("}");
        return r1.toString();
    }
}
