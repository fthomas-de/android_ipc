package android.support.v4.app;

import android.view.View;

class Fragment$1 implements android.support.v4.app.FragmentContainer
{
    final Fragment this$0;

    Fragment$1(Fragment  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public View findViewById(int  i0)
    {


        if (this$0.mView != null)
        {
            return this$0.mView.findViewById(i0);
        }
        else
        {
            throw new IllegalStateException("Fragment does not have a view");
        }
    }
}
