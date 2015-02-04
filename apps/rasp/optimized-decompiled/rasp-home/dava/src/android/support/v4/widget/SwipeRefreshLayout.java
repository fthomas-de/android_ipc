package android.support.v4.widget;

import android.view.ViewGroup;
import android.content.Context;
import android.util.AttributeSet;
import android.content.res.TypedArray;
import android.view.ViewConfiguration;
import android.content.res.Resources;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Animation;
import android.view.View;
import android.widget.AbsListView;
import android.os.Build$VERSION;
import android.support.v4.view.ViewCompat;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;

public class SwipeRefreshLayout extends ViewGroup
{
    private static final float ACCELERATE_INTERPOLATION_FACTOR = 1.5f;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    private static final int[] LAYOUT_ATTRS;
    private static final float MAX_SWIPE_DISTANCE_FACTOR = 0.6f;
    private static final float PROGRESS_BAR_HEIGHT = 4.0f;
    private static final int REFRESH_TRIGGER_DISTANCE = 120;
    private static final long RETURN_TO_ORIGINAL_POSITION_TIMEOUT = 300l;
    private final AccelerateInterpolator mAccelerateInterpolator;
    private final Animation mAnimateToStartPosition;
    private final Runnable mCancel;
    private float mCurrPercentage;
    private int mCurrentTargetOffsetTop;
    private final DecelerateInterpolator mDecelerateInterpolator;
    private float mDistanceToTriggerSync;
    private MotionEvent mDownEvent;
    private int mFrom;
    private float mFromPercentage;
    private SwipeRefreshLayout$OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private int mOriginalOffsetTop;
    private float mPrevY;
    private SwipeProgressBar mProgressBar;
    private int mProgressBarHeight;
    private boolean mRefreshing;
    private final Runnable mReturnToStartPosition;
    private final Animation$AnimationListener mReturnToStartPositionListener;
    private boolean mReturningToStart;
    private final Animation$AnimationListener mShrinkAnimationListener;
    private Animation mShrinkTrigger;
    private View mTarget;
    private int mTouchSlop;

    static
    {

        int[] r0;
        r0 = new int[1];
        r0[0] = 16842766;
        LAYOUT_ATTRS = r0;
    }

    public SwipeRefreshLayout(Context  r1)
    {
        this(r1, null);


        this.<init>(r1, null);
    }

    public SwipeRefreshLayout(Context  r1, AttributeSet  r2)
    {
        super(r1, r2);

        TypedArray r4;
        this.<init>(r1, r2);
        mRefreshing = false;
        mDistanceToTriggerSync = -1.0F;
        mFromPercentage = 0.0F;
        mCurrPercentage = 0.0F;
        mAnimateToStartPosition = new SwipeRefreshLayout$1(this);
        mShrinkTrigger = new SwipeRefreshLayout$2(this);
        mReturnToStartPositionListener = new SwipeRefreshLayout$3(this);
        mShrinkAnimationListener = new SwipeRefreshLayout$4(this);
        mReturnToStartPosition = new SwipeRefreshLayout$5(this);
        mCancel = new SwipeRefreshLayout$6(this);
        mTouchSlop = ViewConfiguration.get(r1).getScaledTouchSlop();
        mMediumAnimationDuration = this.getResources().getInteger(17694721);
        this.setWillNotDraw(false);
        mProgressBar = new SwipeProgressBar(this);
        mProgressBarHeight = (int) (this.getResources().getDisplayMetrics().density * 4.0F);
        mDecelerateInterpolator = new DecelerateInterpolator(2.0F);
        mAccelerateInterpolator = new AccelerateInterpolator(1.5F);
        r4 = r1.obtainStyledAttributes(r2, LAYOUT_ATTRS);
        this.setEnabled(r4.getBoolean((int) 0, true));
        r4.recycle();
    }

    static int access$000(SwipeRefreshLayout  r0)
    {


        return r0.mFrom;
    }

    static int access$100(SwipeRefreshLayout  r0)
    {


        return r0.mOriginalOffsetTop;
    }

    static Animation$AnimationListener access$1000(SwipeRefreshLayout  r0)
    {


        return r0.mReturnToStartPositionListener;
    }

    static void access$1100(SwipeRefreshLayout  r0, int  i0, Animation$AnimationListener  r1)
    {


        r0.animateOffsetToStartPosition(i0, r1);
    }

    static int access$1200(SwipeRefreshLayout  r0)
    {


        return r0.mMediumAnimationDuration;
    }

    static Animation access$1300(SwipeRefreshLayout  r0)
    {


        return r0.mShrinkTrigger;
    }

    static Animation$AnimationListener access$1400(SwipeRefreshLayout  r0)
    {


        return r0.mShrinkAnimationListener;
    }

    static DecelerateInterpolator access$1500(SwipeRefreshLayout  r0)
    {


        return r0.mDecelerateInterpolator;
    }

    static View access$200(SwipeRefreshLayout  r0)
    {


        return r0.mTarget;
    }

