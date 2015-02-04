package android.support.v4.widget;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewConfiguration;
import android.content.res.Resources;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.view.VelocityTracker;
import java.util.Arrays;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.MotionEvent;
import android.support.v4.view.MotionEventCompat;
import android.view.animation.Interpolator;

public class ViewDragHelper
{
    private static final int BASE_SETTLE_DURATION = 256;
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    private static final int EDGE_SIZE = 20;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "ViewDragHelper";
    private static final Interpolator sInterpolator;
    private int mActivePointerId;
    private final ViewDragHelper$Callback mCallback;
    private View mCapturedView;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private ScrollerCompat mScroller;
    private final Runnable mSetIdleRunnable;
    private int mTouchSlop;
    private int mTrackingEdges;
    private VelocityTracker mVelocityTracker;

    static
    {


        sInterpolator = new ViewDragHelper$1();
    }

    private ViewDragHelper(Context  r1, ViewGroup  r2, ViewDragHelper$Callback  r3)
    {

        ViewConfiguration r5;
        this.<init>();
        mActivePointerId = -1;
        mSetIdleRunnable = new ViewDragHelper$2(this);

        if (r2 != null)
        {
            if (r3 != null)
            {
                mParentView = r2;
                mCallback = r3;
                r5 = ViewConfiguration.get(r1);
                mEdgeSize = (int) (20.0F * r1.getResources().getDisplayMetrics().density + 0.5F);
                mTouchSlop = r5.getScaledTouchSlop();
                mMaxVelocity = (float) r5.getScaledMaximumFlingVelocity();
                mMinVelocity = (float) r5.getScaledMinimumFlingVelocity();
                mScroller = ScrollerCompat.create(r1, sInterpolator);
                return;
            }
            else
            {
                throw new IllegalArgumentException("Callback may not be null");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parent view may not be null");
        }
    }

    public void abort()
    {

        int i2, i3, i4, i5;
        this.cancel();

        if (mDragState == 2)
        {
            i2 = mScroller.getCurrX();
            i3 = mScroller.getCurrY();
            mScroller.abortAnimation();
            i4 = mScroller.getCurrX();
            i5 = mScroller.getCurrY();
            mCallback.onViewPositionChanged(mCapturedView, i4, i5, i4 - i2, i5 - i3);
        }

        this.setDragState(0);
    }

    protected boolean canScroll(View  r1, boolean  z0, int  i0, int  i1, int  i2, int  i3)
    {

        int i4, i5, i7;
        View r3;
        ViewGroup r4;
        boolean z4;
        label_1:
        {
            if (r1 instanceof ViewGroup != false)
            {
                r4 = (ViewGroup) r1;
                i4 = r1.getScrollX();
                i5 = r1.getScrollY();
                i7 = r4.getChildCount() + -1;

                while (i7 >= 0)
                {
                    r3 = r4.getChildAt(i7);

                    if (i2 + i4 >= r3.getLeft())
                    {
                        if (i2 + i4 < r3.getRight())
                        {
                            if (i3 + i5 >= r3.getTop())
                            {
                                if (i3 + i5 < r3.getBottom())
                                {
                                    if (this.canScroll(r3, true, i0, i1, i2 + i4 - r3.getLeft(), i3 + i5 - r3.getTop()) != false)
                                    {
                                        z4 = true;
                                        break label_1;
                                    }
                                }
                            }
                        }
                    }

                    i7 = i7 + -1;
                }
            }

            label_0:
            if (z0 != false)
            {
                if (ViewCompat.canScrollHorizontally(r1, (- (i0))) == false)
                {
                    if (ViewCompat.canScrollVertically(r1, (- (i1))) == false)
                    {
                        break label_0;
                    }
                }

                z4 = true;
                break label_1;
            }

            z4 = false;
        } //end label_1:


        return z4;
    }

    public void cancel()
    {


        mActivePointerId = -1;
        this.clearMotionHistory();

        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public void captureChildView(View  r1, int  i0)
    {


        if (r1.getParent() == mParentView)
        {
            mCapturedView = r1;
            mActivePointerId = i0;
            mCallback.onViewCaptured(r1, i0);
            this.setDragState(1);
            return;
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("captureChildView: parameter must be a descendant of the ViewDragHelper\'s tracked parent view (").append(mParentView).append(")").toString());
        }
    }

    private boolean checkNewEdgeDrag(float  f0, float  f1, int  i0, int  i1)
    {

        boolean z0;
        float f2, f3;
        int[] r5;
        z0 = false;
        f2 = Math.abs(f0);
        f3 = Math.abs(f1);

        label_2:
        if ((mInitialEdgesTouched[i0] & i1) == i1)
        {
            if ((mTrackingEdges & i1) != 0)
            {
                if ((mEdgeDragsLocked[i0] & i1) != i1)
                {
                    if ((mEdgeDragsInProgress[i0] & i1) != i1)
                    {
                        if (f2 - (float) mTouchSlop <= 0)
                        {
                            if (f3 - (float) mTouchSlop <= 0)
                            {
                                break label_2;
                            }
                        }

                        if (f2 - 0.5F * f3 < 0)
                        {
                            if (mCallback.onEdgeLock(i1) != false)
                            {
                                r5 = mEdgeDragsLocked;
                                r5[i0] = r5[i0] | i1;
                                break label_2;
                            }
                        }

                        if ((mEdgeDragsInProgress[i0] & i1) == 0)
                        {
                            if (f2 - (float) mTouchSlop > 0)
                            {
                                z0 = true;
                            }
                        }
                    }
                }
            }
        }

        return z0;
    }

    public boolean checkTouchSlop(int  i0)
    {

        int i1, i2;
        boolean z1;
        i1 = mInitialMotionX.length;
        i2 = 0;

        label_3:
        {
            while (i2 < i1)
            {
                if (this.checkTouchSlop(i0, i2) == false)
                {
                    i2 = i2 + 1;
                }
                else
                {
                    z1 = true;
                    break label_3;
                }
            }

            z1 = false;
        } //end label_3:


        return z1;
    }

    public boolean checkTouchSlop(int  i0, int  i1)
    {

        boolean z0, z3, z4;
        float f0, f1;
        z0 = true;

        label_4:
        if (this.isPointerDown(i1) != false)
        {
            if ((i0 & 1) != (int) 1)
            {
                z3 = false;
            }
            else
            {
                z3 = true;
            }

            if ((i0 & 2) != 2)
            {
                z4 = false;
            }
            else
            {
                z4 = true;
            }

            f0 = mLastMotionX[i1] - mInitialMotionX[i1];
            f1 = mLastMotionY[i1] - mInitialMotionY[i1];

            if (z3 != false)
            {
                if (z4 != false)
                {
                    if (f0 * f0 + f1 * f1 - (float) (mTouchSlop * mTouchSlop) > 0)
                    {
                        break label_4;
                    }
                    else
                    {
                        z0 = false;
                        break label_4;
                    }
                }
            }

            if (z3 == false)
            {
                if (z4 == false)
                {
                    z0 = false;
                }
                else
                {
                    if (Math.abs(f1) - (float) mTouchSlop <= 0)
                    {
                        z0 = false;
                    }
                }
            }
            else
            {
                if (Math.abs(f0) - (float) mTouchSlop <= 0)
                {
                    z0 = false;
                }
            }
        }
        else
        {
            z0 = false;
        }

        return z0;
    }

    private boolean checkTouchSlop(View  r1, float  f0, float  f1)
    {

        boolean z0, z2, z3;
        z0 = true;

        label_5:
        if (r1 != null)
        {
            if (mCallback.getViewHorizontalDragRange(r1) <= 0)
            {
                z2 = false;
            }
            else
            {
                z2 = true;
            }

            if (mCallback.getViewVerticalDragRange(r1) <= 0)
            {
                z3 = false;
            }
            else
            {
                z3 = true;
            }

            if (z2 != false)
            {
                if (z3 != false)
                {
                    if (f0 * f0 + f1 * f1 - (float) (mTouchSlop * mTouchSlop) > 0)
                    {
                        break label_5;
                    }
                    else
                    {
                        z0 = false;
                        break label_5;
                    }
                }
            }

            if (z2 == false)
            {
                if (z3 == false)
                {
                    z0 = false;
                }
                else
                {
                    if (Math.abs(f1) - (float) mTouchSlop <= 0)
                    {
                        z0 = false;
                    }
                }
            }
            else
            {
                if (Math.abs(f0) - (float) mTouchSlop <= 0)
                {
                    z0 = false;
                }
            }
        }
        else
        {
            z0 = false;
        }

        return z0;
    }

    private float clampMag(float  f0, float  f1, float  f2)
    {

        float f4;
        f4 = Math.abs(f0);

        if (f4 - f1 >= 0)
        {
            if (f4 - f2 <= 0)
            {
                f2 = f0;
            }
            else
            {
                if (f0 - 0.0F <= 0)
                {
                    f2 = (- (f2));
                }
            }
        }
        else
        {
            f2 = 0.0F;
        }

        return f2;
    }

    private int clampMag(int  i0, int  i1, int  i2)
    {

        int i3;
        i3 = Math.abs(i0);

        if (i3 >= i1)
        {
            if (i3 <= i2)
            {
                i2 = i0;
            }
            else
            {
                if (i0 <= 0)
                {
                    i2 = (- (i2));
                }
            }
        }
        else
        {
            i2 = 0;
        }

        return i2;
    }

    private void clearMotionHistory()
    {


        if (mInitialMotionX != null)
        {
            Arrays.fill(mInitialMotionX, 0.0F);
            Arrays.fill(mInitialMotionY, 0.0F);
            Arrays.fill(mLastMotionX, 0.0F);
            Arrays.fill(mLastMotionY, 0.0F);
            Arrays.fill(mInitialEdgesTouched, 0);
            Arrays.fill(mEdgeDragsInProgress, 0);
            Arrays.fill(mEdgeDragsLocked, 0);
            mPointersDown = 0;
        }
    }

    private void clearMotionHistory(int  i0)
    {


        if (mInitialMotionX != null)
        {
            mInitialMotionX[i0] = 0.0F;
            mInitialMotionY[i0] = 0.0F;
            mLastMotionX[i0] = 0.0F;
            mLastMotionY[i0] = 0.0F;
            mInitialEdgesTouched[i0] = 0;
            mEdgeDragsInProgress[i0] = 0;
            mEdgeDragsLocked[i0] = 0;
            mPointersDown = mPointersDown & (1 << i0 ^ -1);
        }
    }

    private int computeAxisDuration(int  i0, int  i1, int  i2)
    {

        int i3, i4, i5, i6, i8;
        float f4;
        if (i0 != 0)
        {
            i4 = mParentView.getWidth();
            i5 = i4 / 2;
            f4 = (float) i5 + (float) i5 * this.distanceInfluenceForSnapDuration(Math.min(1.0F, (float) Math.abs(i0) / (float) i4));
            i8 = Math.abs(i1);

            if (i8 <= 0)
            {
                i6 = (int) (((float) Math.abs(i0) / (float) i2 + 1.0F) * 256.0F);
            }
            else
            {
                i6 = Math.round(1000.0F * Math.abs(f4 / (float) i8)) * 4;
            }

            i3 = Math.min(i6, 600);
        }
        else
        {
            i3 = 0;
        }

        return i3;
    }

    private int computeSettleDuration(View  r1, int  i0, int  i1, int  i2, int  i3)
    {

        int i5, i6, i7, i8, i9, i10, i16, i20;
        float f2, f3;
        i16 = this.clampMag(i2, (int) mMinVelocity, (int) mMaxVelocity);
        i20 = this.clampMag(i3, (int) mMinVelocity, (int) mMaxVelocity);
        i5 = Math.abs(i0);
        i6 = Math.abs(i1);
        i7 = Math.abs(i16);
        i8 = Math.abs(i20);
        i9 = i7 + i8;
        i10 = i5 + i6;

        if (i16 == 0)
        {
            f2 = (float) i5 / (float) i10;
        }
        else
        {
            f2 = (float) i7 / (float) i9;
        }

        if (i20 == 0)
        {
            f3 = (float) i6 / (float) i10;
        }
        else
        {
            f3 = (float) i8 / (float) i9;
        }

        return (int) ((float) this.computeAxisDuration(i0, i16, mCallback.getViewHorizontalDragRange(r1)) * f2 + (float) this.computeAxisDuration(i1, i20, mCallback.getViewVerticalDragRange(r1)) * f3);
    }

    public boolean continueSettling(boolean  z0)
    {

        boolean z2, z4;
        int i2, i3, i4, i5;
        if (mDragState == 2)
        {
            z2 = mScroller.computeScrollOffset();
            i2 = mScroller.getCurrX();
            i3 = mScroller.getCurrY();
            i4 = i2 - mCapturedView.getLeft();
            i5 = i3 - mCapturedView.getTop();

            if (i4 != 0)
            {
                mCapturedView.offsetLeftAndRight(i4);
            }

            if (i5 != 0)
            {
                mCapturedView.offsetTopAndBottom(i5);
            }

            label_6:
            {
                if (i4 == 0)
                {
                    if (i5 == 0)
                    {
                        break label_6;
                    }
                }

                mCallback.onViewPositionChanged(mCapturedView, i2, i3, i4, i5);
            } //end label_6:


            if (z2 != false)
            {
                if (i2 == mScroller.getFinalX())
                {
                    if (i3 == mScroller.getFinalY())
                    {
                        mScroller.abortAnimation();
                        z2 = mScroller.isFinished();
                    }
                }
            }

            if (z2 == false)
            {
                if (z0 == false)
                {
                    this.setDragState((int) 0);
                }
                else
                {
                    mParentView.post(mSetIdleRunnable);
                }
            }
        }

        if (mDragState != 2)
        {
            z4 = false;
        }
        else
        {
            z4 = true;
        }

        return z4;
    }

    public static ViewDragHelper create(ViewGroup  r0, float  f0, ViewDragHelper$Callback  r1)
    {

        ViewDragHelper r2;
        r2 = ViewDragHelper.create(r0, r1);
        r2.mTouchSlop = (int) ((float) r2.mTouchSlop * (1.0F / f0));
        return r2;
    }

    public static ViewDragHelper create(ViewGroup  r0, ViewDragHelper$Callback  r1)
    {


        return new ViewDragHelper(r0.getContext(), r0, r1);
    }

    private void dispatchViewReleased(float  f0, float  f1)
    {


        mReleaseInProgress = true;
        mCallback.onViewReleased(mCapturedView, f0, f1);
        mReleaseInProgress = false;

        if (mDragState == (int) 1)
        {
            this.setDragState((int) 0);
        }
    }

    private float distanceInfluenceForSnapDuration(float  f0)
    {


        return (float) Math.sin((double) (float) ((double) (f0 - 0.5F) * 0.47123891676382));
    }

    private void dragTo(int  i0, int  i1, int  i2, int  i3)
    {

        int i4, i5, i6, i7;
        i4 = i0;
        i5 = i1;
        i6 = mCapturedView.getLeft();
        i7 = mCapturedView.getTop();

        if (i2 != 0)
        {
            i4 = mCallback.clampViewPositionHorizontal(mCapturedView, i0, i2);
            mCapturedView.offsetLeftAndRight(i4 - i6);
        }

        if (i3 != 0)
        {
            i5 = mCallback.clampViewPositionVertical(mCapturedView, i1, i3);
            mCapturedView.offsetTopAndBottom(i5 - i7);
        }

        label_7:
        {
            if (i2 == 0)
            {
                if (i3 == 0)
                {
                    break label_7;
                }
            }

            mCallback.onViewPositionChanged(mCapturedView, i4, i5, i4 - i6, i5 - i7);
        } //end label_7:

    }

    private void ensureMotionHistorySizeForId(int  i0)
    {

        float[] r2, r3, r4, r5;
        int[] r6, r7, r8;
        label_8:
        {
            if (mInitialMotionX != null)
            {
                if (mInitialMotionX.length > i0)
                {
                    break label_8;
                }
            }

            r2 = new float[i0 + 1];
            r3 = new float[i0 + 1];
            r4 = new float[i0 + 1];
            r5 = new float[i0 + 1];
            r6 = new int[i0 + 1];
            r7 = new int[i0 + 1];
            r8 = new int[i0 + 1];

            if (mInitialMotionX != null)
            {
                System.arraycopy(mInitialMotionX, 0, r2, 0, mInitialMotionX.length);
                System.arraycopy(mInitialMotionY, 0, r3, 0, mInitialMotionY.length);
                System.arraycopy(mLastMotionX, 0, r4, 0, mLastMotionX.length);
                System.arraycopy(mLastMotionY, 0, r5, 0, mLastMotionY.length);
                System.arraycopy(mInitialEdgesTouched, 0, r6, 0, mInitialEdgesTouched.length);
                System.arraycopy(mEdgeDragsInProgress, 0, r7, 0, mEdgeDragsInProgress.length);
                System.arraycopy(mEdgeDragsLocked, 0, r8, 0, mEdgeDragsLocked.length);
            }

            mInitialMotionX = r2;
            mInitialMotionY = r3;
            mLastMotionX = r4;
            mLastMotionY = r5;
            mInitialEdgesTouched = r6;
            mEdgeDragsInProgress = r7;
            mEdgeDragsLocked = r8;
        } //end label_8:

    }

    public View findTopChildUnder(int  i0, int  i1)
    {

        int i3;
        View r3;
        i3 = mParentView.getChildCount() + -1;

        label_9:
        {
            while (i3 >= 0)
            {
                r3 = mParentView.getChildAt(mCallback.getOrderedChildIndex(i3));

                if (i0 >= r3.getLeft())
                {
                    if (i0 < r3.getRight())
                    {
                        if (i1 >= r3.getTop())
                        {
                            if (i1 < r3.getBottom())
                            {
                                break label_9;
                            }
                        }
                    }
                }

                i3 = i3 + -1;
            }

            r3 = null;
        } //end label_9:


        return r3;
    }

    public void flingCapturedView(int  i0, int  i1, int  i2, int  i3)
    {


        if (mReleaseInProgress != false)
        {
            mScroller.fling(mCapturedView.getLeft(), mCapturedView.getTop(), (int) VelocityTrackerCompat.getXVelocity(mVelocityTracker, mActivePointerId), (int) VelocityTrackerCompat.getYVelocity(mVelocityTracker, mActivePointerId), i0, i2, i1, i3);
            this.setDragState(2);
            return;
        }
        else
        {
            throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
        }
    }

    private boolean forceSettleCapturedViewAt(int  i0, int  i1, int  i2, int  i3)
    {

        boolean z0;
        int i4, i5, i6, i7, i10;
        z0 = false;
        i4 = mCapturedView.getLeft();
        i5 = mCapturedView.getTop();
        i6 = i0 - i4;
        i7 = i1 - i5;

        label_10:
        {
            if (i6 == 0)
            {
                if (i7 == 0)
                {
                    mScroller.abortAnimation();
                    this.setDragState((int) 0);
                    break label_10;
                }
            }

            i10 = this.computeSettleDuration(mCapturedView, i6, i7, i2, i3);
            mScroller.startScroll(i4, i5, i6, i7, i10);
            this.setDragState(2);
            z0 = true;
        } //end label_10:


        return z0;
    }

    public int getActivePointerId()
    {


        return mActivePointerId;
    }

    public View getCapturedView()
    {


        return mCapturedView;
    }

    public int getEdgeSize()
    {


        return mEdgeSize;
    }

    private int getEdgesTouched(int  i0, int  i1)
    {

        byte b2;
        b2 = (byte) (byte) 0;

        if (i0 < mParentView.getLeft() + mEdgeSize)
        {
            b2 = (byte) (byte) 1;
        }

        if (i1 < mParentView.getTop() + mEdgeSize)
        {
            b2 = (byte) (b2 | 4);
        }

        if (i0 > mParentView.getRight() - mEdgeSize)
        {
            b2 = (byte) (b2 | 2);
        }

        if (i1 > mParentView.getBottom() - mEdgeSize)
        {
            b2 = (byte) (b2 | 8);
        }

        return b2;
    }

    public float getMinVelocity()
    {


        return mMinVelocity;
    }

    public int getTouchSlop()
    {


        return mTouchSlop;
    }

    public int getViewDragState()
    {


        return mDragState;
    }

    public boolean isCapturedViewUnder(int  i0, int  i1)
    {


        return this.isViewUnder(mCapturedView, i0, i1);
    }

    public boolean isEdgeTouched(int  i0)
    {

        int i1, i2;
        boolean z1;
        i1 = mInitialEdgesTouched.length;
        i2 = 0;

        label_11:
        {
            while (i2 < i1)
            {
                if (this.isEdgeTouched(i0, i2) == false)
                {
                    i2 = i2 + 1;
                }
                else
                {
                    z1 = true;
                    break label_11;
                }
            }

            z1 = false;
        } //end label_11:


        return z1;
    }

    public boolean isEdgeTouched(int  i0, int  i1)
    {

        boolean z1;
        label_12:
        {
            if (this.isPointerDown(i1) != false)
            {
                if ((mInitialEdgesTouched[i1] & i0) != 0)
                {
                    z1 = true;
                    break label_12;
                }
            }

            z1 = false;
        } //end label_12:


        return z1;
    }

    public boolean isPointerDown(int  i0)
    {

        boolean z0;
        z0 = true;

        if ((mPointersDown & (int) 1 << i0) == 0)
        {
            z0 = false;
        }

        return z0;
    }

    public boolean isViewUnder(View  r1, int  i0, int  i1)
    {

        boolean z0;
        z0 = false;

        if (r1 != null)
        {
            if (i0 >= r1.getLeft())
            {
                if (i0 < r1.getRight())
                {
                    if (i1 >= r1.getTop())
                    {
                        if (i1 < r1.getBottom())
                        {
                            z0 = true;
                        }
                    }
                }
            }
        }

        return z0;
    }

    public void processTouchEvent(MotionEvent  r1)
    {

        int i0, i1, i2, i5, i6, i7, i8, i9, i10, i11, i12, i26, i35, i67, i79, i88, i89;
        float f0, f1, f2, f3, f8, f9, f16, f17, f24, f25;
        View r5, r84;
        i0 = MotionEventCompat.getActionMasked(r1);
        i1 = MotionEventCompat.getActionIndex(r1);

        if (i0 == 0)
        {
            this.cancel();
        }

        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }

        mVelocityTracker.addMovement(r1);

        label_15:
        switch (i0)
        {
            case 0:
                f0 = r1.getX();
                f1 = r1.getY();
                i2 = MotionEventCompat.getPointerId(r1, 0);
                r5 = this.findTopChildUnder((int) f0, (int) f1);
                this.saveInitialMotion(f0, f1, i2);
                this.tryCaptureViewForDrag(r5, i2);
                i5 = mInitialEdgesTouched[i2];

                if ((mTrackingEdges & i5) == 0)
                {
                    break label_15;
                }
                else
                {
                    mCallback.onEdgeTouched(mTrackingEdges & i5, i2);
                    break label_15;
                }

            case 1:
                if (mDragState == 1)
                {
                    this.releaseViewForPointerUp();
                }

                this.cancel();
                break label_15;

            case 2:
                if (mDragState != 1)
                {
                    i9 = MotionEventCompat.getPointerCount(r1);
                    i10 = 0;

                    label_13:
                    {
                        while (i10 < i9)
                        {
                            i67 = MotionEventCompat.getPointerId(r1, i10);
                            f24 = MotionEventCompat.getX(r1, i10);
                            f25 = MotionEventCompat.getY(r1, i10);
                            f2 = f24 - mInitialMotionX[i67];
                            f3 = f25 - mInitialMotionY[i67];
                            this.reportNewEdgeDrags(f2, f3, i67);

                            if (mDragState != 1)
                            {
                                r84 = this.findTopChildUnder((int) f24, (int) f25);

                                if (this.checkTouchSlop(r84, f2, f3) != false)
                                {
                                    if (this.tryCaptureViewForDrag(r84, i67) != false)
                                    {
                                        break label_13;
                                    }
                                }

                                i10 = i10 + 1;
                            }
                            else
                            {
                                break label_13;
                            }
                        }
                    } //end label_13:


                    this.saveLastMotion(r1);
                    break label_15;
                }
                else
                {
                    i6 = MotionEventCompat.findPointerIndex(r1, mActivePointerId);
                    f16 = MotionEventCompat.getX(r1, i6);
                    f17 = MotionEventCompat.getY(r1, i6);
                    i7 = (int) (f16 - mLastMotionX[mActivePointerId]);
                    i8 = (int) (f17 - mLastMotionY[mActivePointerId]);
                    this.dragTo(mCapturedView.getLeft() + i7, mCapturedView.getTop() + i8, i7, i8);
                    this.saveLastMotion(r1);
                    break label_15;
                }

            case 3:
                if (mDragState == 1)
                {
                    this.dispatchViewReleased(0.0F, 0.0F);
                }

                this.cancel();
                break label_15;

            case 5:
                i26 = MotionEventCompat.getPointerId(r1, i1);
                f8 = MotionEventCompat.getX(r1, i1);
                f9 = MotionEventCompat.getY(r1, i1);
                this.saveInitialMotion(f8, f9, i26);

                if (mDragState != 0)
                {
                    if (this.isCapturedViewUnder((int) f8, (int) f9) == false)
                    {
                        break label_15;
                    }
                    else
                    {
                        this.tryCaptureViewForDrag(mCapturedView, i26);
                        break label_15;
                    }
                }
                else
                {
                    this.tryCaptureViewForDrag(this.findTopChildUnder((int) f8, (int) f9), i26);
                    i35 = mInitialEdgesTouched[i26];

                    if ((mTrackingEdges & i35) == 0)
                    {
                        break label_15;
                    }
                    else
                    {
                        mCallback.onEdgeTouched(mTrackingEdges & i35, i26);
                        break label_15;
                    }
                }

            case 6:
                i79 = MotionEventCompat.getPointerId(r1, i1);

                if (mDragState == 1)
                {
                    if (i79 == mActivePointerId)
                    {
                        i11 = -1;
                        i88 = MotionEventCompat.getPointerCount(r1);
                        i89 = 0;

                        label_14:
                        {
                            while (i89 < i88)
                            {
                                i12 = MotionEventCompat.getPointerId(r1, i89);

                                if (i12 != mActivePointerId)
                                {
                                    if (this.findTopChildUnder((int) MotionEventCompat.getX(r1, i89), (int) MotionEventCompat.getY(r1, i89)) == mCapturedView)
                                    {
                                        if (this.tryCaptureViewForDrag(mCapturedView, i12) != false)
                                        {
                                            i11 = mActivePointerId;
                                            break label_14;
                                        }
                                    }
                                }

                                i89 = i89 + 1;
                            }
                        } //end label_14:


                        if (i11 == -1)
                        {
                            this.releaseViewForPointerUp();
                        }
                    }
                }

                this.clearMotionHistory(i79);
                break label_15;

            case 4:
            default:
                break label_15;
        }
    }

