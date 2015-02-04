package android.support.v4.print;

import android.os.CancellationSignal$OnCancelListener;

class PrintHelperKitkat$2$1$1 implements android.os.CancellationSignal$OnCancelListener
{
    final PrintHelperKitkat$2$1 this$2;

    PrintHelperKitkat$2$1$1(PrintHelperKitkat$2$1  r1)
    {


        this$2 = r1;
        this.<init>();
    }

    public void onCancel()
    {


        PrintHelperKitkat$2.access$100(this$2.this$1);
        this$2.cancel(false);
    }
}
