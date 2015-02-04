package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

class AccessibilityEventCompat$AccessibilityEventIcsImpl extends AccessibilityEventCompat$AccessibilityEventStubImpl
{

    AccessibilityEventCompat$AccessibilityEventIcsImpl()
    {


        this.<init>();
    }

    public void appendRecord(AccessibilityEvent  r1, Object  r2)
    {


        AccessibilityEventCompatIcs.appendRecord(r1, r2);
    }

    public Object getRecord(AccessibilityEvent  r1, int  i0)
    {


        return AccessibilityEventCompatIcs.getRecord(r1, i0);
    }

    public int getRecordCount(AccessibilityEvent  r1)
    {


        return AccessibilityEventCompatIcs.getRecordCount(r1);
    }
}
