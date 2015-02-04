package android.support.v4.print;

import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter$LayoutResultCallback;
import android.net.Uri;
import android.graphics.Bitmap;
import java.io.FileNotFoundException;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo$Builder;

class PrintHelperKitkat$2$1 extends AsyncTask
{
    final PrintHelperKitkat$2 this$1;
    final CancellationSignal val$cancellationSignal;
    final PrintDocumentAdapter$LayoutResultCallback val$layoutResultCallback;
    final PrintAttributes val$newPrintAttributes;
    final PrintAttributes val$oldPrintAttributes;

    PrintHelperKitkat$2$1(PrintHelperKitkat$2  r1, CancellationSignal  r2, PrintAttributes  r3, PrintAttributes  r4, PrintDocumentAdapter$LayoutResultCallback  r5)
    {


        this$1 = r1;
        val$cancellationSignal = r2;
        val$newPrintAttributes = r3;
        val$oldPrintAttributes = r4;
        val$layoutResultCallback = r5;
        this.<init>();
    }

    protected Bitmap doInBackground(Uri[]  r1)
    {

        PrintHelperKitkat$2 r2, r3;
        PrintHelperKitkat r4;
        Uri r5;
        Bitmap $r6, r7;
        label_2:
        {
            label_1:
            {
                label_0:
                {
                    r2 = this$1;

                    r4 = r2.this$0;

                    r3 = this$1;

                    r5 = r3.val$imageFile;

                    try
                    {
                        $r6 = PrintHelperKitkat.access$200(r4, r5, 3500);
                        break label_1;
                    }
                    catch (FileNotFoundException $r8)
                    {
                    }
                } //end label_0:


                r7 = null;
                break label_2;
            } //end label_1:


            r7 = $r6;
        } //end label_2:


        return r7;
    }

    protected Object doInBackground(Object[]  r1)
    {


        return this.doInBackground((Uri[]) r1);
    }

    protected void onCancelled(Bitmap  r1)
    {


        val$layoutResultCallback.onLayoutCancelled();
    }

    protected void onCancelled(Object  r1)
    {


        this.onCancelled((Bitmap) r1);
    }

    protected void onPostExecute(Bitmap  r1)
    {

        boolean z0;
        PrintDocumentInfo r4;
        z0 = true;
        this.onPostExecute(r1);
        this$1.mBitmap = r1;

        if (r1 == null)
        {
            val$layoutResultCallback.onLayoutFailed(null);
        }
        else
        {
            r4 = (new PrintDocumentInfo$Builder(this$1.val$jobName)).setContentType((int) 1).setPageCount((int) 1).build();

            if (val$newPrintAttributes.equals(val$oldPrintAttributes) != false)
            {
                z0 = false;
            }

            val$layoutResultCallback.onLayoutFinished(r4, z0);
        }
    }

    protected void onPostExecute(Object  r1)
    {


        this.onPostExecute((Bitmap) r1);
    }

    protected void onPreExecute()
    {


        val$cancellationSignal.setOnCancelListener((CancellationSignal$OnCancelListener) new PrintHelperKitkat$2$1$1(this));
    }
}
