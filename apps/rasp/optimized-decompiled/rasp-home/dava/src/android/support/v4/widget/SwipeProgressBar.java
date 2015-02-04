package android.support.v4.widget;

import android.view.View;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.support.v4.view.ViewCompat;

final class SwipeProgressBar
{
    private static final int ANIMATION_DURATION_MS = 2000;
    private static final int COLOR1 = -1291845632;
    private static final int COLOR2 = -2147483648;
    private static final int COLOR3 = 1291845632;
    private static final int COLOR4 = 436207616;
    private static final int FINISH_ANIMATION_DURATION_MS = 1000;
    private static final Interpolator INTERPOLATOR;
    private Rect mBounds;
    private final RectF mClipRect;
    private int mColor1;
    private int mColor2;
    private int mColor3;
    private int mColor4;
    private long mFinishTime;
    private final Paint mPaint;
    private View mParent;
    private boolean mRunning;
    private long mStartTime;
    private float mTriggerPercentage;

    static
    {


        INTERPOLATOR = BakedBezierInterpolator.getInstance();
    }

    public SwipeProgressBar(View  r1)
    {


        this.<init>();
        mPaint = new Paint();
        mClipRect = new RectF();
        mBounds = new Rect();
        mParent = r1;
        mColor1 = -1291845632;
        mColor2 = -2147483648;
        mColor3 = 1291845632;
        mColor4 = 436207616;
    }

    void draw(Canvas  r1)
    {

        int i0, i1, i2, i3, i4;
        boolean z0;
        long l6, l8;
        float f1, f4;
        i0 = mBounds.width();
        i1 = mBounds.height();
        i2 = i0 / 2;
        i3 = i1 / 2;
        z0 = false;
        i4 = r1.save();
        r1.clipRect(mBounds);

        label_2:
        {
            label_1:
            {
                if (mRunning == false)
                {
                    if (mFinishTime - 0L <= 0)
                    {
                        if (mTriggerPercentage - 0.0F <= 0)
                        {
                            break label_1;
                        }
                        else
                        {
                            if ((double) mTriggerPercentage - 1.0 > 0)
                            {
                                break label_1;
                            }
                            else
                            {
                                this.drawTrigger(r1, i2, i3);
                                break label_1;
                            }
                        }
                    }
                }

                l6 = AnimationUtils.currentAnimationTimeMillis();
                l8 = (l6 - mStartTime) / 2000L;
                f1 = (float) ((l6 - mStartTime) % 2000L) / 20.0F;

                if (mRunning == false)
                {
                    if (l6 - mFinishTime - 1000L < 0)
                    {
                        f4 = (float) (i0 / 2) * INTERPOLATOR.getInterpolation((float) ((l6 - mFinishTime) % 1000L) / 10.0F / 100.0F);
                        mClipRect.set((float) i2 - f4, 0.0F, (float) i2 + f4, (float) i1);
                        r1.saveLayerAlpha(mClipRect, 0, 0);
                        z0 = true;
                    }
                    else
                    {
                        mFinishTime = 0L;
                        break label_2;
                    }
                }

                label_0:
                if (l8 - 0L != 0)
                {
                    if (f1 - 0.0F >= 0)
                    {
                        if (f1 - 25.0F < 0)
                        {
                            r1.drawColor(mColor4);
                            break label_0;
                        }
                    }

                    if (f1 - 25.0F >= 0)
                    {
                        if (f1 - 50.0F < 0)
                        {
                            r1.drawColor(mColor1);
                            break label_0;
                        }
                    }

                    if (f1 - 50.0F >= 0)
                    {
                        if (f1 - 75.0F < 0)
                        {
                            r1.drawColor(mColor2);
                            break label_0;
                        }
                    }

                    r1.drawColor(mColor3);
                }
                else
                {
                    r1.drawColor(mColor1);
                }

                if (f1 - 0.0F >= 0)
                {
                    if (f1 - 25.0F <= 0)
                    {
                        this.drawCircle(r1, (float) i2, (float) i3, mColor1, (25.0F + f1) * 2.0F / 100.0F);
                    }
                }

                if (f1 - 0.0F >= 0)
                {
                    if (f1 - 50.0F <= 0)
                    {
                        this.drawCircle(r1, (float) i2, (float) i3, mColor2, 2.0F * f1 / 100.0F);
                    }
                }

                if (f1 - 25.0F >= 0)
                {
                    if (f1 - 75.0F <= 0)
                    {
                        this.drawCircle(r1, (float) i2, (float) i3, mColor3, (f1 - 25.0F) * 2.0F / 100.0F);
                    }
                }

                if (f1 - 50.0F >= 0)
                {
                    if (f1 - 100.0F <= 0)
                    {
                        this.drawCircle(r1, (float) i2, (float) i3, mColor4, (f1 - 50.0F) * 2.0F / 100.0F);
                    }
                }

                if (f1 - 75.0F >= 0)
                {
                    if (f1 - 100.0F <= 0)
                    {
                        this.drawCircle(r1, (float) i2, (float) i3, mColor1, (f1 - 75.0F) * 2.0F / 100.0F);
                    }
                }

                if (mTriggerPercentage - 0.0F > 0)
                {
                    if (z0 != false)
                    {
                        r1.restoreToCount(i4);
                        i4 = r1.save();
                        r1.clipRect(mBounds);
                        this.drawTrigger(r1, i2, i3);
                    }
                }

                ViewCompat.postInvalidateOnAnimation(mParent);
            } //end label_1:


            r1.restoreToCount(i4);
        } //end label_2:

    }

    private void drawCircle(Canvas  r1, float  f0, float  f1, int  i0, float  f2)
    {

        float f4;
        mPaint.setColor(i0);
        r1.save();
        r1.translate(f0, f1);
        f4 = INTERPOLATOR.getInterpolation(f2);
        r1.scale(f4, f4);
        r1.drawCircle(0.0F, 0.0F, f0, mPaint);
        r1.restore();
    }

    private void drawTrigger(Canvas  r1, int  i0, int  i1)
    {


        mPaint.setColor(mColor1);
        r1.drawCircle((float) i0, (float) i1, (float) i0 * mTriggerPercentage, mPaint);
    }

    boolean isRunning()
    {

        boolean z1;
        label_3:
        {
            if (mRunning == false)
            {
                if (mFinishTime - 0L <= 0)
                {
                    z1 = false;
                    break label_3;
                }
            }

            z1 = true;
        } //end label_3:


        return z1;
    }

    void setBounds(int  i0, int  i1, int  i2, int  i3)
    {


        mBounds.left = i0;
        mBounds.top = i1;
        mBounds.right = i2;
        mBounds.bottom = i3;
    }

    void setColorScheme(int  i0, int  i1, int  i2, int  i3)
    {


        mColor1 = i0;
        mColor2 = i1;
        mColor3 = i2;
        mColor4 = i3;
    }

    void setTriggerPercentage(float  f0)
    {


        mTriggerPercentage = f0;
        mStartTime = 0L;
        ViewCompat.postInvalidateOnAnimation(mParent);
    }

    void start()
    {


        if (mRunning == false)
        {
            mTriggerPercentage = 0.0F;
            mStartTime = AnimationUtils.currentAnimationTimeMillis();
            mRunning = true;
            mParent.postInvalidate();
        }
    }

    void stop()
    {


        if (mRunning != false)
        {
            mTriggerPercentage = 0.0F;
            mFinishTime = AnimationUtils.currentAnimationTimeMillis();
            mRunning = false;
            mParent.postInvalidate();
        }
    }
}
