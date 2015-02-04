package android.support.v4.widget;

import android.view.animation.Animation;

class SwipeRefreshLayout$6 implements java.lang.Runnable
{
    final SwipeRefreshLayout this$0;

    SwipeRefreshLayout$6(SwipeRefreshLayout  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void run()
    {


        SwipeRefreshLayout.access$902(this$0, true);

        if (SwipeRefreshLayout.access$500(this$0) != null)
        {
            SwipeRefreshLayout.access$402(this$0, SwipeRefreshLayout.access$800(this$0));
            SwipeRefreshLayout.access$1300(this$0).setDuration((long) SwipeRefreshLayout.access$1200(this$0));
            SwipeRefreshLayout.access$1300(this$0).setAnimationListener(SwipeRefreshLayout.access$1400(this$0));
            SwipeRefreshLayout.access$1300(this$0).reset();
            SwipeRefreshLayout.access$1300(this$0).setInterpolator(SwipeRefreshLayout.access$1500(this$0));
            this$0.startAnimation(SwipeRefreshLayout.access$1300(this$0));
        }

        SwipeRefreshLayout.access$1100(this$0, SwipeRefreshLayout.access$700(this$0) + this$0.getPaddingTop(), SwipeRefreshLayout.access$1000(this$0));
    }
}
