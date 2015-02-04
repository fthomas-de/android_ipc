package android.support.v4.widget;

import android.view.View$OnTouchListener;
import android.view.ViewConfiguration;
import android.view.View;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateInterpolator;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;

public abstract class AutoScrollHelper implements android.view.View$OnTouchListener
{
    private static final int DEFAULT_ACTIVATION_DELAY = 0;
    private static final int DEFAULT_EDGE_TYPE = 1;
    private static final float DEFAULT_MAXIMUM_EDGE = 3.4028235E38f;
    private static final int DEFAULT_MAXIMUM_VELOCITY_DIPS = 1575;
    private static final int DEFAULT_MINIMUM_VELOCITY_DIPS = 315;
    private static final int DEFAULT_RAMP_DOWN_DURATION = 500;
    private static final int DEFAULT_RAMP_UP_DURATION = 500;
    private static final float DEFAULT_RELATIVE_EDGE = 0.2f;
    private static final float DEFAULT_RELATIVE_VELOCITY = 1.0f;
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    private static final int HORIZONTAL = 0;
    public static final float NO_MAX = 3.4028235E38f;
    public static final float NO_MIN = 0.0f;
    public static final float RELATIVE_UNSPECIFIED = 0.0f;
    private static final int VERTICAL = 1;
    private int mActivationDelay;
    private boolean mAlreadyDelayed;
    private boolean mAnimating;
    private final Interpolator mEdgeInterpolator;
    private int mEdgeType;
    private boolean mEnabled;
    private boolean mExclusive;
    private float[] mMaximumEdges;
    private float[] mMaximumVelocity;
    private float[] mMinimumVelocity;
    private boolean mNeedsCancel;
    private boolean mNeedsReset;
    private float[] mRelativeEdges;
    private float[] mRelativeVelocity;
    private Runnable mRunnable;
    private final AutoScrollHelper$ClampedScroller mScroller;
    private final View mTarget;

    static
    {


        DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    }

    public AutoScrollHelper(View  r1)
    {

        DisplayMetrics r3;
        int i1, i2;
        float[] r5, r6, r7, r8, r9;
        this.<init>();
        mScroller = new AutoScrollHelper$ClampedScroller();
        mEdgeInterpolator = new AccelerateInterpolator();
        r5 = new float[2];
        r5[0] = 0.0F;
        r5[1] = 0.0F;
        mRelativeEdges = r5;
        r6 = new float[2];
        r6[0] = 3.4028235E38F;
        r6[1] = 3.4028235E38F;
        mMaximumEdges = r6;
        r7 = new float[2];
        r7[0] = 0.0F;
        r7[1] = 0.0F;
        mRelativeVelocity = r7;
        r8 = new float[2];
        r8[0] = 0.0F;
        r8[1] = 0.0F;
        mMinimumVelocity = r8;
        r9 = new float[2];
        r9[0] = 3.4028235E38F;
        r9[1] = 3.4028235E38F;
        mMaximumVelocity = r9;
        mTarget = r1;
        r3 = Resources.getSystem().getDisplayMetrics();
        i1 = (int) (1575.0F * r3.density + 0.5F);
        i2 = (int) (315.0F * r3.density + 0.5F);
        this.setMaximumVelocity((float) i1, (float) i1);
        this.setMinimumVelocity((float) i2, (float) i2);
        this.setEdgeType(1);
        this.setMaximumEdges(3.4028235E38F, 3.4028235E38F);
        this.setRelativeEdges(0.2F, 0.2F);
        this.setRelativeVelocity(1.0F, 1.0F);
        this.setActivationDelay(DEFAULT_ACTIVATION_DELAY);
        this.setRampUpDuration(500);
        this.setRampDownDuration(500);
    }

    static boolean access$100(AutoScrollHelper  r0)
    {


        return r0.mAnimating;
    }

    static boolean access$102(AutoScrollHelper  r0, boolean  z0)
    {


        r0.mAnimating = z0;
        return z0;
    }

    static boolean access$200(AutoScrollHelper  r0)
    {


        return r0.mNeedsReset;
    }

    static boolean access$202(AutoScrollHelper  r0, boolean  z0)
    {


        r0.mNeedsReset = z0;
        return z0;
    }

    static AutoScrollHelper$ClampedScroller access$300(AutoScrollHelper  r0)
    {


        return r0.mScroller;
    }

    static boolean access$400(AutoScrollHelper  r0)
    {


        return r0.shouldAnimate();
    }

    static boolean access$500(AutoScrollHelper  r0)
    {


        return r0.mNeedsCancel;
    }

    static boolean access$502(AutoScrollHelper  r0, boolean  z0)
    {


        r0.mNeedsCancel = z0;
        return z0;
    }

