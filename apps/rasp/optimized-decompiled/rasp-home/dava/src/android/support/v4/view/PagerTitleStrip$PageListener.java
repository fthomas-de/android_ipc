package android.support.v4.view;

import android.database.DataSetObserver;

class PagerTitleStrip$PageListener extends DataSetObserver implements android.support.v4.view.ViewPager$OnPageChangeListener, android.support.v4.view.ViewPager$OnAdapterChangeListener
{
    private int mScrollState;
    final PagerTitleStrip this$0;

    private PagerTitleStrip$PageListener(PagerTitleStrip  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    PagerTitleStrip$PageListener(PagerTitleStrip  r1, PagerTitleStrip$1  r2)
    {
        this(r1);


        this.<init>(r1);
    }

    public void onAdapterChanged(PagerAdapter  r1, PagerAdapter  r2)
    {


        this$0.updateAdapter(r1, r2);
    }

    public void onChanged()
    {

        float f0;
        f0 = 0.0F;
        this$0.updateText(this$0.mPager.getCurrentItem(), this$0.mPager.getAdapter());

        if (PagerTitleStrip.access$100(this$0) - 0.0F >= 0)
        {
            f0 = PagerTitleStrip.access$100(this$0);
        }

        this$0.updateTextPositions(this$0.mPager.getCurrentItem(), f0, true);
    }

    public void onPageScrollStateChanged(int  i0)
    {


        mScrollState = i0;
    }

    public void onPageScrolled(int  i0, float  f0, int  i1)
    {


        if (f0 - 0.5F > 0)
        {
            i0 = i0 + 1;
        }

        this$0.updateTextPositions(i0, f0, false);
    }

    public void onPageSelected(int  i0)
    {

        float f0;
        f0 = 0.0F;

        if (mScrollState == 0)
        {
            this$0.updateText(this$0.mPager.getCurrentItem(), this$0.mPager.getAdapter());

            if (PagerTitleStrip.access$100(this$0) - 0.0F >= 0)
            {
                f0 = PagerTitleStrip.access$100(this$0);
            }

            this$0.updateTextPositions(this$0.mPager.getCurrentItem(), f0, true);
        }
    }
}
