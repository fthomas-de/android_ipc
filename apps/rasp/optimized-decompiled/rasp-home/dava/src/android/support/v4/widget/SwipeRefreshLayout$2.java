package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class SwipeRefreshLayout$2 extends Animation
{
    final SwipeRefreshLayout this$0;

    SwipeRefreshLayout$2(SwipeRefreshLayout  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void applyTransformation(float  f0, Transformation  r1)
    {

        float f2;
        f2 = SwipeRefreshLayout.access$400(this$0) + (0.0F - SwipeRefreshLayout.access$400(this$0)) * f0;
        SwipeRefreshLayout.access$500(this$0).setTriggerPercentage(f2);
    }
}
