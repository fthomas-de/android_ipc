package android.support.v4.view;

import android.view.ViewGroup;
import android.os.Build$VERSION;
import android.content.Context;
import android.util.AttributeSet;
import android.content.res.TypedArray;
import android.widget.TextView;
import android.content.res.ColorStateList;
import android.text.TextUtils$TruncateAt;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
import android.view.View$MeasureSpec;

public class PagerTitleStrip extends ViewGroup implements android.support.v4.view.ViewPager$Decor
{
    private static final int[] ATTRS;
    private static final PagerTitleStrip$PagerTitleStripImpl IMPL;
    private static final float SIDE_ALPHA = 0.6f;
    private static final String TAG = "PagerTitleStrip";
    private static final int[] TEXT_ATTRS;
    private static final int TEXT_SPACING = 16;
    TextView mCurrText;
    private int mGravity;
    private int mLastKnownCurrentPage;
    private float mLastKnownPositionOffset;
    TextView mNextText;
    private int mNonPrimaryAlpha;
    private final PagerTitleStrip$PageListener mPageListener;
    ViewPager mPager;
    TextView mPrevText;
    private int mScaledTextSpacing;
    int mTextColor;
    private boolean mUpdatingPositions;
    private boolean mUpdatingText;
    private WeakReference mWatchingAdapter;

    static
    {

        int[] r0, r1;
        r0 = new int[4];
        r0[0] = 16842804;
        r0[1] = 16842901;
        r0[2] = 16842904;
        r0[3] = 16842927;
        ATTRS = r0;
        r1 = new int[1];
        r1[0] = 16843660;
        TEXT_ATTRS = r1;

        if (Build$VERSION.SDK_INT < 14)
        {
            IMPL = new PagerTitleStrip$PagerTitleStripImplBase();
        }
        else
        {
            IMPL = new PagerTitleStrip$PagerTitleStripImplIcs();
        }
    }

    public PagerTitleStrip(Context  r1)
    {
        this(r1, null);


        this.<init>(r1, null);
    }

    public PagerTitleStrip(Context  r1, AttributeSet  r2)
    {
        super(r1, r2);

        TypedArray r3, r4;
        int i2, i3, i4;
        boolean z1;
        TextView r6, r7, r8;
        this.<init>(r1, r2);
        mLastKnownCurrentPage = -1;
        mLastKnownPositionOffset = -1.0F;
        mPageListener = new PagerTitleStrip$PageListener(this, null);
        r6 = new TextView(r1);
        mPrevText = r6;
        this.addView(r6);
        r7 = new TextView(r1);
        mCurrText = r7;
        this.addView(r7);
        r8 = new TextView(r1);
        mNextText = r8;
        this.addView(r8);
        r3 = r1.obtainStyledAttributes(r2, ATTRS);
        i2 = r3.getResourceId((int) 0, (int) 0);

        if (i2 != 0)
        {
            mPrevText.setTextAppearance(r1, i2);
            mCurrText.setTextAppearance(r1, i2);
            mNextText.setTextAppearance(r1, i2);
        }

        i3 = r3.getDimensionPixelSize(1, (int) 0);

        if (i3 != 0)
        {
            this.setTextSize((int) 0, (float) i3);
        }

        if (r3.hasValue(2) != false)
        {
            i4 = r3.getColor(2, (int) 0);
            mPrevText.setTextColor(i4);
            mCurrText.setTextColor(i4);
            mNextText.setTextColor(i4);
        }

        mGravity = r3.getInteger(3, 80);
        r3.recycle();
        mTextColor = mCurrText.getTextColors().getDefaultColor();
        this.setNonPrimaryAlpha(0.6F);
        mPrevText.setEllipsize(TextUtils$TruncateAt.END);
        mCurrText.setEllipsize(TextUtils$TruncateAt.END);
        mNextText.setEllipsize(TextUtils$TruncateAt.END);
        z1 = false;

        if (i2 != 0)
        {
            r4 = r1.obtainStyledAttributes(i2, TEXT_ATTRS);
            z1 = r4.getBoolean((int) 0, false);
            r4.recycle();
        }

        if (z1 == false)
        {
            mPrevText.setSingleLine();
            mCurrText.setSingleLine();
            mNextText.setSingleLine();
        }
        else
        {
            PagerTitleStrip.setSingleLineAllCaps(mPrevText);
            PagerTitleStrip.setSingleLineAllCaps(mCurrText);
            PagerTitleStrip.setSingleLineAllCaps(mNextText);
        }

        mScaledTextSpacing = (int) (16.0F * r1.getResources().getDisplayMetrics().density);
    }

    static float access$100(PagerTitleStrip  r0)
    {


        return r0.mLastKnownPositionOffset;
    }

