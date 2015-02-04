package android.support.v4.content;

import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
import android.util.Log;

class ModernAsyncTask$3 extends FutureTask
{
    final ModernAsyncTask this$0;

    ModernAsyncTask$3(ModernAsyncTask  r1, Callable  r2)
    {
        super(r2);


        this$0 = r1;
        this.<init>(r2);
    }

    protected void done()
    {

        ModernAsyncTask r2;
        Object $r7;
        label_5:
        {
            label_4:
            {
                label_3:
                {
                    label_2:
                    {
                        label_1:
                        {
                            label_0:
                            {
                                try
                                {
                                    $r7 = this.get();
                                }
                                catch (InterruptedException $r8)
                                {
                                    break label_0;
                                }
                                catch (ExecutionException $r10)
                                {
                                    break label_1;
                                }
                                catch (CancellationException $r13)
                                {
                                    break label_2;
                                }
                                catch (Throwable $r16)
                                {
                                    break label_3;
                                }

                                try
                                {
                                    r2 = this$0;
                                    break label_4;
                                }
                                catch (CancellationException $r13)
                                {
                                    break label_2;
                                }
                                catch (Throwable $r16)
                                {
                                    break label_3;
                                }
                            } //end label_0:


                            Log.w("AsyncTask", $r8);
                            break label_5;
                        } //end label_1:


                        throw new RuntimeException("An error occured while executing doInBackground()", $r10.getCause());
                    } //end label_2:


                    ModernAsyncTask.access$400(this$0, null);
                    break label_5;
                } //end label_3:


                throw new RuntimeException("An error occured while executing doInBackground()", $r16);
            } //end label_4:


            ModernAsyncTask.access$400(r2, $r7);
        } //end label_5:

    }
}
