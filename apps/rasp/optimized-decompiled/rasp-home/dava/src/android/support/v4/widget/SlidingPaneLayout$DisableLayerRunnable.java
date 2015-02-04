package android.support.v4.widget;

import android.view.View;
import android.support.v4.view.ViewCompat;
import java.util.ArrayList;

class SlidingPaneLayout$DisableLayerRunnable implements java.lang.Runnable
{
    final View mChildView;
    final SlidingPaneLayout this$0;

    SlidingPaneLayout$DisableLayerRunnable(SlidingPaneLayout  r1, View  r2)
    {


        this$0 = r1;
        this.<init>();
        mChildView = r2;
    }

    public void run()
    {


        if (mChildView.getParent() == this$0)
        {
            ViewCompat.setLayerType(mChildView, 0, null);
            SlidingPaneLayout.access$900(this$0, mChildView);
        }

        SlidingPaneLayout.access$1000(this$0).remove(this);
    }
}
