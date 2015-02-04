package android.support.v4.view;

import android.view.View$OnClickListener;
import android.view.View;

class PagerTabStrip$2 implements android.view.View$OnClickListener
{
    final PagerTabStrip this$0;

    PagerTabStrip$2(PagerTabStrip  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void onClick(View  r1)
    {


        this$0.mPager.setCurrentItem(this$0.mPager.getCurrentItem() + 1);
    }
}