    private void releaseViewForPointerUp()
    {


        mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
        this.dispatchViewReleased(this.clampMag(VelocityTrackerCompat.getXVelocity(mVelocityTracker, mActivePointerId), mMinVelocity, mMaxVelocity), this.clampMag(VelocityTrackerCompat.getYVelocity(mVelocityTracker, mActivePointerId), mMinVelocity, mMaxVelocity));
    }

    private void reportNewEdgeDrags(float  f0, float  f1, int  i0)
    {

        byte b1;
        int[] r1;
        b1 = (byte) (byte) 0;

        if (this.checkNewEdgeDrag(f0, f1, i0, 1) != false)
        {
            b1 = (byte) (byte) 1;
        }

        if (this.checkNewEdgeDrag(f1, f0, i0, 4) != false)
        {
            b1 = (byte) (b1 | 4);
        }

        if (this.checkNewEdgeDrag(f0, f1, i0, 2) != false)
        {
            b1 = (byte) (b1 | 2);
        }

        if (this.checkNewEdgeDrag(f1, f0, i0, 8) != false)
        {
            b1 = (byte) (b1 | 8);
        }

        if (b1 != (byte) 0)
        {
            r1 = mEdgeDragsInProgress;
            r1[i0] = r1[i0] | b1;
            mCallback.onEdgeDragStarted(b1, i0);
        }
    }

