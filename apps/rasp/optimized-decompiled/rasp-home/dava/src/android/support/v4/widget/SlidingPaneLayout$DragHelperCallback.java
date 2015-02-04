package android.support.v4.widget;

import android.view.View;

class SlidingPaneLayout$DragHelperCallback extends ViewDragHelper$Callback
{
    final SlidingPaneLayout this$0;

    private SlidingPaneLayout$DragHelperCallback(SlidingPaneLayout  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    SlidingPaneLayout$DragHelperCallback(SlidingPaneLayout  r1, SlidingPaneLayout$1  r2)
    {
        this(r1);


        this.<init>(r1);
    }

    public int clampViewPositionHorizontal(View  r1, int  i0, int  i1)
    {

        int i3, i4;
        SlidingPaneLayout$LayoutParams r5;
        r5 = (SlidingPaneLayout$LayoutParams) SlidingPaneLayout.access$400(this$0).getLayoutParams();
        i3 = this$0.getPaddingLeft() + r5.leftMargin;
        i4 = i3 + SlidingPaneLayout.access$700(this$0);
        return Math.min(Math.max(i0, i3), i4);
    }

    public int getViewHorizontalDragRange(View  r1)
    {


        return SlidingPaneLayout.access$700(this$0);
    }

    public void onEdgeDragStarted(int  i0, int  i1)
    {


        SlidingPaneLayout.access$200(this$0).captureChildView(SlidingPaneLayout.access$400(this$0), i1);
    }

    public void onViewCaptured(View  r1, int  i0)
    {


        this$0.setAllChildrenVisible();
    }

    public void onViewDragStateChanged(int  i0)
    {


        if (SlidingPaneLayout.access$200(this$0).getViewDragState() == 0)
        {
            if (SlidingPaneLayout.access$300(this$0) - 0.0F != 0)
            {
                this$0.dispatchOnPanelOpened(SlidingPaneLayout.access$400(this$0));
                SlidingPaneLayout.access$502(this$0, true);
            }
            else
            {
                this$0.updateObscuredViewsVisibility(SlidingPaneLayout.access$400(this$0));
                this$0.dispatchOnPanelClosed(SlidingPaneLayout.access$400(this$0));
                SlidingPaneLayout.access$502(this$0, false);
            }
        }
    }

    public void onViewPositionChanged(View  r1, int  i0, int  i1, int  i2, int  i3)
    {


        SlidingPaneLayout.access$600(this$0, i0);
        this$0.invalidate();
    }

    public void onViewReleased(View  r1, float  f0, float  f1)
    {

        int i1;
        SlidingPaneLayout$LayoutParams r4;
        r4 = (SlidingPaneLayout$LayoutParams) r1.getLayoutParams();
        i1 = this$0.getPaddingLeft() + r4.leftMargin;

        label_0:
        {
            if (f0 - 0.0F <= 0)
            {
                if (f0 - 0.0F != 0)
                {
                    break label_0;
                }
                else
                {
                    if (SlidingPaneLayout.access$300(this$0) - 0.5F <= 0)
                    {
                        break label_0;
                    }
                }
            }

            i1 = i1 + SlidingPaneLayout.access$700(this$0);
        } //end label_0:


        SlidingPaneLayout.access$200(this$0).settleCapturedViewAt(i1, r1.getTop());
        this$0.invalidate();
    }

    public boolean tryCaptureView(View  r1, int  i0)
    {

        boolean z1;
        if (SlidingPaneLayout.access$100(this$0) == false)
        {
            z1 = ((SlidingPaneLayout$LayoutParams) r1.getLayoutParams()).slideable;
        }
        else
        {
            z1 = false;
        }

        return z1;
    }
}
