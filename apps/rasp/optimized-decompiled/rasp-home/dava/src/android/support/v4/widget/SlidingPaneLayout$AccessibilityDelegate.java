package android.support.v4.widget;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewParent;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;

class SlidingPaneLayout$AccessibilityDelegate extends AccessibilityDelegateCompat
{
    private final Rect mTmpRect;
    final SlidingPaneLayout this$0;

    SlidingPaneLayout$AccessibilityDelegate(SlidingPaneLayout  r1)
    {


        this$0 = r1;
        this.<init>();
        mTmpRect = new Rect();
    }

    private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat  r1, AccessibilityNodeInfoCompat  r2)
    {

        Rect r3;
        r3 = mTmpRect;
        r2.getBoundsInParent(r3);
        r1.setBoundsInParent(r3);
        r2.getBoundsInScreen(r3);
        r1.setBoundsInScreen(r3);
        r1.setVisibleToUser(r2.isVisibleToUser());
        r1.setPackageName(r2.getPackageName());
        r1.setClassName(r2.getClassName());
        r1.setContentDescription(r2.getContentDescription());
        r1.setEnabled(r2.isEnabled());
        r1.setClickable(r2.isClickable());
        r1.setFocusable(r2.isFocusable());
        r1.setFocused(r2.isFocused());
        r1.setAccessibilityFocused(r2.isAccessibilityFocused());
        r1.setSelected(r2.isSelected());
        r1.setLongClickable(r2.isLongClickable());
        r1.addAction(r2.getActions());
        r1.setMovementGranularities(r2.getMovementGranularities());
    }

    public boolean filter(View  r1)
    {


        return this$0.isDimmed(r1);
    }

    public void onInitializeAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        this.onInitializeAccessibilityEvent(r1, r2);
        r2.setClassName(class "android/support/v4/widget/SlidingPaneLayout".getName());
    }

    public void onInitializeAccessibilityNodeInfo(View  r1, AccessibilityNodeInfoCompat  r2)
    {

        AccessibilityNodeInfoCompat r3;
        ViewParent r5;
        int i0, i1;
        View r6;
        r3 = AccessibilityNodeInfoCompat.obtain(r2);
        this.onInitializeAccessibilityNodeInfo(r1, r3);
        this.copyNodeInfoNoChildren(r2, r3);
        r3.recycle();
        r2.setClassName(class "android/support/v4/widget/SlidingPaneLayout".getName());
        r2.setSource(r1);
        r5 = ViewCompat.getParentForAccessibility(r1);

        if (r5 instanceof View != false)
        {
            r2.setParent((View) r5);
        }

        i0 = this$0.getChildCount();
        i1 = 0;

        while (i1 < i0)
        {
            r6 = this$0.getChildAt(i1);

            if (this.filter(r6) == false)
            {
                if (r6.getVisibility() == 0)
                {
                    ViewCompat.setImportantForAccessibility(r6, 1);
                    r2.addChild(r6);
                }
            }

            i1 = i1 + 1;
        }
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup  r1, View  r2, AccessibilityEvent  r3)
    {

        boolean z1;
        if (this.filter(r2) != false)
        {
            z1 = false;
        }
        else
        {
            z1 = this.onRequestSendAccessibilityEvent(r1, r2, r3);
        }

        return z1;
    }
}
