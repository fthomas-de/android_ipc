package android.support.v4.view;

import android.database.DataSetObserver;

class ViewPager$PagerObserver extends DataSetObserver
{
    final ViewPager this$0;

    private ViewPager$PagerObserver(ViewPager  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    ViewPager$PagerObserver(ViewPager  r1, ViewPager$1  r2)
    {
        this(r1);


        this.<init>(r1);
    }

    public void onChanged()
    {


        this$0.dataSetChanged();
    }

    public void onInvalidated()
    {


        this$0.dataSetChanged();
    }
}
