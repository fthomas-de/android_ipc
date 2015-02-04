package android.support.v4.widget;

import android.widget.ProgressBar;
import android.content.Context;
import android.util.AttributeSet;

public class ContentLoadingProgressBar extends ProgressBar
{
    private static final int MIN_DELAY = 500;
    private static final int MIN_SHOW_TIME = 500;
    private final Runnable mDelayedHide;
    private final Runnable mDelayedShow;
    private boolean mDismissed;
    private boolean mPostedHide;
    private boolean mPostedShow;
    private long mStartTime;

    public ContentLoadingProgressBar(Context  r1)
    {
        this(r1, null);


        this.<init>(r1, null);
    }

    public ContentLoadingProgressBar(Context  r1, AttributeSet  r2)
    {
        super(r1, r2, (int) 0);


        this.<init>(r1, r2, (int) 0);
        mStartTime = 4294967295L;
        mPostedHide = false;
        mPostedShow = false;
        mDismissed = false;
        mDelayedHide = new ContentLoadingProgressBar$1(this);
        mDelayedShow = new ContentLoadingProgressBar$2(this);
    }

    static boolean access$002(ContentLoadingProgressBar  r0, boolean  z0)
    {


        r0.mPostedHide = z0;
        return z0;
    }

    static long access$102(ContentLoadingProgressBar  r0, long  l0)
    {


        r0.mStartTime = l0;
        return l0;
    }

    static boolean access$202(ContentLoadingProgressBar  r0, boolean  z0)
    {


        r0.mPostedShow = z0;
        return z0;
    }

    static boolean access$300(ContentLoadingProgressBar  r0)
    {


        return r0.mDismissed;
    }

    public void hide()
    {

        long l2;
        mDismissed = true;
        this.removeCallbacks(mDelayedShow);
        l2 = System.currentTimeMillis() - mStartTime;

        label_0:
        {
            if (l2 - 500L < 0)
            {
                if (mStartTime - 4294967295L != 0)
                {
                    if (mPostedHide != false)
                    {
                        break label_0;
                    }
                    else
                    {
                        this.postDelayed(mDelayedHide, 500L - l2);
                        mPostedHide = true;
                        break label_0;
                    }
                }
            }

            this.setVisibility(8);
        } //end label_0:

    }

    public void onAttachedToWindow()
    {


        this.onAttachedToWindow();
        this.removeCallbacks();
    }

    public void onDetachedFromWindow()
    {


        this.onDetachedFromWindow();
        this.removeCallbacks();
    }

    private void removeCallbacks()
    {


        this.removeCallbacks(mDelayedHide);
        this.removeCallbacks(mDelayedShow);
    }

    public void show()
    {


        mStartTime = 4294967295L;
        mDismissed = false;
        this.removeCallbacks(mDelayedHide);

        if (mPostedShow == false)
        {
            this.postDelayed(mDelayedShow, 500L);
            mPostedShow = true;
        }
    }
}
