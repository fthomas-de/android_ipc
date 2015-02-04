package android.support.v4.app;

import android.widget.ListView;

class ListFragment$1 implements java.lang.Runnable
{
    final ListFragment this$0;

    ListFragment$1(ListFragment  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void run()
    {


        this$0.mList.focusableViewAvailable(this$0.mList);
    }
}
