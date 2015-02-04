package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

class AccessibilityEventCompatIcs
{

    AccessibilityEventCompatIcs()
    {


        this.<init>();
    }

    public static void appendRecord(AccessibilityEvent  r0, Object  r1)
    {


        r0.appendRecord((AccessibilityRecord) r1);
    }

    public static Object getRecord(AccessibilityEvent  r0, int  i0)
    {


        return r0.getRecord(i0);
    }

    public static int getRecordCount(AccessibilityEvent  r0)
    {


        return r0.getRecordCount();
    }
}