    static void access$300(SwipeRefreshLayout  r0, int  i0)
    {


        r0.setTargetOffsetTopAndBottom(i0);
    }

    static float access$400(SwipeRefreshLayout  r0)
    {


        return r0.mFromPercentage;
    }

    static float access$402(SwipeRefreshLayout  r0, float  f0)
    {


        r0.mFromPercentage = f0;
        return f0;
    }

    static SwipeProgressBar access$500(SwipeRefreshLayout  r0)
    {


        return r0.mProgressBar;
    }

    static int access$700(SwipeRefreshLayout  r0)
    {


        return r0.mCurrentTargetOffsetTop;
    }

    static int access$702(SwipeRefreshLayout  r0, int  i0)
    {


        r0.mCurrentTargetOffsetTop = i0;
        return i0;
    }

    static float access$800(SwipeRefreshLayout  r0)
    {


        return r0.mCurrPercentage;
    }

    static float access$802(SwipeRefreshLayout  r0, float  f0)
    {


        r0.mCurrPercentage = f0;
        return f0;
    }

    static boolean access$902(SwipeRefreshLayout  r0, boolean  z0)
    {


        r0.mReturningToStart = z0;
        return z0;
    }

    private void animateOffsetToStartPosition(int  i0, Animation$AnimationListener  r1)
    {


        mFrom = i0;
        mAnimateToStartPosition.reset();
        mAnimateToStartPosition.setDuration((long) mMediumAnimationDuration);
        mAnimateToStartPosition.setAnimationListener(r1);
        mAnimateToStartPosition.setInterpolator(mDecelerateInterpolator);
        mTarget.startAnimation(mAnimateToStartPosition);
    }

    public boolean canChildScrollUp()
    {

        boolean z0;
        AbsListView r3;
        z0 = true;

        label_0:
        if (Build$VERSION.SDK_INT >= 14)
        {
            z0 = ViewCompat.canScrollVertically(mTarget, -1);
        }
        else
        {
            if (mTarget instanceof AbsListView == false)
            {
                if (mTarget.getScrollY() <= 0)
                {
                    z0 = false;
                }
            }
            else
            {
                r3 = (AbsListView) mTarget;

                if (r3.getChildCount() > 0)
                {
                    if (r3.getFirstVisiblePosition() > 0)
                    {
                        break label_0;
                    }
                    else
                    {
                        if (r3.getChildAt((int) 0).getTop() < r3.getPaddingTop())
                        {
                            break label_0;
                        }
                    }
                }

                z0 = false;
            }
        }

        return z0;
    }

    public void draw(Canvas  r1)
    {


        this.draw(r1);
        mProgressBar.draw(r1);
    }

    private void ensureTarget()
    {

        DisplayMetrics r2;
        if (mTarget == null)
        {
            if (this.getChildCount() > 1)
            {
                if (this.isInEditMode() == false)
                {
                    throw new IllegalStateException("SwipeRefreshLayout can host only one direct child");
                }
            }

            mTarget = this.getChildAt(0);
            mOriginalOffsetTop = mTarget.getTop() + this.getPaddingTop();
        }

        if (mDistanceToTriggerSync - -1.0F == 0)
        {
            if (this.getParent() != null)
            {
                if (((View) this.getParent()).getHeight() > 0)
                {
                    r2 = this.getResources().getDisplayMetrics();
                    mDistanceToTriggerSync = (float) (int) Math.min((float) ((View) this.getParent()).getHeight() * 0.6F, 120.0F * r2.density);
                }
            }
        }
    }

    public boolean isRefreshing()
    {


        return mRefreshing;
    }

    public void onAttachedToWindow()
    {


        this.onAttachedToWindow();
        this.removeCallbacks(mCancel);
        this.removeCallbacks(mReturnToStartPosition);
    }

    public void onDetachedFromWindow()
    {


        this.onDetachedFromWindow();
        this.removeCallbacks(mReturnToStartPosition);
        this.removeCallbacks(mCancel);
    }

    public boolean onInterceptTouchEvent(MotionEvent  r1)
    {

        boolean z0;
        this.ensureTarget();
        z0 = false;

        if (mReturningToStart != false)
        {
            if (r1.getAction() == 0)
            {
                mReturningToStart = false;
            }
        }

        if (this.isEnabled() != false)
        {
            if (mReturningToStart == false)
            {
                if (this.canChildScrollUp() == false)
                {
                    z0 = this.onTouchEvent(r1);
                }
            }
        }

        if (z0 == false)
        {
            z0 = this.onInterceptTouchEvent(r1);
        }

        return z0;
    }

    protected void onLayout(boolean  z0, int  i0, int  i1, int  i2, int  i3)
    {

        int i5, i6, i8, i9;
        View r2;
        i5 = this.getMeasuredWidth();
        i6 = this.getMeasuredHeight();
        mProgressBar.setBounds(0, 0, i5, mProgressBarHeight);

        if (this.getChildCount() != 0)
        {
            r2 = this.getChildAt(0);
            i8 = this.getPaddingLeft();
            i9 = mCurrentTargetOffsetTop + this.getPaddingTop();
            r2.layout(i8, i9, i8 + (i5 - this.getPaddingLeft() - this.getPaddingRight()), i9 + (i6 - this.getPaddingTop() - this.getPaddingBottom()));
        }
    }

