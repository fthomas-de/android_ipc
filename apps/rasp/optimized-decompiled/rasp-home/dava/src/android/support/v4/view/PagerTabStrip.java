package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.graphics.Rect;
import android.content.res.Resources;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.graphics.drawable.Drawable;

public class PagerTabStrip extends PagerTitleStrip
{
    private static final int FULL_UNDERLINE_HEIGHT = 1;
    private static final int INDICATOR_HEIGHT = 3;
    private static final int MIN_PADDING_BOTTOM = 6;
    private static final int MIN_STRIP_HEIGHT = 32;
    private static final int MIN_TEXT_SPACING = 64;
    private static final int TAB_PADDING = 16;
    private static final int TAB_SPACING = 32;
    private static final String TAG = "PagerTabStrip";
    private boolean mDrawFullUnderline;
    private boolean mDrawFullUnderlineSet;
    private int mFullUnderlineHeight;
    private boolean mIgnoreTap;
    private int mIndicatorColor;
    private int mIndicatorHeight;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private int mMinPaddingBottom;
    private int mMinStripHeight;
    private int mMinTextSpacing;
    private int mTabAlpha;
    private int mTabPadding;
    private final Paint mTabPaint;
    private final Rect mTempRect;
    private int mTouchSlop;

    public PagerTabStrip(Context  r1)
    {
        this(r1, null);


        this.<init>(r1, null);
    }

    public PagerTabStrip(Context  r1, AttributeSet  r2)
    {
        super(r1, r2);

        float f1;
        this.<init>(r1, r2);
        mTabPaint = new Paint();
        mTempRect = new Rect();
        mTabAlpha = 255;
        mDrawFullUnderline = false;
        mDrawFullUnderlineSet = false;
        mIndicatorColor = mTextColor;
        mTabPaint.setColor(mIndicatorColor);
        f1 = r1.getResources().getDisplayMetrics().density;
        mIndicatorHeight = (int) (3.0F * f1 + 0.5F);
        mMinPaddingBottom = (int) (6.0F * f1 + 0.5F);
        mMinTextSpacing = (int) (64.0F * f1);
        mTabPadding = (int) (16.0F * f1 + 0.5F);
        mFullUnderlineHeight = (int) (1.0F * f1 + 0.5F);
        mMinStripHeight = (int) (32.0F * f1 + 0.5F);
        mTouchSlop = ViewConfiguration.get(r1).getScaledTouchSlop();
        this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        this.setTextSpacing(this.getTextSpacing());
        this.setWillNotDraw(false);
        mPrevText.setFocusable(true);
        mPrevText.setOnClickListener(new PagerTabStrip$1(this));
        mNextText.setFocusable(true);
        mNextText.setOnClickListener(new PagerTabStrip$2(this));

        if (this.getBackground() == null)
        {
            mDrawFullUnderline = true;
        }
    }

    public boolean getDrawFullUnderline()
    {


        return mDrawFullUnderline;
    }

    int getMinHeight()
    {


        return Math.max(this.getMinHeight(), mMinStripHeight);
    }

    public int getTabIndicatorColor()
    {


        return mIndicatorColor;
    }

    protected void onDraw(Canvas  r1)
    {

        int i1, i4, i5, i6;
        this.onDraw(r1);
        i1 = this.getHeight();
        i4 = mCurrText.getLeft() - mTabPadding;
        i5 = mCurrText.getRight() + mTabPadding;
        i6 = i1 - mIndicatorHeight;
        mTabPaint.setColor(mTabAlpha << 24 | mIndicatorColor & 16777215);
        r1.drawRect((float) i4, (float) i6, (float) i5, (float) i1, mTabPaint);

        if (mDrawFullUnderline != false)
        {
            mTabPaint.setColor(-16777216 | mIndicatorColor & 16777215);
            r1.drawRect((float) this.getPaddingLeft(), (float) (i1 - mFullUnderlineHeight), (float) (this.getWidth() - this.getPaddingRight()), (float) i1, mTabPaint);
        }
    }