    static void access$600(AutoScrollHelper  r0)
    {


        r0.cancelTargetTouch();
    }

    static View access$700(AutoScrollHelper  r0)
    {


        return r0.mTarget;
    }

    static int access$800(int  i0, int  i1, int  i2)
    {


        return AutoScrollHelper.constrain(i0, i1, i2);
    }

    static float access$900(float  f0, float  f1, float  f2)
    {


        return AutoScrollHelper.constrain(f0, f1, f2);
    }

    public abstract boolean canTargetScrollHorizontally(int  i0);

    public abstract boolean canTargetScrollVertically(int  i0);

    private void cancelTargetTouch()
    {

        long l0;
        MotionEvent r1;
        l0 = SystemClock.uptimeMillis();
        r1 = MotionEvent.obtain(l0, l0, 3, 0.0F, 0.0F, 0);
        mTarget.onTouchEvent(r1);
        r1.recycle();
    }

    private float computeTargetVelocity(int  i0, float  f0, float  f1, float  f2)
    {

        float f3, f6, f8, f9, f10;
        f3 = 0.0F;
        f6 = this.getEdgeValue(mRelativeEdges[i0], f1, mMaximumEdges[i0], f0);

        if (f6 - 0.0F != 0)
        {
            f8 = mMinimumVelocity[i0];
            f9 = mMaximumVelocity[i0];
            f10 = mRelativeVelocity[i0] * f2;

            if (f6 - 0.0F <= 0)
            {
                f3 = (- (AutoScrollHelper.constrain((- (f6)) * f10, f8, f9)));
            }
            else
            {
                f3 = AutoScrollHelper.constrain(f6 * f10, f8, f9);
            }
        }

        return f3;
    }

    private static float constrain(float  f0, float  f1, float  f2)
    {


        if (f0 - f2 <= 0)
        {
            if (f0 - f1 >= 0)
            {
                f2 = f0;
            }
            else
            {
                f2 = f1;
            }
        }

        return f2;
    }

    private static int constrain(int  i0, int  i1, int  i2)
    {


        if (i0 <= i2)
        {
            if (i0 >= i1)
            {
                i2 = i0;
            }
            else
            {
                i2 = i1;
            }
        }

        return i2;
    }

    private float constrainEdgeValue(float  f0, float  f1)
    {

        float f3;
        f3 = 0.0F;

        label_0:
        if (f1 - 0.0F != 0)
        {
            switch (mEdgeType)
            {
                case 0:
                case 1:
                    if (f0 - f1 >= 0)
                    {
                        break label_0;
                    }
                    else
                    {
                        if (f0 - 0.0F < 0)
                        {
                            if (mAnimating == false)
                            {
                                break label_0;
                            }
                            else
                            {
                                if (mEdgeType != 1)
                                {
                                    break label_0;
                                }
                                else
                                {
                                    f3 = 1.0F;
                                    break label_0;
                                }
                            }
                        }
                        else
                        {
                            f3 = 1.0F - f0 / f1;
                            break label_0;
                        }
                    }

                case 2:
                    if (f0 - 0.0F >= 0)
                    {
                        break label_0;
                    }
                    else
                    {
                        f3 = f0 / (- (f1));
                        break label_0;
                    }

                default:
                    break label_0;
            }
        }

        return f3;
    }

    private float getEdgeValue(float  f0, float  f1, float  f2, float  f3)
    {

        float f4, f6, f7, f9, f10;
        f4 = 0.0F;
        f6 = AutoScrollHelper.constrain(f0 * f1, 0.0F, f2);
        f7 = this.constrainEdgeValue(f3, f6);
        f9 = this.constrainEdgeValue(f1 - f3, f6) - f7;

        label_1:
        {
            if (f9 - 0.0F >= 0)
            {
                if (f9 - 0.0F <= 0)
                {
                    break label_1;
                }
                else
                {
                    f10 = mEdgeInterpolator.getInterpolation(f9);
                }
            }
            else
            {
                f10 = (- (mEdgeInterpolator.getInterpolation((- (f9)))));
            }

            f4 = AutoScrollHelper.constrain(f10, -1.0F, 1.0F);
        } //end label_1:


        return f4;
    }

    public boolean isEnabled()
    {


        return mEnabled;
    }

    public boolean isExclusive()
    {


        return mExclusive;
    }

