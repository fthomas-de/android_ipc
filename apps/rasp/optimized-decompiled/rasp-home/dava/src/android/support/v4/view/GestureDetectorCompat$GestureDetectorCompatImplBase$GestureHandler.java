package android.support.v4.view;

import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector$OnDoubleTapListener;

class GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler extends Handler
{
    final GestureDetectorCompat$GestureDetectorCompatImplBase this$0;

    GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler(GestureDetectorCompat$GestureDetectorCompatImplBase  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler(GestureDetectorCompat$GestureDetectorCompatImplBase  r1, Handler  r2)
    {
        super(r2.getLooper());


        this$0 = r1;
        this.<init>(r2.getLooper());
    }

    public void handleMessage(Message  r1)
    {


        label_0:
        switch (r1.what)
        {
            case 1:
                GestureDetectorCompat$GestureDetectorCompatImplBase.access$100(this$0).onShowPress(GestureDetectorCompat$GestureDetectorCompatImplBase.access$000(this$0));
                break label_0;

            case 2:
                GestureDetectorCompat$GestureDetectorCompatImplBase.access$200(this$0);
                break label_0;

            case 3:
                if (GestureDetectorCompat$GestureDetectorCompatImplBase.access$300(this$0) == null)
                {
                    break label_0;
                }
                else
                {
                    if (GestureDetectorCompat$GestureDetectorCompatImplBase.access$400(this$0) != false)
                    {
                        GestureDetectorCompat$GestureDetectorCompatImplBase.access$502(this$0, true);
                        break label_0;
                    }
                    else
                    {
                        GestureDetectorCompat$GestureDetectorCompatImplBase.access$300(this$0).onSingleTapConfirmed(GestureDetectorCompat$GestureDetectorCompatImplBase.access$000(this$0));
                        break label_0;
                    }
                }

            default:
                throw new RuntimeException((new StringBuilder()).append("Unknown message ").append(r1).toString());
        }
    }
}
