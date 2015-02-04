package android.support.v4.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.util.Log;

class TransportMediatorJellybeanMR2$3 extends BroadcastReceiver
{
    final TransportMediatorJellybeanMR2 this$0;

    TransportMediatorJellybeanMR2$3(TransportMediatorJellybeanMR2  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void onReceive(Context  r1, Intent  r2)
    {

        String r3;
        Parcelable r4;
        KeyEvent r6;
        TransportMediatorJellybeanMR2 r7;
        TransportMediatorCallback r8;
        label_2:
        {
            label_1:
            {
                label_0:
                {
                    try
                    {
                        r3 = "android.intent.extra.KEY_EVENT";
                    }
                    catch (ClassCastException $r9)
                    {
                        break label_0;
                    }

                    r4 = r2.getParcelableExtra(r3);

                    try
                    {
                        r6 = (KeyEvent) r4;
                    }
                    catch (ClassCastException $r9)
                    {
                        break label_0;
                    }

                    r7 = this$0;

                    try
                    {
                        r8 = r7.mTransportCallback;
                        break label_1;
                    }
                    catch (ClassCastException $r9)
                    {
                    }
                } //end label_0:


                Log.w("TransportController", $r9);
                break label_2;
            } //end label_1:


            r8.handleKey(r6);
        } //end label_2:

    }
}
