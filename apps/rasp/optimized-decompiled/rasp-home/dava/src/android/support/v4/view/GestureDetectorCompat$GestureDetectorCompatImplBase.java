package android.support.v4.view;

import android.view.ViewConfiguration;
import android.content.Context;
import android.view.GestureDetector$OnGestureListener;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.GestureDetector$OnDoubleTapListener;
import android.view.VelocityTracker;

class GestureDetectorCompat$GestureDetectorCompatImplBase implements android.support.v4.view.GestureDetectorCompat$GestureDetectorCompatImpl
{
    private static final int DOUBLE_TAP_TIMEOUT = 0;
    private static final int LONGPRESS_TIMEOUT = 0;
    private static final int LONG_PRESS = 2;
    private static final int SHOW_PRESS = 1;
    private static final int TAP = 3;
    private static final int TAP_TIMEOUT = 0;
    private boolean mAlwaysInBiggerTapRegion;
    private boolean mAlwaysInTapRegion;
    private MotionEvent mCurrentDownEvent;
    private boolean mDeferConfirmSingleTap;
    private GestureDetector$OnDoubleTapListener mDoubleTapListener;
    private int mDoubleTapSlopSquare;
    private float mDownFocusX;
    private float mDownFocusY;
    private final Handler mHandler;
    private boolean mInLongPress;
    private boolean mIsDoubleTapping;
    private boolean mIsLongpressEnabled;
    private float mLastFocusX;
    private float mLastFocusY;
    private final GestureDetector$OnGestureListener mListener;
    private int mMaximumFlingVelocity;
    private int mMinimumFlingVelocity;
    private MotionEvent mPreviousUpEvent;
    private boolean mStillDown;
    private int mTouchSlopSquare;
    private VelocityTracker mVelocityTracker;

    static
    {


        LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
        TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
        DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    }

    public GestureDetectorCompat$GestureDetectorCompatImplBase(Context  r1, GestureDetector$OnGestureListener  r2, Handler  r3)
    {


        this.<init>();

        if (r3 == null)
        {
            mHandler = new GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler(this);
        }
        else
        {
            mHandler = new GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler(this, r3);
        }

        mListener = r2;

        if (r2 instanceof GestureDetector$OnDoubleTapListener != false)
        {
            this.setOnDoubleTapListener((GestureDetector$OnDoubleTapListener) r2);
        }

        this.init(r1);
    }

    static MotionEvent access$000(GestureDetectorCompat$GestureDetectorCompatImplBase  r0)
    {


        return r0.mCurrentDownEvent;
    }

    static GestureDetector$OnGestureListener access$100(GestureDetectorCompat$GestureDetectorCompatImplBase  r0)
    {


        return r0.mListener;
    }

    static void access$200(GestureDetectorCompat$GestureDetectorCompatImplBase  r0)
    {


        r0.dispatchLongPress();
    }

    static GestureDetector$OnDoubleTapListener access$300(GestureDetectorCompat$GestureDetectorCompatImplBase  r0)
    {


        return r0.mDoubleTapListener;
    }

    static boolean access$400(GestureDetectorCompat$GestureDetectorCompatImplBase  r0)
    {


        return r0.mStillDown;
    }

    static boolean access$502(GestureDetectorCompat$GestureDetectorCompatImplBase  r0, boolean  z0)
    {


        r0.mDeferConfirmSingleTap = z0;
        return z0;
    }

    private void cancel()
    {


        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        mHandler.removeMessages(3);
        mVelocityTracker.recycle();
        mVelocityTracker = null;
        mIsDoubleTapping = false;
        mStillDown = false;
        mAlwaysInTapRegion = false;
        mAlwaysInBiggerTapRegion = false;
        mDeferConfirmSingleTap = false;

        if (mInLongPress != false)
        {
            mInLongPress = false;
        }
    }

    private void cancelTaps()
    {


        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        mHandler.removeMessages(3);
        mIsDoubleTapping = false;
        mAlwaysInTapRegion = false;
        mAlwaysInBiggerTapRegion = false;
        mDeferConfirmSingleTap = false;

        if (mInLongPress != false)
        {
            mInLongPress = false;
        }
    }

    private void dispatchLongPress()
    {


        mHandler.removeMessages(3);
        mDeferConfirmSingleTap = false;
        mInLongPress = true;
        mListener.onLongPress(mCurrentDownEvent);
    }

