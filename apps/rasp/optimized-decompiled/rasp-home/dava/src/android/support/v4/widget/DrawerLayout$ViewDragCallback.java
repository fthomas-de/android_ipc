package android.support.v4.widget;

import android.view.View;

class DrawerLayout$ViewDragCallback extends ViewDragHelper$Callback
{
    private final int mAbsGravity;
    private ViewDragHelper mDragger;
    private final Runnable mPeekRunnable;
    final DrawerLayout this$0;

    public DrawerLayout$ViewDragCallback(DrawerLayout  r1, int  i0)
    {


        this$0 = r1;
        this.<init>();
        mPeekRunnable = new DrawerLayout$ViewDragCallback$1(this);
        mAbsGravity = i0;
    }

    static void access$000(DrawerLayout$ViewDragCallback  r0)
    {


        r0.peekDrawer();
    }

    public int clampViewPositionHorizontal(View  r1, int  i0, int  i1)
    {

        int i3, i8;
        if (this$0.checkDrawerViewAbsoluteGravity(r1, 3) == false)
        {
            i3 = this$0.getWidth();
            i8 = Math.max(i3 - r1.getWidth(), Math.min(i0, i3));
        }
        else
        {
            i8 = Math.max((- (r1.getWidth())), Math.min(i0, 0));
        }

        return i8;
    }

    public int clampViewPositionVertical(View  r1, int  i0, int  i1)
    {


        return r1.getTop();
    }

    private void closeOtherDrawer()
    {

        byte b0;
        View r1;
        b0 = (byte) (byte) 3;

        if (mAbsGravity == 3)
        {
            b0 = (byte) (byte) 5;
        }

        r1 = this$0.findDrawerWithGravity(b0);

        if (r1 != null)
        {
            this$0.closeDrawer(r1);
        }
    }

    public int getViewHorizontalDragRange(View  r1)
    {


        return r1.getWidth();
    }

    public void onEdgeDragStarted(int  i0, int  i1)
    {

        View r1;
        if ((i0 & 1) != 1)
        {
            r1 = this$0.findDrawerWithGravity(5);
        }
        else
        {
            r1 = this$0.findDrawerWithGravity(3);
        }

        if (r1 != null)
        {
            if (this$0.getDrawerLockMode(r1) == 0)
            {
                mDragger.captureChildView(r1, i1);
            }
        }
    }

    public boolean onEdgeLock(int  i0)
    {


        return false;
    }

    public void onEdgeTouched(int  i0, int  i1)
    {


        this$0.postDelayed(mPeekRunnable, 160L);
    }

    public void onViewCaptured(View  r1, int  i0)
    {


        ((DrawerLayout$LayoutParams) r1.getLayoutParams()).isPeeking = false;
        this.closeOtherDrawer();
    }

    public void onViewDragStateChanged(int  i0)
    {


        this$0.updateDrawerState(mAbsGravity, i0, mDragger.getCapturedView());
    }

    public void onViewPositionChanged(View  r1, int  i0, int  i1, int  i2, int  i3)
    {

        int i4;
        float f0;
        byte b9;
        i4 = r1.getWidth();

        if (this$0.checkDrawerViewAbsoluteGravity(r1, 3) == false)
        {
            f0 = (float) (this$0.getWidth() - i0) / (float) i4;
        }
        else
        {
            f0 = (float) (i4 + i0) / (float) i4;
        }

        this$0.setDrawerViewOffset(r1, f0);

        if (f0 - 0.0F != 0)
        {
            b9 = (byte) (byte) 0;
        }
        else
        {
            b9 = (byte) (byte) 4;
        }

        r1.setVisibility(b9);
        this$0.invalidate();
    }

    public void onViewReleased(View  r1, float  f0, float  f1)
    {

        float f4;
        int i0, i2, i3;
        f4 = this$0.getDrawerViewOffset(r1);
        i0 = r1.getWidth();

        label_2:
        if (this$0.checkDrawerViewAbsoluteGravity(r1, 3) == false)
        {
            i3 = this$0.getWidth();

            label_0:
            if (f0 - 0.0F >= 0)
            {
                if (f0 - 0.0F == 0)
                {
                    if (f4 - 0.5F > 0)
                    {
                        break label_0;
                    }
                }

                i2 = i3;
                break label_2;
            }

            i2 = i3 - i0;
        }
        else
        {
            label_1:
            if (f0 - 0.0F <= 0)
            {
                if (f0 - 0.0F == 0)
                {
                    if (f4 - 0.5F > 0)
                    {
                        break label_1;
                    }
                }

                i2 = (- (i0));
                break label_2;
            }

            i2 = 0;
        }

        mDragger.settleCapturedViewAt(i2, r1.getTop());
        this$0.invalidate();
    }

    private void peekDrawer()
    {

        int i1, i2, i3, i4;
        View r2;
        DrawerLayout$LayoutParams r6;
        i1 = 0;
        i2 = mDragger.getEdgeSize();

        if (mAbsGravity != 3)
        {
            i3 = 0;
        }
        else
        {
            i3 = (int) 1;
        }

        if (i3 == 0)
        {
            r2 = this$0.findDrawerWithGravity(5);
            i4 = this$0.getWidth() - i2;
        }
        else
        {
            r2 = this$0.findDrawerWithGravity(3);

            if (r2 != null)
            {
                i1 = (- (r2.getWidth()));
            }

            i4 = i1 + i2;
        }

        label_4:
        if (r2 != null)
        {
            label_3:
            {
                if (i3 != 0)
                {
                    if (r2.getLeft() < i4)
                    {
                        break label_3;
                    }
                }

                if (i3 != 0)
                {
                    break label_4;
                }
                else
                {
                    if (r2.getLeft() <= i4)
                    {
                        break label_4;
                    }
                }
            } //end label_3:


            if (this$0.getDrawerLockMode(r2) == 0)
            {
                r6 = (DrawerLayout$LayoutParams) r2.getLayoutParams();
                mDragger.smoothSlideViewTo(r2, i4, r2.getTop());
                r6.isPeeking = true;
                this$0.invalidate();
                this.closeOtherDrawer();
                this$0.cancelChildViewTouch();
            }
        }
    }

    public void removeCallbacks()
    {


        this$0.removeCallbacks(mPeekRunnable);
    }

    public void setDragger(ViewDragHelper  r1)
    {


        mDragger = r1;
    }

    public boolean tryCaptureView(View  r1, int  i0)
    {

        boolean z2;
        label_5:
        {
            if (this$0.isDrawerView(r1) != false)
            {
                if (this$0.checkDrawerViewAbsoluteGravity(r1, mAbsGravity) != false)
                {
                    if (this$0.getDrawerLockMode(r1) == 0)
                    {
                        z2 = true;
                        break label_5;
                    }
                }
            }

            z2 = false;
        } //end label_5:


        return z2;
    }
}
