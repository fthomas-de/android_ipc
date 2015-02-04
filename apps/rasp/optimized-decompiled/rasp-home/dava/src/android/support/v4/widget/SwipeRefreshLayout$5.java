package android.support.v4.widget;


class SwipeRefreshLayout$5 implements java.lang.Runnable
{
    final SwipeRefreshLayout this$0;

    SwipeRefreshLayout$5(SwipeRefreshLayout  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void run()
    {


        SwipeRefreshLayout.access$902(this$0, true);
        SwipeRefreshLayout.access$1100(this$0, SwipeRefreshLayout.access$700(this$0) + this$0.getPaddingTop(), SwipeRefreshLayout.access$1000(this$0));
    }
}