    private void init(Context  r1)
    {

        ViewConfiguration r4;
        int i0, i1;
        if (r1 != null)
        {
            if (mListener != null)
            {
                mIsLongpressEnabled = true;
                r4 = ViewConfiguration.get(r1);
                i0 = r4.getScaledTouchSlop();
                i1 = r4.getScaledDoubleTapSlop();
                mMinimumFlingVelocity = r4.getScaledMinimumFlingVelocity();
                mMaximumFlingVelocity = r4.getScaledMaximumFlingVelocity();
                mTouchSlopSquare = i0 * i0;
                mDoubleTapSlopSquare = i1 * i1;
                return;
            }
            else
            {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }
        }
        else
        {
            throw new IllegalArgumentException("Context must not be null");
        }
    }

    private boolean isConsideredDoubleTap(MotionEvent  r1, MotionEvent  r2, MotionEvent  r3)
    {

        boolean z0;
        int i1, i2;
        z0 = false;

        if (mAlwaysInBiggerTapRegion != false)
        {
            if (r3.getEventTime() - r2.getEventTime() - (long) DOUBLE_TAP_TIMEOUT <= 0)
            {
                i1 = (int) r1.getX() - (int) r3.getX();
                i2 = (int) r1.getY() - (int) r3.getY();

                if (i1 * i1 + i2 * i2 < mDoubleTapSlopSquare)
                {
                    z0 = true;
                }
            }
        }

        return z0;
    }

    public boolean isLongpressEnabled()
    {


        return mIsLongpressEnabled;
    }

    public boolean onTouchEvent(MotionEvent  r1)
    {

        int i0, i2, i3, i4, i5, i7, i8, i9, i11, i12, i13, i14, i32;
        boolean z0, z1, z2;
        float f0, f1, f2, f3, f4, f5, f9, f10, f12, f13;
        MotionEvent r7;
        VelocityTracker r226;
        i0 = r1.getAction();

        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }

        mVelocityTracker.addMovement(r1);

        if (i0 + 255 != 6)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        if (z0 == false)
        {
            i2 = -1;
        }
        else
        {
            i2 = MotionEventCompat.getActionIndex(r1);
        }

        f0 = 0.0F;
        f1 = 0.0F;
        i3 = MotionEventCompat.getPointerCount(r1);
        i4 = 0;

        while (i4 < i3)
        {
            if (i2 != i4)
            {
                f0 = f0 + MotionEventCompat.getX(r1, i4);
                f1 = f1 + MotionEventCompat.getY(r1, i4);
            }

            i4 = i4 + 1;
        }

        if (z0 == false)
        {
            i5 = i3;
        }
        else
        {
            i5 = i3 + -1;
        }

        f2 = f0 / (float) i5;
        f3 = f1 / (float) i5;
        z1 = false;

