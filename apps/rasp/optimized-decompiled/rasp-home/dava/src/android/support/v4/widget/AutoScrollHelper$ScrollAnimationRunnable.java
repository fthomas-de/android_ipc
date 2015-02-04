package android.support.v4.widget;

import android.support.v4.view.ViewCompat;

class AutoScrollHelper$ScrollAnimationRunnable implements java.lang.Runnable
{
    final AutoScrollHelper this$0;

    private AutoScrollHelper$ScrollAnimationRunnable(AutoScrollHelper  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    AutoScrollHelper$ScrollAnimationRunnable(AutoScrollHelper  r1, AutoScrollHelper$1  r2)
    {
        this(r1);


        this.<init>(r1);
    }

    public void run()
    {

        AutoScrollHelper$ClampedScroller r2;
        int i0, i1;
        label_0:
        if (AutoScrollHelper.access$100(this$0) != false)
        {
            if (AutoScrollHelper.access$200(this$0) != false)
            {
                AutoScrollHelper.access$202(this$0, false);
                AutoScrollHelper.access$300(this$0).start();
            }

            r2 = AutoScrollHelper.access$300(this$0);

            if (r2.isFinished() == false)
            {
                if (AutoScrollHelper.access$400(this$0) != false)
                {
                    if (AutoScrollHelper.access$500(this$0) != false)
                    {
                        AutoScrollHelper.access$502(this$0, false);
                        AutoScrollHelper.access$600(this$0);
                    }

                    r2.computeScrollDelta();
                    i0 = r2.getDeltaX();
                    i1 = r2.getDeltaY();
                    this$0.scrollTargetBy(i0, i1);
                    ViewCompat.postOnAnimation(AutoScrollHelper.access$700(this$0), this);
                    break label_0;
                }
            }

            AutoScrollHelper.access$102(this$0, false);
        }
    }
}