    private void saveInitialMotion(float  f0, float  f1, int  i0)
    {


        this.ensureMotionHistorySizeForId(i0);
        mLastMotionX[i0] = f0;
        mInitialMotionX[i0] = f0;
        mLastMotionY[i0] = f1;
        mInitialMotionY[i0] = f1;
        mInitialEdgesTouched[i0] = this.getEdgesTouched((int) f0, (int) f1);
        mPointersDown = mPointersDown | 1 << i0;
    }

    private void saveLastMotion(MotionEvent  r1)
    {

        int i0, i1, i2;
        float f0, f1;
        i0 = MotionEventCompat.getPointerCount(r1);
        i1 = 0;

        while (i1 < i0)
        {
            i2 = MotionEventCompat.getPointerId(r1, i1);
            f0 = MotionEventCompat.getX(r1, i1);
            f1 = MotionEventCompat.getY(r1, i1);
            mLastMotionX[i2] = f0;
            mLastMotionY[i2] = f1;
            i1 = i1 + 1;
        }
    }

    void setDragState(int  i0)
    {


        if (mDragState != i0)
        {
            mDragState = i0;
            mCallback.onViewDragStateChanged(i0);

            if (i0 == 0)
            {
                mCapturedView = null;
            }
        }
    }

    public void setEdgeTrackingEnabled(int  i0)
    {


        mTrackingEdges = i0;
    }

