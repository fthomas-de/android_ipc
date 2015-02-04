package android.support.v4.widget;

import android.view.ViewGroup;
import android.os.Build$VERSION;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Rect;
import java.util.ArrayList;
import android.content.res.Resources;
import android.view.ViewConfiguration;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;
import android.support.v4.view.MotionEventCompat;
import android.view.View$MeasureSpec;
import android.os.Parcelable;

public class SlidingPaneLayout extends ViewGroup
{
    private static final int DEFAULT_FADE_COLOR = -858993460;
    private static final int DEFAULT_OVERHANG_SIZE = 32;
    static final SlidingPaneLayout$SlidingPanelLayoutImpl IMPL;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private SlidingPaneLayout$PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    private final ArrayList mPostedRunnables;
    private boolean mPreservedOpenState;
    private Drawable mShadowDrawable;
    private float mSlideOffset;
    private int mSlideRange;
    private View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    static
    {

        int i0;
        i0 = Build$VERSION.SDK_INT;

        if (i0 < 17)
        {
            if (i0 < 16)
            {
                IMPL = new SlidingPaneLayout$SlidingPanelLayoutImplBase();
            }
            else
            {
                IMPL = new SlidingPaneLayout$SlidingPanelLayoutImplJB();
            }
        }
        else
        {
            IMPL = new SlidingPaneLayout$SlidingPanelLayoutImplJBMR1();
        }
    }

    public SlidingPaneLayout(Context  r1)
    {
        this(r1, null);


        this.<init>(r1, null);
    }

    public SlidingPaneLayout(Context  r1, AttributeSet  r2)
    {
        this(r1, r2, 0);


        this.<init>(r1, r2, 0);
    }

    public SlidingPaneLayout(Context  r1, AttributeSet  r2, int  i0)
    {
        super(r1, r2, i0);

        float f1;
        this.<init>(r1, r2, i0);
        mSliderFadeColor = -858993460;
        mFirstLayout = true;
        mTmpRect = new Rect();
        mPostedRunnables = new ArrayList();
        f1 = r1.getResources().getDisplayMetrics().density;
        mOverhangSize = (int) (32.0F * f1 + 0.5F);
        ViewConfiguration.get(r1);
        this.setWillNotDraw(false);
        ViewCompat.setAccessibilityDelegate(this, new SlidingPaneLayout$AccessibilityDelegate(this));
        ViewCompat.setImportantForAccessibility(this, (int) 1);
        mDragHelper = ViewDragHelper.create(this, 0.5F, new SlidingPaneLayout$DragHelperCallback(this, null));
        mDragHelper.setEdgeTrackingEnabled((int) 1);
        mDragHelper.setMinVelocity(400.0F * f1);
    }

    static boolean access$100(SlidingPaneLayout  r0)
    {


        return r0.mIsUnableToDrag;
    }

    static ArrayList access$1000(SlidingPaneLayout  r0)
    {


        return r0.mPostedRunnables;
    }

    static ViewDragHelper access$200(SlidingPaneLayout  r0)
    {


        return r0.mDragHelper;
    }

    static float access$300(SlidingPaneLayout  r0)
    {


        return r0.mSlideOffset;
    }

    static View access$400(SlidingPaneLayout  r0)
    {


        return r0.mSlideableView;
    }

    static boolean access$502(SlidingPaneLayout  r0, boolean  z0)
    {


        r0.mPreservedOpenState = z0;
        return z0;
    }

    static void access$600(SlidingPaneLayout  r0, int  i0)
    {


        r0.onPanelDragged(i0);
    }

    static int access$700(SlidingPaneLayout  r0)
    {


        return r0.mSlideRange;
    }

    static void access$900(SlidingPaneLayout  r0, View  r1)
    {


        r0.invalidateChildRegion(r1);
    }

    protected boolean canScroll(View  r1, boolean  z0, int  i0, int  i1, int  i2)
    {

        int i3, i4, i6;
        View r3;
        ViewGroup r4;
        boolean z4;
        label_0:
        {
            if (r1 instanceof ViewGroup != false)
            {
                r4 = (ViewGroup) r1;
                i3 = r1.getScrollX();
                i4 = r1.getScrollY();
                i6 = r4.getChildCount() + -1;

                while (i6 >= 0)
                {
                    r3 = r4.getChildAt(i6);

                    if (i1 + i3 >= r3.getLeft())
                    {
                        if (i1 + i3 < r3.getRight())
                        {
                            if (i2 + i4 >= r3.getTop())
                            {
                                if (i2 + i4 < r3.getBottom())
                                {
                                    if (this.canScroll(r3, true, i0, i1 + i3 - r3.getLeft(), i2 + i4 - r3.getTop()) != false)
                                    {
                                        z4 = true;
                                        break label_0;
                                    }
                                }
                            }
                        }
                    }

                    i6 = i6 + -1;
                }
            }

            if (z0 != false)
            {
                if (ViewCompat.canScrollHorizontally(r1, (- (i0))) != false)
                {
                    z4 = true;
                    break label_0;
                }
            }

            z4 = false;
        } //end label_0:


        return z4;
    }