    public boolean onTouchEvent(MotionEvent  r1)
    {

        boolean z1;
        int i0;
        float f0, f1;
        z1 = false;
        i0 = r1.getAction();

        label_1:
        {
            if (i0 != 0)
            {
                if (mIgnoreTap != false)
                {
                    break label_1;
                }
            }

            f0 = r1.getX();
            f1 = r1.getY();

            label_0:
            switch (i0)
            {
                case 0:
                    mInitialMotionX = f0;
                    mInitialMotionY = f1;
                    mIgnoreTap = false;
                    break label_0;

                case 1:
                    if (f0 - (float) (mCurrText.getLeft() - mTabPadding) >= 0)
                    {
                        if (f0 - (float) (mCurrText.getRight() + mTabPadding) <= 0)
                        {
                            break label_0;
                        }
                        else
                        {
                            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                            break label_0;
                        }
                    }
                    else
                    {
                        mPager.setCurrentItem(mPager.getCurrentItem() + -1);
                        break label_0;
                    }

                case 2:
                    if (Math.abs(f0 - mInitialMotionX) - (float) mTouchSlop <= 0)
                    {
                        if (Math.abs(f1 - mInitialMotionY) - (float) mTouchSlop <= 0)
                        {
                            break label_0;
                        }
                    }

                    mIgnoreTap = true;
                    break label_0;

                default:
                    break label_0;
            }

            z1 = true;
        } //end label_1:


        return z1;
    }

    public void setBackgroundColor(int  i0)
    {

        boolean z1;
        this.setBackgroundColor(i0);

        if (mDrawFullUnderlineSet == false)
        {
            if ((-16777216 & i0) != 0)
            {
                z1 = false;
            }
            else
            {
                z1 = true;
            }

            mDrawFullUnderline = z1;
        }
    }

    public void setBackgroundDrawable(Drawable  r1)
    {

        boolean z1;
        this.setBackgroundDrawable(r1);

        if (mDrawFullUnderlineSet == false)
        {
            if (r1 != null)
            {
                z1 = false;
            }
            else
            {
                z1 = true;
            }

            mDrawFullUnderline = z1;
        }
    }

    public void setBackgroundResource(int  i0)
    {

        boolean z1;
        this.setBackgroundResource(i0);

        if (mDrawFullUnderlineSet == false)
        {
            if (i0 != 0)
            {
                z1 = false;
            }
            else
            {
                z1 = true;
            }

            mDrawFullUnderline = z1;
        }
    }

    public void setDrawFullUnderline(boolean  z0)
    {


        mDrawFullUnderline = z0;
        mDrawFullUnderlineSet = true;
        this.invalidate();
    }

    public void setPadding(int  i0, int  i1, int  i2, int  i3)
    {


        if (i3 < mMinPaddingBottom)
        {
            i3 = mMinPaddingBottom;
        }

        this.setPadding(i0, i1, i2, i3);
    }

    public void setTabIndicatorColor(int  i0)
    {


        mIndicatorColor = i0;
        mTabPaint.setColor(mIndicatorColor);
        this.invalidate();
    }

    public void setTabIndicatorColorResource(int  i0)
    {


        this.setTabIndicatorColor(this.getContext().getResources().getColor(i0));
    }

    public void setTextSpacing(int  i0)
    {


        if (i0 < mMinTextSpacing)
        {
            i0 = mMinTextSpacing;
        }

        this.setTextSpacing(i0);
    }

    void updateTextPositions(int  i0, float  f0, boolean  z0)
    {

        Rect r1;
        int i1, i3, i5, i7;
        r1 = mTempRect;
        i1 = this.getHeight();
        i3 = mCurrText.getLeft() - mTabPadding;
        i7 = mCurrText.getRight();
        i5 = i1 - mIndicatorHeight;
        r1.set(i3, i5, i7 + mTabPadding, i1);
        this.updateTextPositions(i0, f0, z0);
        mTabAlpha = (int) (Math.abs(f0 - 0.5F) * 2.0F * 255.0F);
        r1.union(mCurrText.getLeft() - mTabPadding, i5, mCurrText.getRight() + mTabPadding, i1);
        this.invalidate(r1);
    }
}