        label_2:
        switch (i0 + 255)
        {
            case 0:
                label_0:
                if (mDoubleTapListener != null)
                {
                    z2 = mHandler.hasMessages(3);

                    if (z2 != false)
                    {
                        mHandler.removeMessages(3);
                    }

                    if (mCurrentDownEvent != null)
                    {
                        if (mPreviousUpEvent != null)
                        {
                            if (z2 != false)
                            {
                                if (this.isConsideredDoubleTap(mCurrentDownEvent, mPreviousUpEvent, r1) != false)
                                {
                                    mIsDoubleTapping = true;
                                    z1 = 0 | mDoubleTapListener.onDoubleTap(mCurrentDownEvent) | mDoubleTapListener.onDoubleTapEvent(r1);
                                    break label_0;
                                }
                            }
                        }
                    }

                    mHandler.sendEmptyMessageDelayed(3, (long) DOUBLE_TAP_TIMEOUT);
                }

                mLastFocusX = f2;
                mDownFocusX = f2;
                mLastFocusY = f3;
                mDownFocusY = f3;

                if (mCurrentDownEvent != null)
                {
                    mCurrentDownEvent.recycle();
                }

                mCurrentDownEvent = MotionEvent.obtain(r1);
                mAlwaysInTapRegion = true;
                mAlwaysInBiggerTapRegion = true;
                mStillDown = true;
                mInLongPress = false;
                mDeferConfirmSingleTap = false;

                if (mIsLongpressEnabled != false)
                {
                    mHandler.removeMessages(2);
                    mHandler.sendEmptyMessageAtTime(2, mCurrentDownEvent.getDownTime() + (long) TAP_TIMEOUT + (long) LONGPRESS_TIMEOUT);
                }

                mHandler.sendEmptyMessageAtTime(1, mCurrentDownEvent.getDownTime() + (long) TAP_TIMEOUT);
                z1 = z1 | mListener.onDown(r1);
                break label_2;

            case 1:
                mStillDown = false;
                r7 = MotionEvent.obtain(r1);

                label_1:
                if (mIsDoubleTapping == false)
                {
                    if (mInLongPress == false)
                    {
                        if (mAlwaysInTapRegion == false)
                        {
                            r226 = mVelocityTracker;
                            i14 = MotionEventCompat.getPointerId(r1, 0);
                            r226.computeCurrentVelocity(1000, (float) mMaximumFlingVelocity);
                            f12 = VelocityTrackerCompat.getYVelocity(r226, i14);
                            f13 = VelocityTrackerCompat.getXVelocity(r226, i14);

                            if (Math.abs(f12) - (float) mMinimumFlingVelocity <= 0)
                            {
                                if (Math.abs(f13) - (float) mMinimumFlingVelocity <= 0)
                                {
                                    break label_1;
                                }
                            }

                            z1 = mListener.onFling(mCurrentDownEvent, r1, f13, f12);
                        }
                        else
                        {
                            z1 = mListener.onSingleTapUp(r1);

                            if (mDeferConfirmSingleTap != false)
                            {
                                if (mDoubleTapListener != null)
                                {
                                    mDoubleTapListener.onSingleTapConfirmed(r1);
                                }
                            }
                        }
                    }
                    else
                    {
                        mHandler.removeMessages(3);
                        mInLongPress = false;
                    }
                }
                else
                {
                    z1 = 0 | mDoubleTapListener.onDoubleTapEvent(r1);
                }

                if (mPreviousUpEvent != null)
                {
                    mPreviousUpEvent.recycle();
                }

                mPreviousUpEvent = r7;

                if (mVelocityTracker != null)
                {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                mIsDoubleTapping = false;
                mDeferConfirmSingleTap = false;
                mHandler.removeMessages(1);
                mHandler.removeMessages(2);
                break label_2;

            case 2:
                if (mInLongPress != false)
                {
                    break label_2;
                }
                else
                {
                    f9 = mLastFocusX - f2;
                    f10 = mLastFocusY - f3;

                    if (mIsDoubleTapping == false)
                    {
                        if (mAlwaysInTapRegion == false)
                        {
                            if (Math.abs(f9) - 1.0F < 0)
                            {
                                if (Math.abs(f10) - 1.0F < 0)
                                {
                                    break label_2;
                                }
                            }

                            z1 = mListener.onScroll(mCurrentDownEvent, r1, f9, f10);
                            mLastFocusX = f2;
                            mLastFocusY = f3;
                            break label_2;
                        }
                        else
                        {
                            i11 = (int) (f2 - mDownFocusX);
                            i12 = (int) (f3 - mDownFocusY);
                            i13 = i11 * i11 + i12 * i12;

                            if (i13 > mTouchSlopSquare)
                            {
                                z1 = mListener.onScroll(mCurrentDownEvent, r1, f9, f10);
                                mLastFocusX = f2;
                                mLastFocusY = f3;
                                mAlwaysInTapRegion = false;
                                mHandler.removeMessages(3);
                                mHandler.removeMessages(1);
                                mHandler.removeMessages(2);
                            }

                            if (i13 <= mTouchSlopSquare)
                            {
                                break label_2;
                            }
                            else
                            {
                                mAlwaysInBiggerTapRegion = false;
                                break label_2;
                            }
                        }
                    }
                    else
                    {
                        z1 = 0 | mDoubleTapListener.onDoubleTapEvent(r1);
                        break label_2;
                    }
                }

            case 3:
                this.cancel();
                break label_2;

            case 5:
                mLastFocusX = f2;
                mDownFocusX = f2;
                mLastFocusY = f3;
                mDownFocusY = f3;
                this.cancelTaps();
                break label_2;

            case 6:
                mLastFocusX = f2;
                mDownFocusX = f2;
                mLastFocusY = f3;
                mDownFocusY = f3;
                mVelocityTracker.computeCurrentVelocity(1000, (float) mMaximumFlingVelocity);
                i7 = MotionEventCompat.getActionIndex(r1);
                i8 = MotionEventCompat.getPointerId(r1, i7);
                f4 = VelocityTrackerCompat.getXVelocity(mVelocityTracker, i8);
                f5 = VelocityTrackerCompat.getYVelocity(mVelocityTracker, i8);
                i32 = 0;

                while (i32 < i3)
                {
                    if (i32 != i7)
                    {
                        i9 = MotionEventCompat.getPointerId(r1, i32);

                        if (f4 * VelocityTrackerCompat.getXVelocity(mVelocityTracker, i9) + f5 * VelocityTrackerCompat.getYVelocity(mVelocityTracker, i9) - 0.0F < 0)
                        {
                            mVelocityTracker.clear();
                            break label_2;
                        }
                    }

                    i32 = i32 + 1;
                }

                break label_2;

            case 4:
            default:
                break label_2;
        }

        return z1;
    }

    public void setIsLongpressEnabled(boolean  z0)
    {


        mIsLongpressEnabled = z0;
    }

    public void setOnDoubleTapListener(GestureDetector$OnDoubleTapListener  r1)
    {


        mDoubleTapListener = r1;
    }
}