    public boolean canSlide()
    {


        return mCanSlide;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams  r1)
    {

        boolean z2;
        label_1:
        {
            if (r1 instanceof SlidingPaneLayout$LayoutParams != false)
            {
                if (this.checkLayoutParams(r1) != false)
                {
                    z2 = true;
                    break label_1;
                }
            }

            z2 = false;
        } //end label_1:


        return z2;
    }

    public boolean closePane()
    {


        return this.closePane(mSlideableView, 0);
    }

    private boolean closePane(View  r1, int  i0)
    {

        boolean z0;
        z0 = false;

        label_2:
        {
            if (mFirstLayout == false)
            {
                if (this.smoothSlideTo(0.0F, i0) == false)
                {
                    break label_2;
                }
            }

            mPreservedOpenState = false;
            z0 = true;
        } //end label_2:


        return z0;
    }

    public void computeScroll()
    {


        if (mDragHelper.continueSettling(true) != false)
        {
            if (mCanSlide != false)
            {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            else
            {
                mDragHelper.abort();
            }
        }
    }

    private void dimChildView(View  r1, float  f0, int  i0)
    {

        int i5;
        SlidingPaneLayout$DisableLayerRunnable r4;
        SlidingPaneLayout$LayoutParams r5;
        r5 = (SlidingPaneLayout$LayoutParams) r1.getLayoutParams();

        label_3:
        {
            if (f0 - 0.0F > 0)
            {
                if (i0 != 0)
                {
                    i5 = (int) ((float) ((-16777216 & i0) >>> 24) * f0) << 24 | 16777215 & i0;

                    if (r5.dimPaint == null)
                    {
                        r5.dimPaint = new Paint();
                    }

                    r5.dimPaint.setColorFilter(new PorterDuffColorFilter(i5, PorterDuff$Mode.SRC_OVER));

                    if (ViewCompat.getLayerType(r1) != 2)
                    {
                        ViewCompat.setLayerType(r1, 2, r5.dimPaint);
                    }

                    this.invalidateChildRegion(r1);
                    break label_3;
                }
            }

            if (ViewCompat.getLayerType(r1) != 0)
            {
                if (r5.dimPaint != null)
                {
                    r5.dimPaint.setColorFilter(null);
                }

                r4 = new SlidingPaneLayout$DisableLayerRunnable(this, r1);
                mPostedRunnables.add(r4);
                ViewCompat.postOnAnimation(this, r4);
            }
        } //end label_3:

    }

    void dispatchOnPanelClosed(View  r1)
    {


        if (mPanelSlideListener != null)
        {
            mPanelSlideListener.onPanelClosed(r1);
        }

        this.sendAccessibilityEvent(32);
    }

    void dispatchOnPanelOpened(View  r1)
    {


        if (mPanelSlideListener != null)
        {
            mPanelSlideListener.onPanelOpened(r1);
        }

        this.sendAccessibilityEvent(32);
    }

    void dispatchOnPanelSlide(View  r1)
    {


        if (mPanelSlideListener != null)
        {
            mPanelSlideListener.onPanelSlide(r1, mSlideOffset);
        }
    }

    public void draw(Canvas  r1)
    {

        View r2;
        int i2, i3, i4, i5;
        this.draw(r1);

        if (this.getChildCount() <= 1)
        {
            r2 = null;
        }
        else
        {
            r2 = this.getChildAt(1);
        }

        if (r2 != null)
        {
            if (mShadowDrawable != null)
            {
                i2 = mShadowDrawable.getIntrinsicWidth();
                i3 = r2.getLeft();
                i4 = r2.getTop();
                i5 = r2.getBottom();
                mShadowDrawable.setBounds(i3 - i2, i4, i3, i5);
                mShadowDrawable.draw(r1);
            }
        }
    }

    protected boolean drawChild(Canvas  r1, View  r2, long  l0)
    {

        int i2;
        boolean z0;
        Bitmap r6;
        SlidingPaneLayout$LayoutParams r7;
        r7 = (SlidingPaneLayout$LayoutParams) r2.getLayoutParams();
        i2 = r1.save(2);

        if (mCanSlide != false)
        {
            if (r7.slideable == false)
            {
                if (mSlideableView != null)
                {
                    r1.getClipBounds(mTmpRect);
                    mTmpRect.right = Math.min(mTmpRect.right, mSlideableView.getLeft());
                    r1.clipRect(mTmpRect);
                }
            }
        }

        label_4:
        if (Build$VERSION.SDK_INT < 11)
        {
            if (r7.dimWhenOffset != false)
            {
                if (mSlideOffset - 0.0F > 0)
                {
                    if (r2.isDrawingCacheEnabled() == false)
                    {
                        r2.setDrawingCacheEnabled(true);
                    }

                    r6 = r2.getDrawingCache();

                    if (r6 == null)
                    {
                        Log.e("SlidingPaneLayout", (new StringBuilder()).append("drawChild: child view ").append(r2).append(" returned null drawing cache").toString());
                        z0 = this.drawChild(r1, r2, l0);
                        break label_4;
                    }
                    else
                    {
                        r1.drawBitmap(r6, (float) r2.getLeft(), (float) r2.getTop(), r7.dimPaint);
                        z0 = false;
                        break label_4;
                    }
                }
            }

            if (r2.isDrawingCacheEnabled() != false)
            {
                r2.setDrawingCacheEnabled(false);
            }

            z0 = this.drawChild(r1, r2, l0);
        }
        else
        {
            z0 = this.drawChild(r1, r2, l0);
        }

        r1.restoreToCount(i2);
        return z0;
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams()
    {


        return new SlidingPaneLayout$LayoutParams();
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet  r1)
    {


        return new SlidingPaneLayout$LayoutParams(this.getContext(), r1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams  r1)
    {

        SlidingPaneLayout$LayoutParams r2;
        if (r1 instanceof ViewGroup$MarginLayoutParams == false)
        {
            r2 = new SlidingPaneLayout$LayoutParams(r1);
        }
        else
        {
            r2 = new SlidingPaneLayout$LayoutParams((ViewGroup$MarginLayoutParams) r1);
        }

        return r2;
    }

    public int getCoveredFadeColor()
    {


        return mCoveredFadeColor;
    }

    public int getParallaxDistance()
    {


        return mParallaxBy;
    }

    public int getSliderFadeColor()
    {


        return mSliderFadeColor;
    }

    private void invalidateChildRegion(View  r1)
    {


        IMPL.invalidateChildRegion(this, r1);
    }

    boolean isDimmed(View  r1)
    {

        boolean z0;
        SlidingPaneLayout$LayoutParams r3;
        z0 = false;

        if (r1 != null)
        {
            r3 = (SlidingPaneLayout$LayoutParams) r1.getLayoutParams();

            if (mCanSlide != false)
            {
                if (r3.dimWhenOffset != false)
                {
                    if (mSlideOffset - 0.0F > 0)
                    {
                        z0 = true;
                    }
                }
            }
        }

        return z0;
    }

    public boolean isOpen()
    {

        boolean z1;
        label_5:
        {
            if (mCanSlide != false)
            {
                if (mSlideOffset - 1.0F != 0)
                {
                    z1 = false;
                    break label_5;
                }
            }

            z1 = true;
        } //end label_5:


        return z1;
    }

    public boolean isSlideable()
    {


        return mCanSlide;
    }

    protected void onAttachedToWindow()
    {


        this.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {

        int i0, i1;
        this.onDetachedFromWindow();
        mFirstLayout = true;
        i0 = 0;
        i1 = mPostedRunnables.size();

        while (i0 < i1)
        {
            ((SlidingPaneLayout$DisableLayerRunnable) mPostedRunnables.get(i0)).run();
            i0 = i0 + 1;
        }

        mPostedRunnables.clear();
    }

    public boolean onInterceptTouchEvent(MotionEvent  r1)
    {

        int i0;
        View r2;
        boolean z1, z4, z7;
        float f1, f2, f3, f4, f6, f7;
        i0 = MotionEventCompat.getActionMasked(r1);

        if (mCanSlide == false)
        {
            if (i0 == 0)
            {
                if (this.getChildCount() > 1)
                {
                    r2 = this.getChildAt(1);

                    if (r2 != null)
                    {
                        if (mDragHelper.isViewUnder(r2, (int) r1.getX(), (int) r1.getY()) != false)
                        {
                            z4 = false;
                        }
                        else
                        {
                            z4 = true;
                        }

                        mPreservedOpenState = z4;
                    }
                }
            }
        }

        label_8:
        {
            label_7:
            if (mCanSlide != false)
            {
                if (mIsUnableToDrag != false)
                {
                    if (i0 != 0)
                    {
                        break label_7;
                    }
                }

                if (i0 != 3)
                {
                    if (i0 != 1)
                    {
                        z1 = false;

                        label_6:
                        switch (i0)
                        {
                            case 0:
                                mIsUnableToDrag = false;
                                f1 = r1.getX();
                                f2 = r1.getY();
                                mInitialMotionX = f1;
                                mInitialMotionY = f2;

                                if (mDragHelper.isViewUnder(mSlideableView, (int) f1, (int) f2) == false)
                                {
                                    break label_6;
                                }
                                else
                                {
                                    if (this.isDimmed(mSlideableView) == false)
                                    {
                                        break label_6;
                                    }
                                    else
                                    {
                                        z1 = true;
                                        break label_6;
                                    }
                                }

                            case 2:
                                f6 = r1.getX();
                                f7 = r1.getY();
                                f3 = Math.abs(f6 - mInitialMotionX);
                                f4 = Math.abs(f7 - mInitialMotionY);

                                if (f3 - (float) mDragHelper.getTouchSlop() <= 0)
                                {
                                    break label_6;
                                }
                                else
                                {
                                    if (f4 - f3 <= 0)
                                    {
                                        break label_6;
                                    }
                                    else
                                    {
                                        mDragHelper.cancel();
                                        mIsUnableToDrag = true;
                                        z7 = false;
                                        break label_8;
                                    }
                                }

                            case 1:
                            default:
                                break label_6;
                        }

                        if (mDragHelper.shouldInterceptTouchEvent(r1) == false)
                        {
                            if (z1 == false)
                            {
                                z7 = false;
                                break label_8;
                            }
                        }

                        z7 = true;
                        break label_8;
                    }
                }

                mDragHelper.cancel();
                z7 = false;
                break label_8;
            }

            mDragHelper.cancel();
            z7 = this.onInterceptTouchEvent(r1);
        } //end label_8:


        return z7;
    }

    protected void onLayout(boolean  z0, int  i0, int  i1, int  i2, int  i3)
    {

        int i4, i5, i6, i7, i8, i9, i10, i11, i13, i14, i15, i16, i18, i49, i68;
        View r3;
        float f1;
        SlidingPaneLayout$LayoutParams r8;
        boolean z9;
        i4 = i2 - i0;
        i5 = this.getPaddingLeft();
        i6 = this.getPaddingRight();
        i7 = this.getPaddingTop();
        i8 = this.getChildCount();
        i9 = i5;
        i10 = i5;

        if (mFirstLayout != false)
        {
            label_9:
            {
                if (mCanSlide != false)
                {
                    if (mPreservedOpenState != false)
                    {
                        f1 = 1.0F;
                        break label_9;
                    }
                }

                f1 = 0.0F;
            } //end label_9:


            mSlideOffset = f1;
        }

        i11 = 0;

        while (i11 < i8)
        {
            r3 = this.getChildAt(i11);

            if (r3.getVisibility() != 8)
            {
                r8 = (SlidingPaneLayout$LayoutParams) r3.getLayoutParams();
                i13 = r3.getMeasuredWidth();
                i14 = 0;

                label_10:
                if (r8.slideable == false)
                {
                    if (mCanSlide != false)
                    {
                        if (mParallaxBy != 0)
                        {
                            i14 = (int) ((1.0F - mSlideOffset) * (float) mParallaxBy);
                            i9 = i10;
                            break label_10;
                        }
                    }

                    i9 = i10;
                }
                else
                {
                    i15 = r8.leftMargin + r8.rightMargin;
                    i16 = Math.min(i10, i4 - i6 - mOverhangSize) - i9 - i15;
                    mSlideRange = i16;

                    if (r8.leftMargin + i9 + i16 + i13 / 2 <= i4 - i6)
                    {
                        z9 = false;
                    }
                    else
                    {
                        z9 = true;
                    }

                    r8.dimWhenOffset = z9;
                    i49 = (int) ((float) i16 * mSlideOffset);
                    i9 = i9 + (r8.leftMargin + i49);
                    mSlideOffset = (float) i49 / (float) mSlideRange;
                }

                i18 = i9 - i14;
                r3.layout(i18, i7, i18 + i13, i7 + r3.getMeasuredHeight());
                i10 = i10 + r3.getWidth();
            }

            i11 = i11 + 1;
        }

        if (mFirstLayout != false)
        {
            if (mCanSlide == false)
            {
                i68 = 0;

                while (i68 < i8)
                {
                    this.dimChildView(this.getChildAt(i68), 0.0F, mSliderFadeColor);
                    i68 = i68 + 1;
                }
            }
            else
            {
                if (mParallaxBy != 0)
                {
                    this.parallaxOtherViews(mSlideOffset);
                }

                if (((SlidingPaneLayout$LayoutParams) mSlideableView.getLayoutParams()).dimWhenOffset != false)
                {
                    this.dimChildView(mSlideableView, mSlideOffset, mSliderFadeColor);
                }
            }

            this.updateObscuredViewsVisibility(mSlideableView);
        }

        mFirstLayout = false;
    }

    protected void onMeasure(int  i0, int  i1)
    {

        int i2, i3, i4, i5, i9, i10, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i94, i118, i146;
        float f0;
        boolean z0, z1, z6;
        View r1, r29;
        SlidingPaneLayout$LayoutParams r7, r31;
        i2 = View$MeasureSpec.getMode(i0);
        i3 = View$MeasureSpec.getSize(i0);
        i4 = View$MeasureSpec.getMode(i1);
        i5 = View$MeasureSpec.getSize(i1);

        if (i2 == 1073741824)
        {
            if (i4 == 0)
            {
                if (this.isInEditMode() == false)
                {
                    throw new IllegalStateException("Height must not be UNSPECIFIED");
                }
                else
                {
                    if (i4 == 0)
                    {
                        i4 = -2147483648;
                        i5 = 300;
                    }
                }
            }
        }
        else
        {
            if (this.isInEditMode() == false)
            {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            }
            else
            {
                if (i2 != -2147483648)
                {
                    if (i2 == 0)
                    {
                        i3 = 300;
                    }
                }
            }
        }

        i9 = 0;
        i10 = -1;

        label_14:
        switch (i4)
        {
            case 1073741824:
                i10 = i5 - this.getPaddingTop() - this.getPaddingBottom();
                i9 = i10;
                break label_14;

            case -2147483648:
                i10 = i5 - this.getPaddingTop() - this.getPaddingBottom();
                break label_14;

            default:
                break label_14;
        }

        f0 = 0.0F;
        z0 = false;
        i12 = i3 - this.getPaddingLeft() - this.getPaddingRight();
        i13 = this.getChildCount();

        if (i13 > 2)
        {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }

        mSlideableView = null;
        i14 = 0;

        while (i14 < i13)
        {
            r1 = this.getChildAt(i14);
            r7 = (SlidingPaneLayout$LayoutParams) r1.getLayoutParams();

            label_11:
            if (r1.getVisibility() != 8)
            {
                if (r7.weight - 0.0F > 0)
                {
                    f0 = f0 + r7.weight;

                    if (r7.width == 0)
                    {
                        break label_11;
                    }
                }

                i15 = r7.leftMargin + r7.rightMargin;

                if (r7.width != -2)
                {
                    if (r7.width != -1)
                    {
                        i16 = View$MeasureSpec.makeMeasureSpec(r7.width, 1073741824);
                    }
                    else
                    {
                        i16 = View$MeasureSpec.makeMeasureSpec(i3 - i15, 1073741824);
                    }
                }
                else
                {
                    i16 = View$MeasureSpec.makeMeasureSpec(i3 - i15, -2147483648);
                }

                if (r7.height != -2)
                {
                    if (r7.height != -1)
                    {
                        i17 = View$MeasureSpec.makeMeasureSpec(r7.height, 1073741824);
                    }
                    else
                    {
                        i17 = View$MeasureSpec.makeMeasureSpec(i10, 1073741824);
                    }
                }
                else
                {
                    i17 = View$MeasureSpec.makeMeasureSpec(i10, -2147483648);
                }

                r1.measure(i16, i17);
                i18 = r1.getMeasuredWidth();
                i19 = r1.getMeasuredHeight();

                if (i4 == -2147483648)
                {
                    if (i19 > i9)
                    {
                        i9 = Math.min(i19, i10);
                    }
                }

                i12 = i12 - i18;

                if (i12 >= 0)
                {
                    z6 = false;
                }
                else
                {
                    z6 = true;
                }

                r7.slideable = z6;
                z0 = z0 | z6;

                if (r7.slideable != false)
                {
                    mSlideableView = r1;
                }
            }
            else
            {
                r7.dimWhenOffset = false;
            }

            i14 = i14 + 1;
        }

        label_15:
        {
            if (z0 == false)
            {
                if (f0 - 0.0F <= 0)
                {
                    break label_15;
                }
            }

            i20 = i3 - mOverhangSize;
            i94 = 0;

            while (i94 < i13)
            {
                r29 = this.getChildAt(i94);

                label_13:
                if (r29.getVisibility() != 8)
                {
                    r31 = (SlidingPaneLayout$LayoutParams) r29.getLayoutParams();

                    if (r29.getVisibility() != 8)
                    {
                        label_12:
                        {
                            if (r31.width == 0)
                            {
                                if (r31.weight - 0.0F > 0)
                                {
                                    z1 = true;
                                    break label_12;
                                }
                            }

                            z1 = false;
                        } //end label_12:


                        if (z1 == false)
                        {
                            i21 = r29.getMeasuredWidth();
                        }
                        else
                        {
                            i21 = 0;
                        }

                        if (z0 != false)
                        {
                            if (r29 != mSlideableView)
                            {
                                if (r31.width >= 0)
                                {
                                    break label_13;
                                }
                                else
                                {
                                    if (i21 <= i20)
                                    {
                                        if (r31.weight - 0.0F <= 0)
                                        {
                                            break label_13;
                                        }
                                    }

                                    if (z1 == false)
                                    {
                                        i118 = View$MeasureSpec.makeMeasureSpec(r29.getMeasuredHeight(), 1073741824);
                                    }
                                    else
                                    {
                                        if (r31.height != -2)
                                        {
                                            if (r31.height != -1)
                                            {
                                                i118 = View$MeasureSpec.makeMeasureSpec(r31.height, 1073741824);
                                            }
                                            else
                                            {
                                                i118 = View$MeasureSpec.makeMeasureSpec(i10, 1073741824);
                                            }
                                        }
                                        else
                                        {
                                            i118 = View$MeasureSpec.makeMeasureSpec(i10, -2147483648);
                                        }
                                    }

                                    r29.measure(View$MeasureSpec.makeMeasureSpec(i20, 1073741824), i118);
                                    break label_13;
                                }
                            }
                        }

                        if (r31.weight - 0.0F > 0)
                        {
                            if (r31.width != 0)
                            {
                                i146 = View$MeasureSpec.makeMeasureSpec(r29.getMeasuredHeight(), 1073741824);
                            }
                            else
                            {
                                if (r31.height != -2)
                                {
                                    if (r31.height != -1)
                                    {
                                        i146 = View$MeasureSpec.makeMeasureSpec(r31.height, 1073741824);
                                    }
                                    else
                                    {
                                        i146 = View$MeasureSpec.makeMeasureSpec(i10, 1073741824);
                                    }
                                }
                                else
                                {
                                    i146 = View$MeasureSpec.makeMeasureSpec(i10, -2147483648);
                                }
                            }

                            if (z0 == false)
                            {
                                i23 = Math.max(0, i12);
                                r29.measure(View$MeasureSpec.makeMeasureSpec(i21 + (int) (r31.weight * (float) i23 / f0), 1073741824), i146);
                            }
                            else
                            {
                                i22 = i3 - (r31.leftMargin + r31.rightMargin);

                                if (i21 != i22)
                                {
                                    r29.measure(View$MeasureSpec.makeMeasureSpec(i22, 1073741824), i146);
                                }
                            }
                        }
                    }
                }

                i94 = i94 + 1;
            }
        } //end label_15:


        this.setMeasuredDimension(i3, i9);
        mCanSlide = z0;

        if (mDragHelper.getViewDragState() != 0)
        {
            if (z0 == false)
            {
                mDragHelper.abort();
            }
        }
    }

    private void onPanelDragged(int  i0)
    {

        SlidingPaneLayout$LayoutParams r4;
        if (mSlideableView != null)
        {
            r4 = (SlidingPaneLayout$LayoutParams) mSlideableView.getLayoutParams();
            mSlideOffset = (float) (i0 - (this.getPaddingLeft() + r4.leftMargin)) / (float) mSlideRange;

            if (mParallaxBy != 0)
            {
                this.parallaxOtherViews(mSlideOffset);
            }

            if (r4.dimWhenOffset != false)
            {
                this.dimChildView(mSlideableView, mSlideOffset, mSliderFadeColor);
            }

            this.dispatchOnPanelSlide(mSlideableView);
        }
        else
        {
            mSlideOffset = 0.0F;
        }
    }

    protected void onRestoreInstanceState(Parcelable  r1)
    {

        SlidingPaneLayout$SavedState r4;
        r4 = (SlidingPaneLayout$SavedState) r1;
        this.onRestoreInstanceState(r4.getSuperState());

        if (r4.isOpen == false)
        {
            this.closePane();
        }
        else
        {
            this.openPane();
        }

        mPreservedOpenState = r4.isOpen;
    }

    protected Parcelable onSaveInstanceState()
    {

        SlidingPaneLayout$SavedState r2;
        boolean z1;
        r2 = new SlidingPaneLayout$SavedState(this.onSaveInstanceState());

        if (this.isSlideable() == false)
        {
            z1 = mPreservedOpenState;
        }
        else
        {
            z1 = this.isOpen();
        }

        r2.isOpen = z1;
        return r2;
    }

    protected void onSizeChanged(int  i0, int  i1, int  i2, int  i3)
    {


        this.onSizeChanged(i0, i1, i2, i3);

        if (i0 != i2)
        {
            mFirstLayout = true;
        }
    }

    public boolean onTouchEvent(MotionEvent  r1)
    {

        boolean z1;
        float f0, f1, f2, f3, f5, f6;
        int i1;
        label_16:
        if (mCanSlide != false)
        {
            mDragHelper.processTouchEvent(r1);
            z1 = true;

            switch (r1.getAction() + 255)
            {
                case 0:
                    f0 = r1.getX();
                    f1 = r1.getY();
                    mInitialMotionX = f0;
                    mInitialMotionY = f1;
                    break label_16;

                case 1:
                    if (this.isDimmed(mSlideableView) == false)
                    {
                        break label_16;
                    }
                    else
                    {
                        f5 = r1.getX();
                        f6 = r1.getY();
                        f2 = f5 - mInitialMotionX;
                        f3 = f6 - mInitialMotionY;
                        i1 = mDragHelper.getTouchSlop();

                        if (f2 * f2 + f3 * f3 - (float) (i1 * i1) >= 0)
                        {
                            break label_16;
                        }
                        else
                        {
                            if (mDragHelper.isViewUnder(mSlideableView, (int) f5, (int) f6) == false)
                            {
                                break label_16;
                            }
                            else
                            {
                                this.closePane(mSlideableView, 0);
                                break label_16;
                            }
                        }
                    }

                default:
                    break label_16;
            }
        }
        else
        {
            z1 = this.onTouchEvent(r1);
        }

        return z1;
    }

    public boolean openPane()
    {


        return this.openPane(mSlideableView, 0);
    }

    private boolean openPane(View  r1, int  i0)
    {

        boolean z0;
        z0 = true;

        label_17:
        {
            if (mFirstLayout == false)
            {
                if (this.smoothSlideTo(1.0F, i0) == false)
                {
                    z0 = false;
                    break label_17;
                }
            }

            mPreservedOpenState = true;
        } //end label_17:


        return z0;
    }

    private void parallaxOtherViews(float  f0)
    {

        boolean z0;
        int i0, i1, i3;
        View r3;
        SlidingPaneLayout$LayoutParams r4;
        r4 = (SlidingPaneLayout$LayoutParams) mSlideableView.getLayoutParams();

        label_18:
        {
            if (r4.dimWhenOffset != false)
            {
                if (r4.leftMargin <= 0)
                {
                    z0 = true;
                    break label_18;
                }
            }

            z0 = false;
        } //end label_18:


        i0 = this.getChildCount();
        i1 = 0;

        while (i1 < i0)
        {
            r3 = this.getChildAt(i1);

            if (r3 != mSlideableView)
            {
                i3 = (int) ((1.0F - mParallaxOffset) * (float) mParallaxBy);
                mParallaxOffset = f0;
                r3.offsetLeftAndRight(i3 - (int) ((1.0F - f0) * (float) mParallaxBy));

                if (z0 != false)
                {
                    this.dimChildView(r3, 1.0F - mParallaxOffset, mCoveredFadeColor);
                }
            }

            i1 = i1 + 1;
        }
    }

    public void requestChildFocus(View  r1, View  r2)
    {

        boolean z2;
        this.requestChildFocus(r1, r2);

        if (this.isInTouchMode() == false)
        {
            if (mCanSlide == false)
            {
                if (r1 != mSlideableView)
                {
                    z2 = false;
                }
                else
                {
                    z2 = true;
                }

                mPreservedOpenState = z2;
            }
        }
    }

    void setAllChildrenVisible()
    {

        int i0, i1;
        View r1;
        i0 = 0;
        i1 = this.getChildCount();

        while (i0 < i1)
        {
            r1 = this.getChildAt(i0);

            if (r1.getVisibility() == 4)
            {
                r1.setVisibility(0);
            }

            i0 = i0 + 1;
        }
    }

    public void setCoveredFadeColor(int  i0)
    {


        mCoveredFadeColor = i0;
    }

    public void setPanelSlideListener(SlidingPaneLayout$PanelSlideListener  r1)
    {


        mPanelSlideListener = r1;
    }

    public void setParallaxDistance(int  i0)
    {


        mParallaxBy = i0;
        this.requestLayout();
    }

    public void setShadowDrawable(Drawable  r1)
    {


        mShadowDrawable = r1;
    }

    public void setShadowResource(int  i0)
    {


        this.setShadowDrawable(this.getResources().getDrawable(i0));
    }

    public void setSliderFadeColor(int  i0)
    {


        mSliderFadeColor = i0;
    }

    public void smoothSlideClosed()
    {


        this.closePane();
    }

    public void smoothSlideOpen()
    {


        this.openPane();
    }

    boolean smoothSlideTo(float  f0, int  i0)
    {

        boolean z0;
        int i3;
        SlidingPaneLayout$LayoutParams r4;
        z0 = false;

        if (mCanSlide != false)
        {
            r4 = (SlidingPaneLayout$LayoutParams) mSlideableView.getLayoutParams();
            i3 = (int) ((float) (this.getPaddingLeft() + r4.leftMargin) + (float) mSlideRange * f0);

            if (mDragHelper.smoothSlideViewTo(mSlideableView, i3, mSlideableView.getTop()) != false)
            {
                this.setAllChildrenVisible();
                ViewCompat.postInvalidateOnAnimation(this);
                z0 = true;
            }
        }

        return z0;
    }

    void updateObscuredViewsVisibility(View  r1)
    {

        int i0, i3, i4, i5, i6, i7, i8, i9, i10, i11;
        View r3;
        byte b16;
        i0 = this.getPaddingLeft();
        i3 = this.getWidth() - this.getPaddingRight();
        i4 = this.getPaddingTop();
        i5 = this.getHeight() - this.getPaddingBottom();

        label_20:
        {
            if (r1 != null)
            {
                if (SlidingPaneLayout.viewIsOpaque(r1) != false)
                {
                    i6 = r1.getLeft();
                    i7 = r1.getRight();
                    i8 = r1.getTop();
                    i9 = r1.getBottom();
                    break label_20;
                }
            }

            i9 = 0;
            i8 = 0;
            i7 = 0;
            i6 = 0;
        } //end label_20:


        i10 = 0;
        i11 = this.getChildCount();

        label_21:
        {
            while (i10 < i11)
            {
                r3 = this.getChildAt(i10);

                if (r3 != r1)
                {
                    label_19:
                    {
                        if (Math.max(i0, r3.getLeft()) >= i6)
                        {
                            if (Math.max(i4, r3.getTop()) >= i8)
                            {
                                if (Math.min(i3, r3.getRight()) <= i7)
                                {
                                    if (Math.min(i5, r3.getBottom()) <= i9)
                                    {
                                        b16 = (byte) (byte) 4;
                                        break label_19;
                                    }
                                }
                            }
                        }

                        b16 = (byte) (byte) 0;
                    } //end label_19:


                    r3.setVisibility(b16);
                    i10 = i10 + 1;
                }
                else
                {
                    break label_21;
                }
            }
        } //end label_21:

    }

    private static boolean viewIsOpaque(View  r0)
    {

        boolean z0;
        Drawable r1;
        z0 = true;

        if (ViewCompat.isOpaque(r0) == false)
        {
            if (Build$VERSION.SDK_INT < 18)
            {
                r1 = r0.getBackground();

                if (r1 == null)
                {
                    z0 = false;
                }
                else
                {
                    if (r1.getOpacity() != -1)
                    {
                        z0 = false;
                    }
                }
            }
            else
            {
                z0 = false;
            }
        }

        return z0;
    }
}
