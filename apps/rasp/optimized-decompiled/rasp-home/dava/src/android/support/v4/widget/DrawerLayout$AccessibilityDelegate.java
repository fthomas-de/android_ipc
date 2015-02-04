package android.support.v4.widget;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.ViewGroup;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;
import android.view.ViewParent;

class DrawerLayout$AccessibilityDelegate extends AccessibilityDelegateCompat
{
    private final Rect mTmpRect;
    final DrawerLayout this$0;

    DrawerLayout$AccessibilityDelegate(DrawerLayout  r1)
    {


        this$0 = r1;
        this.<init>();
        mTmpRect = new Rect();
    }

    private void addChildrenForAccessibility(AccessibilityNodeInfoCompat  r1, ViewGroup  r2)
    {

        int i0, i1;
        View r3;
        i0 = r2.getChildCount();
        i1 = 0;

        while (i1 < i0)
        {
            r3 = r2.getChildAt(i1);

            label_0:
            if (this.filter(r3) == false)
            {
                switch (ViewCompat.getImportantForAccessibility(r3))
                {
                    case 0:
                        ViewCompat.setImportantForAccessibility(r3, 1);

                    case 1:
                        r1.addChild(r3);
                        break label_0;

                    case 2:
                        if (r3 instanceof ViewGroup == false)
                        {
                            break label_0;
                        }
                        else
                        {
                            this.addChildrenForAccessibility(r1, (ViewGroup) r3);
                            break label_0;
                        }

                    case 4:
                        break label_0;

                    case 3:
                    default:
                        break label_0;
                }
            }

            i1 = i1 + 1;
        }
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
    }

    public boolean dispatchPopulateAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {

        List r3;
        View r4;
        int i2;
        CharSequence r5;
        boolean z1;
        if (r2.getEventType() != 32)
        {
            z1 = this.dispatchPopulateAccessibilityEvent(r1, r2);
        }
        else
        {
            r3 = r2.getText();
            r4 = DrawerLayout.access$200(this$0);

            if (r4 != null)
            {
                i2 = this$0.getDrawerViewAbsoluteGravity(r4);
                r5 = this$0.getDrawerTitle(i2);

                if (r5 != null)
                {
                    r3.add(r5);
                }
            }

            z1 = true;
        }

        return z1;
    }

    public boolean filter(View  r1)
    {

        View r3;
        boolean z0;
        r3 = this$0.findOpenDrawer();

        label_1:
        {
            if (r3 != null)
            {
                if (r3 != r1)
                {
                    z0 = true;
                    break label_1;
                }
            }

            z0 = false;
        } //end label_1:


        return z0;
    }

    public void onInitializeAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        this.onInitializeAccessibilityEvent(r1, r2);
        r2.setClassName(class "android/support/v4/widget/DrawerLayout".getName());
    }

    public void onInitializeAccessibilityNodeInfo(View  r1, AccessibilityNodeInfoCompat  r2)
    {

        AccessibilityNodeInfoCompat r3;
        ViewParent r5;
        r3 = AccessibilityNodeInfoCompat.obtain(r2);
        this.onInitializeAccessibilityNodeInfo(r1, r3);
        r2.setClassName(class "android/support/v4/widget/DrawerLayout".getName());
        r2.setSource(r1);
        r5 = ViewCompat.getParentForAccessibility(r1);

        if (r5 instanceof View != false)
        {
            r2.setParent((View) r5);
        }

        this.copyNodeInfoNoChildren(r2, r3);
        r3.recycle();
        this.addChildrenForAccessibility(r2, (ViewGroup) r1);
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
