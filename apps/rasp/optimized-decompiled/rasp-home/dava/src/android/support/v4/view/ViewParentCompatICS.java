package android.support.v4.view;

import android.view.ViewParent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class ViewParentCompatICS
{

    public ViewParentCompatICS()
    {


        this.<init>();
    }

    public static boolean requestSendAccessibilityEvent(ViewParent  r0, View  r1, AccessibilityEvent  r2)
    {


        return r0.requestSendAccessibilityEvent(r1, r2);
    }
}
