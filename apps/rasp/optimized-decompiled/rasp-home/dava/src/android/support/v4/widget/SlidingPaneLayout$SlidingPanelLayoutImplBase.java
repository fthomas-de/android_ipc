package android.support.v4.widget;

import android.view.View;
import android.support.v4.view.ViewCompat;

class SlidingPaneLayout$SlidingPanelLayoutImplBase implements android.support.v4.widget.SlidingPaneLayout$SlidingPanelLayoutImpl
{

    SlidingPaneLayout$SlidingPanelLayoutImplBase()
    {


        this.<init>();
    }

    public void invalidateChildRegion(SlidingPaneLayout  r1, View  r2)
    {


        ViewCompat.postInvalidateOnAnimation(r1, r2.getLeft(), r2.getTop(), r2.getRight(), r2.getBottom());
    }
}