    public void setMinVelocity(float  f0)
    {


        mMinVelocity = f0;
    }

    public boolean settleCapturedViewAt(int  i0, int  i1)
    {


        if (mReleaseInProgress != false)
        {
            return this.forceSettleCapturedViewAt(i0, i1, (int) VelocityTrackerCompat.getXVelocity(mVelocityTracker, mActivePointerId), (int) VelocityTrackerCompat.getYVelocity(mVelocityTracker, mActivePointerId));
        }
        else
        {
            throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        }
    }

    public boolean shouldInterceptTouchEvent(MotionEvent  r1)
    {

        int i0, i1, i3, i4, i5, i6, i17, i19, i28;
        float f0, f1, f2, f3, f4, f5, f6, f7;
        View r3, r11, r15;
        boolean z0;
        i0 = MotionEventCompat.getActionMasked(r1);
        i1 = MotionEventCompat.getActionIndex(r1);

        if (i0 == 0)
        {
            this.cancel();
        }

        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }

        mVelocityTracker.addMovement(r1);

        label_17:
        switch (i0)
        {
            case 0:
                f0 = r1.getX();
                f1 = r1.getY();
                i3 = MotionEventCompat.getPointerId(r1, 0);
                this.saveInitialMotion(f0, f1, i3);
                r3 = this.findTopChildUnder((int) f0, (int) f1);

                if (r3 == mCapturedView)
                {
                    if (mDragState == 2)
                    {
                        this.tryCaptureViewForDrag(r3, i3);
                    }
                }

                i4 = mInitialEdgesTouched[i3];

                if ((mTrackingEdges & i4) == 0)
                {
                    break label_17;
                }
                else
                {
                    mCallback.onEdgeTouched(mTrackingEdges & i4, i3);
                    break label_17;
                }

            case 2:
                i5 = MotionEventCompat.getPointerCount(r1);
                i6 = 0;

                label_16:
                {
                    while (i6 < i5)
                    {
                        i28 = MotionEventCompat.getPointerId(r1, i6);
                        f6 = MotionEventCompat.getX(r1, i6);
                        f7 = MotionEventCompat.getY(r1, i6);
                        f2 = f6 - mInitialMotionX[i28];
                        f3 = f7 - mInitialMotionY[i28];
                        this.reportNewEdgeDrags(f2, f3, i28);

                        if (mDragState != 1)
                        {
                            r15 = this.findTopChildUnder((int) f6, (int) f7);

                            if (r15 != null)
                            {
                                if (this.checkTouchSlop(r15, f2, f3) != false)
                                {
                                    if (this.tryCaptureViewForDrag(r15, i28) != false)
                                    {
                                        break label_16;
                                    }
                                }
                            }

                            i6 = i6 + 1;
                        }
                        else
                        {
                            break label_16;
                        }
                    }
                } //end label_16:


                this.saveLastMotion(r1);
                break label_17;

            case 1:
            case 3:
                this.cancel();
                break label_17;

            case 5:
                i17 = MotionEventCompat.getPointerId(r1, i1);
                f4 = MotionEventCompat.getX(r1, i1);
                f5 = MotionEventCompat.getY(r1, i1);
                this.saveInitialMotion(f4, f5, i17);

                if (mDragState != 0)
                {
                    if (mDragState != 2)
                    {
                        break label_17;
                    }
                    else
                    {
                        r11 = this.findTopChildUnder((int) f4, (int) f5);

                        if (r11 != mCapturedView)
                        {
                            break label_17;
                        }
                        else
                        {
                            this.tryCaptureViewForDrag(r11, i17);
                            break label_17;
                        }
                    }
                }
                else
                {
                    i19 = mInitialEdgesTouched[i17];

                    if ((mTrackingEdges & i19) == 0)
                    {
                        break label_17;
                    }
                    else
                    {
                        mCallback.onEdgeTouched(mTrackingEdges & i19, i17);
                        break label_17;
                    }
                }

            case 6:
                this.clearMotionHistory(MotionEventCompat.getPointerId(r1, i1));
                break label_17;

            case 4:
            default:
                break label_17;
        }

        if (mDragState != 1)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public boolean smoothSlideViewTo(View  r1, int  i0, int  i1)
    {


        mCapturedView = r1;
        mActivePointerId = -1;
        return this.forceSettleCapturedViewAt(i0, i1, 0, 0);
    }

    boolean tryCaptureViewForDrag(View  r1, int  i0)
    {

        boolean z0;
        z0 = true;

        label_18:
        {
            if (r1 == mCapturedView)
            {
                if (mActivePointerId == i0)
                {
                    break label_18;
                }
            }

            if (r1 != null)
            {
                if (mCallback.tryCaptureView(r1, i0) != false)
                {
                    mActivePointerId = i0;
                    this.captureChildView(r1, i0);
                    break label_18;
                }
            }

            z0 = false;
        } //end label_18:


        return z0;
    }
}
