package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;
import android.os.Bundle;

class ViewCompatJB
{

    ViewCompatJB()
    {


        this.<init>();
    }

    public static Object getAccessibilityNodeProvider(View  r0)
    {


        return r0.getAccessibilityNodeProvider();
    }

    public static int getImportantForAccessibility(View  r0)
    {


        return r0.getImportantForAccessibility();
    }

    public static ViewParent getParentForAccessibility(View  r0)
    {


        return r0.getParentForAccessibility();
    }

    public static boolean hasTransientState(View  r0)
    {


        return r0.hasTransientState();
    }

    public static boolean performAccessibilityAction(View  r0, int  i0, Bundle  r1)
    {


        return r0.performAccessibilityAction(i0, r1);
    }

    public static void postInvalidateOnAnimation(View  r0)
    {


        r0.postInvalidateOnAnimation();
    }

    public static void postInvalidateOnAnimation(View  r0, int  i0, int  i1, int  i2, int  i3)
    {


        r0.postInvalidate(i0, i1, i2, i3);
    }

    public static void postOnAnimation(View  r0, Runnable  r1)
    {


        r0.postOnAnimation(r1);
    }

    public static void postOnAnimationDelayed(View  r0, Runnable  r1, long  l0)
    {


        r0.postOnAnimationDelayed(r1, l0);
    }

    public static void setHasTransientState(View  r0, boolean  z0)
    {


        r0.setHasTransientState(z0);
    }

    public static void setImportantForAccessibility(View  r0, int  i0)
    {


        r0.setImportantForAccessibility(i0);
    }
}
