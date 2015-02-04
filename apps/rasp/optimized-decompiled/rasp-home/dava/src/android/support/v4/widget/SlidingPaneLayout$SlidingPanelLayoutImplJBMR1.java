package android.support.v4.widget;

import android.view.View;
import android.support.v4.view.ViewCompat;

class SlidingPaneLayout$SlidingPanelLayoutImplJBMR1 extends SlidingPaneLayout$SlidingPanelLayoutImplBase
{

    SlidingPaneLayout$SlidingPanelLayoutImplJBMR1()
    {


        this.<init>();
    }

    public void invalidateChildRegion(SlidingPaneLayout  r1, View  r2)
    {


        ViewCompat.setLayerPaint(r2, ((SlidingPaneLayout$LayoutParams) r2.getLayoutParams()).dimPaint);
    }
}
