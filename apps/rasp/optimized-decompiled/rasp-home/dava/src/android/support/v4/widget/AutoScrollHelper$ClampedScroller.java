package android.support.v4.widget;

import android.view.animation.AnimationUtils;

class AutoScrollHelper$ClampedScroller
{
    private long mDeltaTime;
    private int mDeltaX;
    private int mDeltaY;
    private int mEffectiveRampDown;
    private int mRampDownDuration;
    private int mRampUpDuration;
    private long mStartTime;
    private long mStopTime;
    private float mStopValue;
    private float mTargetVelocityX;
    private float mTargetVelocityY;

    public AutoScrollHelper$ClampedScroller()
    {


        this.<init>();
        mStartTime = -9223372036854775808L;
        mStopTime = 4294967295L;
        mDeltaTime = 0L;
        mDeltaX = 0;
        mDeltaY = 0;
    }

    public void computeScrollDelta()
    {

        long l2, l3;
        float f1;
        if (mDeltaTime - 0L != 0)
        {
            l2 = AnimationUtils.currentAnimationTimeMillis();
            f1 = this.interpolateValue(this.getValueAt(l2));
            l3 = l2 - mDeltaTime;
            mDeltaTime = l2;
            mDeltaX = (int) ((float) l3 * f1 * mTargetVelocityX);
            mDeltaY = (int) ((float) l3 * f1 * mTargetVelocityY);
            return;
        }
        else
        {
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }
    }

    public int getDeltaX()
    {


        return mDeltaX;
    }

    public int getDeltaY()
    {


        return mDeltaY;
    }

    public int getHorizontalDirection()
    {


        return (int) (mTargetVelocityX / Math.abs(mTargetVelocityX));
    }

    private float getValueAt(long  l0)
    {

        float f1, f8, f9;
        f1 = 0.0F;

        label_0:
        if (l0 - mStartTime >= 0)
        {
            if (mStopTime - 0L >= 0)
            {
                if (l0 - mStopTime >= 0)
                {
                    f8 = 1.0F - mStopValue;
                    f9 = mStopValue;
                    f1 = AutoScrollHelper.access$900((float) (l0 - mStopTime) / (float) mEffectiveRampDown, 0.0F, 1.0F) * f9 + f8;
                    break label_0;
                }
            }

            f1 = AutoScrollHelper.access$900((float) (l0 - mStartTime) / (float) mRampUpDuration, 0.0F, 1.0F) * 0.5F;
        }

        return f1;
    }

    public int getVerticalDirection()
    {


        return (int) (mTargetVelocityY / Math.abs(mTargetVelocityY));
    }

    private float interpolateValue(float  f0)
    {


        return -4.0F * f0 * f0 + 4.0F * f0;
    }

    public boolean isFinished()
    {

        boolean z0;
        label_1:
        {
            if (mStopTime - 0L > 0)
            {
                if (AnimationUtils.currentAnimationTimeMillis() - (mStopTime + (long) mEffectiveRampDown) > 0)
                {
                    z0 = true;
                    break label_1;
                }
            }

            z0 = false;
        } //end label_1:


        return z0;
    }

    public void requestStop()
    {

        long l0;
        l0 = AnimationUtils.currentAnimationTimeMillis();
        mEffectiveRampDown = AutoScrollHelper.access$800((int) (l0 - mStartTime), 0, mRampDownDuration);
        mStopValue = this.getValueAt(l0);
        mStopTime = l0;
    }

    public void setRampDownDuration(int  i0)
    {


        mRampDownDuration = i0;
    }

    public void setRampUpDuration(int  i0)
    {


        mRampUpDuration = i0;
    }

    public void setTargetVelocity(float  f0, float  f1)
    {


        mTargetVelocityX = f0;
        mTargetVelocityY = f1;
    }

    public void start()
    {


        mStartTime = AnimationUtils.currentAnimationTimeMillis();
        mStopTime = 4294967295L;
        mDeltaTime = mStartTime;
        mStopValue = 0.5F;
        mDeltaX = 0;
        mDeltaY = 0;
    }
}
