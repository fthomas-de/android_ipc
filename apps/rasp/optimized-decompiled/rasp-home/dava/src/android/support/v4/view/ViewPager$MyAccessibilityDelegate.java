package android.support.v4.view;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.os.Bundle;

class ViewPager$MyAccessibilityDelegate extends AccessibilityDelegateCompat
{
    final ViewPager this$0;

    ViewPager$MyAccessibilityDelegate(ViewPager  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    private boolean canScroll()
    {

        boolean z0;
        z0 = true;

        label_0:
        {
            if (ViewPager.access$200(this$0) != null)
            {
                if (ViewPager.access$200(this$0).getCount() > (int) 1)
                {
                    break label_0;
                }
            }

            z0 = false;
        } //end label_0:


        return z0;
    }

    public void onInitializeAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {

        AccessibilityRecordCompat r4;
        this.onInitializeAccessibilityEvent(r1, r2);
        r2.setClassName(class "android/support/v4/view/ViewPager".getName());
        r4 = AccessibilityRecordCompat.obtain();
        r4.setScrollable(this.canScroll());

        if (r2.getEventType() == 4096)
        {
            if (ViewPager.access$200(this$0) != null)
            {
                r4.setItemCount(ViewPager.access$200(this$0).getCount());
                r4.setFromIndex(ViewPager.access$300(this$0));
                r4.setToIndex(ViewPager.access$300(this$0));
            }
        }
    }

    public void onInitializeAccessibilityNodeInfo(View  r1, AccessibilityNodeInfoCompat  r2)
    {


        this.onInitializeAccessibilityNodeInfo(r1, r2);
        r2.setClassName(class "android/support/v4/view/ViewPager".getName());
        r2.setScrollable(this.canScroll());

        if (this$0.canScrollHorizontally(1) != false)
        {
            r2.addAction(4096);
        }

        if (this$0.canScrollHorizontally(-1) != false)
        {
            r2.addAction(8192);
        }
    }

    public boolean performAccessibilityAction(View  r1, int  i0, Bundle  r2)
    {

        boolean z1;
        z1 = true;

        label_1:
        if (this.performAccessibilityAction(r1, i0, r2) == false)
        {
            switch (i0)
            {
                case 4096:
                    if (this$0.canScrollHorizontally((int) 1) == false)
                    {
                        z1 = false;
                        break label_1;
                    }
                    else
                    {
                        this$0.setCurrentItem(ViewPager.access$300(this$0) + 1);
                        break label_1;
                    }

                case 8192:
                    if (this$0.canScrollHorizontally(-1) == false)
                    {
                        z1 = false;
                        break label_1;
                    }
                    else
                    {
                        this$0.setCurrentItem(ViewPager.access$300(this$0) + -1);
                        break label_1;
                    }

                default:
                    z1 = false;
                    break label_1;
            }
        }

        return z1;
    }
}