    public boolean onTouch(View  r1, MotionEvent  r2)
    {

        boolean z0, z1;
        float f0, f1;
        z0 = true;
        z1 = false;

        if (mEnabled != false)
        {
            label_2:
            switch (MotionEventCompat.getActionMasked(r2))
            {
                case 0:
                    mNeedsCancel = true;
                    mAlreadyDelayed = false;

                case 2:
                    f0 = this.computeTargetVelocity((int) 0, r2.getX(), (float) r1.getWidth(), (float) mTarget.getWidth());
                    f1 = this.computeTargetVelocity((int) 1, r2.getY(), (float) r1.getHeight(), (float) mTarget.getHeight());
                    mScroller.setTargetVelocity(f0, f1);

                    if (mAnimating != false)
                    {
                        break label_2;
                    }
                    else
                    {
                        if (this.shouldAnimate() == false)
                        {
                            break label_2;
                        }
                        else
                        {
                            this.startAnimating();
                            break label_2;
                        }
                    }

                case 1:
                case 3:
                    this.requestStop();
                    break label_2;

                default:
                    break label_2;
            }

            label_3:
            {
                if (mExclusive != false)
                {
                    if (mAnimating != false)
                    {
                        break label_3;
                    }
                }

                z0 = false;
            } //end label_3:


            z1 = z0;
        }

        return z1;
    }

    private void requestStop()
    {


        if (mNeedsReset == false)
        {
            mScroller.requestStop();
        }
        else
        {
            mAnimating = false;
        }
    }

    public abstract void scrollTargetBy(int  i0, int  i1);

    public AutoScrollHelper setActivationDelay(int  i0)
    {


        mActivationDelay = i0;
        return this;
    }

    public AutoScrollHelper setEdgeType(int  i0)
    {


        mEdgeType = i0;
        return this;
    }

    public AutoScrollHelper setEnabled(boolean  z0)
    {


        if (mEnabled != false)
        {
            if (z0 == false)
            {
                this.requestStop();
            }
        }

        mEnabled = z0;
        return this;
    }

    public AutoScrollHelper setExclusive(boolean  z0)
    {


        mExclusive = z0;
        return this;
    }

    public AutoScrollHelper setMaximumEdges(float  f0, float  f1)
    {


        mMaximumEdges[0] = f0;
        mMaximumEdges[1] = f1;
        return this;
    }

    public AutoScrollHelper setMaximumVelocity(float  f0, float  f1)
    {


        mMaximumVelocity[0] = f0 / 1000.0F;
        mMaximumVelocity[1] = f1 / 1000.0F;
        return this;
    }

    public AutoScrollHelper setMinimumVelocity(float  f0, float  f1)
    {


        mMinimumVelocity[0] = f0 / 1000.0F;
        mMinimumVelocity[1] = f1 / 1000.0F;
        return this;
    }

    public AutoScrollHelper setRampDownDuration(int  i0)
    {


        mScroller.setRampDownDuration(i0);
        return this;
    }

    public AutoScrollHelper setRampUpDuration(int  i0)
    {


        mScroller.setRampUpDuration(i0);
        return this;
    }

    public AutoScrollHelper setRelativeEdges(float  f0, float  f1)
    {


        mRelativeEdges[0] = f0;
        mRelativeEdges[1] = f1;
        return this;
    }

    public AutoScrollHelper setRelativeVelocity(float  f0, float  f1)
    {


        mRelativeVelocity[0] = f0 / 1000.0F;
        mRelativeVelocity[1] = f1 / 1000.0F;
        return this;
    }

    private boolean shouldAnimate()
    {

        AutoScrollHelper$ClampedScroller r1;
        int i0, i1;
        boolean z2;
        r1 = mScroller;
        i0 = r1.getVerticalDirection();
        i1 = r1.getHorizontalDirection();

        label_6:
        {
            label_5:
            {
                label_4:
                {
                    if (i0 != 0)
                    {
                        if (this.canTargetScrollVertically(i0) != false)
                        {
                            break label_4;
                        }
                    }

                    if (i1 == 0)
                    {
                        break label_5;
                    }
                    else
                    {
                        if (this.canTargetScrollHorizontally(i1) == false)
                        {
                            break label_5;
                        }
                    }
                } //end label_4:


                z2 = true;
                break label_6;
            } //end label_5:


            z2 = false;
        } //end label_6:


        return z2;
    }

    private void startAnimating()
    {


        if (mRunnable == null)
        {
            mRunnable = new AutoScrollHelper$ScrollAnimationRunnable(this, null);
        }

        mAnimating = true;
        mNeedsReset = true;

        label_7:
        {
            if (mAlreadyDelayed == false)
            {
                if (mActivationDelay > 0)
                {
                    ViewCompat.postOnAnimationDelayed(mTarget, mRunnable, (long) mActivationDelay);
                    break label_7;
                }
            }

            mRunnable.run();
        } //end label_7:


        mAlreadyDelayed = true;
    }
}