    int getMinHeight()
    {

        int i0;
        Drawable r1;
        i0 = 0;
        r1 = this.getBackground();

        if (r1 != null)
        {
            i0 = r1.getIntrinsicHeight();
        }

        return i0;
    }

    public int getTextSpacing()
    {


        return mScaledTextSpacing;
    }

    protected void onAttachedToWindow()
    {

        ViewParent r1;
        PagerAdapter r4, r13;
        ViewPager r6;
        this.onAttachedToWindow();
        r1 = this.getParent();

        if (r1 instanceof ViewPager != false)
        {
            r6 = (ViewPager) r1;
            r4 = r6.getAdapter();
            r6.setInternalPageChangeListener(mPageListener);
            r6.setOnAdapterChangeListener(mPageListener);
            mPager = r6;

            if (mWatchingAdapter == null)
            {
                r13 = null;
            }
            else
            {
                r13 = (PagerAdapter) mWatchingAdapter.get();
            }

            this.updateAdapter(r13, r4);
            return;
        }
        else
        {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
    }

    protected void onDetachedFromWindow()
    {

        Object n0;
        n0 = null;
        this.onDetachedFromWindow();

        if (mPager != null)
        {
            this.updateAdapter(mPager.getAdapter(), n0);
            mPager.setInternalPageChangeListener(n0);
            mPager.setOnAdapterChangeListener(n0);
            mPager = n0;
        }
    }

    protected void onLayout(boolean  z0, int  i0, int  i1, int  i2, int  i3)
    {

        float f0;
        f0 = 0.0F;

        if (mPager != null)
        {
            if (mLastKnownPositionOffset - 0.0F >= 0)
            {
                f0 = mLastKnownPositionOffset;
            }

            this.updateTextPositions(mLastKnownCurrentPage, f0, true);
        }
    }

    protected void onMeasure(int  i0, int  i1)
    {

        int i2, i3, i4, i5, i8, i9, i10, i14;
        i2 = View$MeasureSpec.getMode(i0);
        i3 = View$MeasureSpec.getMode(i1);
        i4 = View$MeasureSpec.getSize(i0);
        i5 = View$MeasureSpec.getSize(i1);

        if (i2 == 1073741824)
        {
            i8 = this.getMinHeight();
            i14 = this.getPaddingTop() + this.getPaddingBottom();
            i9 = View$MeasureSpec.makeMeasureSpec((int) ((float) i4 * 0.8F), -2147483648);
            i10 = View$MeasureSpec.makeMeasureSpec(i5 - i14, -2147483648);
            mPrevText.measure(i9, i10);
            mCurrText.measure(i9, i10);
            mNextText.measure(i9, i10);

            if (i3 != 1073741824)
            {
                this.setMeasuredDimension(i4, Math.max(i8, mCurrText.getMeasuredHeight() + i14));
            }
            else
            {
                this.setMeasuredDimension(i4, i5);
            }

            return;
        }
        else
        {
            throw new IllegalStateException("Must measure with an exact width");
        }
    }

    public void requestLayout()
    {


        if (mUpdatingText == false)
        {
            this.requestLayout();
        }
    }

    public void setGravity(int  i0)
    {


        mGravity = i0;
        this.requestLayout();
    }

    public void setNonPrimaryAlpha(float  f0)
    {

        int i2;
        mNonPrimaryAlpha = (int) (255.0F * f0) + 255;
        i2 = mNonPrimaryAlpha << 24 | mTextColor & 16777215;
        mPrevText.setTextColor(i2);
        mNextText.setTextColor(i2);
    }

    private static void setSingleLineAllCaps(TextView  r0)
    {


        IMPL.setSingleLineAllCaps(r0);
    }

    public void setTextColor(int  i0)
    {

        int i3;
        mTextColor = i0;
        mCurrText.setTextColor(i0);
        i3 = mNonPrimaryAlpha << 24 | mTextColor & 16777215;
        mPrevText.setTextColor(i3);
        mNextText.setTextColor(i3);
    }

    public void setTextSize(int  i0, float  f0)
    {


        mPrevText.setTextSize(i0, f0);
        mCurrText.setTextSize(i0, f0);
        mNextText.setTextSize(i0, f0);
    }

    public void setTextSpacing(int  i0)
    {


        mScaledTextSpacing = i0;
        this.requestLayout();
    }

    void updateAdapter(PagerAdapter  r1, PagerAdapter  r2)
    {


        if (r1 != null)
        {
            r1.unregisterDataSetObserver(mPageListener);
            mWatchingAdapter = null;
        }

        if (r2 != null)
        {
            r2.registerDataSetObserver(mPageListener);
            mWatchingAdapter = new WeakReference(r2);
        }

        if (mPager != null)
        {
            mLastKnownCurrentPage = -1;
            mLastKnownPositionOffset = -1.0F;
            this.updateText(mPager.getCurrentItem(), r2);
            this.requestLayout();
        }
    }

    void updateText(int  i0, PagerAdapter  r1)
    {

        int i2, i3, i4, i5, i6;
        CharSequence r2, r5, r6;
        TextView r3;
        if (r1 == null)
        {
            i2 = (int) 0;
        }
        else
        {
            i2 = r1.getCount();
        }

        mUpdatingText = true;
        r2 = null;

        if (i0 >= (int) 1)
        {
            if (r1 != null)
            {
                r2 = r1.getPageTitle(i0 + -1);
            }
        }

        mPrevText.setText(r2);
        r3 = mCurrText;

        label_0:
        {
            if (r1 != null)
            {
                if (i0 < i2)
                {
                    r5 = r1.getPageTitle(i0);
                    break label_0;
                }
            }

            r5 = null;
        } //end label_0:


        r3.setText(r5);
        r6 = null;

        if (i0 + 1 < i2)
        {
            if (r1 != null)
            {
                r6 = r1.getPageTitle(i0 + 1);
            }
        }

        mNextText.setText(r6);
        i3 = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        i4 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
        i5 = View$MeasureSpec.makeMeasureSpec((int) ((float) i3 * 0.8F), -2147483648);
        i6 = View$MeasureSpec.makeMeasureSpec(i4, -2147483648);
        mPrevText.measure(i5, i6);
        mCurrText.measure(i5, i6);
        mNextText.measure(i5, i6);
        mLastKnownCurrentPage = i0;

        if (mUpdatingPositions == false)
        {
            this.updateTextPositions(i0, mLastKnownPositionOffset, false);
        }

        mUpdatingText = false;
    }

    void updateTextPositions(int  i0, float  f0, boolean  z0)
    {

        int i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i14, i15, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, i28, i29, i31, i32, i33, i34, i37, i39, i40;
        float f1;
        label_2:
        {
            if (i0 == mLastKnownCurrentPage)
            {
                if (z0 == false)
                {
                    if (f0 - mLastKnownPositionOffset == 0)
                    {
                        break label_2;
                    }
                }
            }
            else
            {
                this.updateText(i0, mPager.getAdapter());
            }

            mUpdatingPositions = true;
            i3 = mPrevText.getMeasuredWidth();
            i4 = mCurrText.getMeasuredWidth();
            i5 = mNextText.getMeasuredWidth();
            i6 = i4 / 2;
            i7 = this.getWidth();
            i8 = this.getHeight();
            i9 = this.getPaddingLeft();
            i10 = this.getPaddingRight();
            i11 = this.getPaddingTop();
            i12 = this.getPaddingBottom();
            i14 = i10 + i6;
            i15 = i7 - (i9 + i6) - i14;
            f1 = f0 + 0.5F;

            if (f1 - 1.0F > 0)
            {
                f1 = f1 - 1.0F;
            }

            i17 = i7 - i14 - (int) ((float) i15 * f1) - i4 / 2;
            i18 = i17 + i4;
            i19 = mPrevText.getBaseline();
            i20 = mCurrText.getBaseline();
            i21 = mNextText.getBaseline();
            i22 = Math.max(Math.max(i19, i20), i21);
            i23 = i22 - i19;
            i24 = i22 - i20;
            i25 = i22 - i21;
            i26 = i23 + mPrevText.getMeasuredHeight();
            i27 = i24 + mCurrText.getMeasuredHeight();
            i28 = i25 + mNextText.getMeasuredHeight();
            i29 = Math.max(Math.max(i26, i27), i28);

            label_1:
            switch (mGravity & 112)
            {
                case 16:
                    i39 = (i8 - i11 - i12 - i29) / 2;
                    i31 = i39 + i23;
                    i32 = i39 + i24;
                    i33 = i39 + i25;
                    break label_1;

                case 80:
                    i40 = i8 - i12 - i29;
                    i31 = i40 + i23;
                    i32 = i40 + i24;
                    i33 = i40 + i25;
                    break label_1;

                default:
                    i31 = i11 + i23;
                    i32 = i11 + i24;
                    i33 = i11 + i25;
                    break label_1;
            }

            mCurrText.layout(i17, i32, i18, mCurrText.getMeasuredHeight() + i32);
            i34 = Math.min(i9, i17 - mScaledTextSpacing - i3);
            mPrevText.layout(i34, i31, i34 + i3, mPrevText.getMeasuredHeight() + i31);
            i37 = Math.max(i7 - i10 - i5, mScaledTextSpacing + i18);
            mNextText.layout(i37, i33, i37 + i5, mNextText.getMeasuredHeight() + i33);
            mLastKnownPositionOffset = f0;
            mUpdatingPositions = false;
        } //end label_2:

    }
}
