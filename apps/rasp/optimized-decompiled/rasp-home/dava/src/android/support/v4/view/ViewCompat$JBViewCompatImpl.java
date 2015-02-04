package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;
import android.os.Bundle;

class ViewCompat$JBViewCompatImpl extends ViewCompat$ICSViewCompatImpl
{

    ViewCompat$JBViewCompatImpl()
    {


        this.<init>();
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View  r1)
    {

        Object r2;
        AccessibilityNodeProviderCompat r3;
        r2 = ViewCompatJB.getAccessibilityNodeProvider(r1);

        if (r2 == null)
        {
            r3 = null;
        }
        else
        {
            r3 = new AccessibilityNodeProviderCompat(r2);
        }

        return r3;
    }

    public int getImportantForAccessibility(View  r1)
    {


        return ViewCompatJB.getImportantForAccessibility(r1);
    }

    public ViewParent getParentForAccessibility(View  r1)
    {


        return ViewCompatJB.getParentForAccessibility(r1);
    }

    public boolean hasTransientState(View  r1)
    {


        return ViewCompatJB.hasTransientState(r1);
    }

    public boolean performAccessibilityAction(View  r1, int  i0, Bundle  r2)
    {


        return ViewCompatJB.performAccessibilityAction(r1, i0, r2);
    }

    public void postInvalidateOnAnimation(View  r1)
    {


        ViewCompatJB.postInvalidateOnAnimation(r1);
    }

    public void postInvalidateOnAnimation(View  r1, int  i0, int  i1, int  i2, int  i3)
    {


        ViewCompatJB.postInvalidateOnAnimation(r1, i0, i1, i2, i3);
    }

    public void postOnAnimation(View  r1, Runnable  r2)
    {


        ViewCompatJB.postOnAnimation(r1, r2);
    }

    public void postOnAnimationDelayed(View  r1, Runnable  r2, long  l0)
    {


        ViewCompatJB.postOnAnimationDelayed(r1, r2, l0);
    }

    public void setHasTransientState(View  r1, boolean  z0)
    {


        ViewCompatJB.setHasTransientState(r1, z0);
    }

    public void setImportantForAccessibility(View  r1, int  i0)
    {


        ViewCompatJB.setImportantForAccessibility(r1, i0);
    }
}
