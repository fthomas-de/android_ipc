package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.View;

class SwipeRefreshLayout$1 extends Animation
{
    final SwipeRefreshLayout this$0;

    SwipeRefreshLayout$1(SwipeRefreshLayout  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void applyTransformation(float  f0, Transformation  r1)
    {

        int i0, i1, i2;
        i0 = 0;

        if (SwipeRefreshLayout.access$000(this$0) != SwipeRefreshLayout.access$100(this$0))
        {
            i0 = SwipeRefreshLayout.access$000(this$0) + (int) ((float) (SwipeRefreshLayout.access$100(this$0) - SwipeRefreshLayout.access$000(this$0)) * f0);
        }

        i1 = i0 - SwipeRefreshLayout.access$200(this$0).getTop();
        i2 = SwipeRefreshLayout.access$200(this$0).getTop();

        if (i1 + i2 < 0)
        {
            i1 = 0 - i2;
        }

        SwipeRefreshLayout.access$300(this$0, i1);
    }
}