    public void onMeasure(int  i0, int  i1)
    {


        this.onMeasure(i0, i1);

        if (this.getChildCount() > 1)
        {
            if (this.isInEditMode() == false)
            {
                throw new IllegalStateException("SwipeRefreshLayout can host only one direct child");
            }
        }

        if (this.getChildCount() > 0)
        {
            this.getChildAt(0).measure(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), 1073741824), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), 1073741824));
        }
    }

    public boolean onTouchEvent(MotionEvent  r1)
    {

        boolean z0;
        float f1, f2, f4;
        z0 = false;

        label_2:
        switch (r1.getAction())
        {
            case 0:
                mCurrPercentage = 0.0F;
                mDownEvent = MotionEvent.obtain(r1);
                mPrevY = mDownEvent.getY();
                break label_2;

            case 2:
                if (mDownEvent == null)
                {
                    break label_2;
                }
                else
                {
                    if (mReturningToStart != false)
                    {
                        break label_2;
                    }
                    else
                    {
                        f1 = r1.getY();
                        f2 = f1 - mDownEvent.getY();

                        if (f2 - (float) mTouchSlop <= 0)
                        {
                            break label_2;
                        }
                        else
                        {
                            if (f2 - mDistanceToTriggerSync <= 0)
                            {
                                this.setTriggerPercentage(mAccelerateInterpolator.getInterpolation(f2 / mDistanceToTriggerSync));
                                f4 = f2;

                                if (mPrevY - f1 > 0)
                                {
                                    f4 = f2 - (float) mTouchSlop;
                                }

                                this.updateContentOffsetTop((int) f4);

                                label_1:
                                {
                                    if (mPrevY - f1 > 0)
                                    {
                                        if (mTarget.getTop() < mTouchSlop)
                                        {
                                            this.removeCallbacks(mCancel);
                                            break label_1;
                                        }
                                    }

                                    this.updatePositionTimeout();
                                } //end label_1:


                                mPrevY = r1.getY();
                                z0 = true;
                                break label_2;
                            }
                            else
                            {
                                this.startRefresh();
                                z0 = true;
                                break label_2;
                            }
                        }
                    }
                }

            case 1:
            case 3:
                if (mDownEvent == null)
                {
                    break label_2;
                }
                else
                {
                    mDownEvent.recycle();
                    mDownEvent = null;
                    break label_2;
                }

            default:
                break label_2;
        }

        return z0;
    }

    public void requestDisallowInterceptTouchEvent(boolean  z0)
    {

    }

    public void setColorScheme(int  i0, int  i1, int  i2, int  i3)
    {

        Resources r1;
        int i4, i5, i6, i7;
        this.ensureTarget();
        r1 = this.getResources();
        i4 = r1.getColor(i0);
        i5 = r1.getColor(i1);
        i6 = r1.getColor(i2);
        i7 = r1.getColor(i3);
        mProgressBar.setColorScheme(i4, i5, i6, i7);
    }

    public void setOnRefreshListener(SwipeRefreshLayout$OnRefreshListener  r1)
    {


        mListener = r1;
    }

    public void setRefreshing(boolean  z0)
    {


        if (mRefreshing != z0)
        {
            this.ensureTarget();
            mCurrPercentage = 0.0F;
            mRefreshing = z0;

            if (mRefreshing == false)
            {
                mProgressBar.stop();
            }
            else
            {
                mProgressBar.start();
            }
        }
    }

    private void setTargetOffsetTopAndBottom(int  i0)
    {


        mTarget.offsetTopAndBottom(i0);
        mCurrentTargetOffsetTop = mTarget.getTop();
    }

    private void setTriggerPercentage(float  f0)
    {


        if (f0 - 0.0F != 0)
        {
            mCurrPercentage = f0;
            mProgressBar.setTriggerPercentage(f0);
        }
        else
        {
            mCurrPercentage = 0.0F;
        }
    }

    private void startRefresh()
    {


        this.removeCallbacks(mCancel);
        mReturnToStartPosition.run();
        this.setRefreshing(true);
        mListener.onRefresh();
    }

    private void updateContentOffsetTop(int  i0)
    {

        int i1;
        i1 = mTarget.getTop();

        if ((float) i0 - mDistanceToTriggerSync <= 0)
        {
            if (i0 < 0)
            {
                i0 = 0;
            }
        }
        else
        {
            i0 = (int) mDistanceToTriggerSync;
        }

        this.setTargetOffsetTopAndBottom(i0 - i1);
    }

    private void updatePositionTimeout()
    {


        this.removeCallbacks(mCancel);
        this.postDelayed(mCancel, 300L);
    }
}
