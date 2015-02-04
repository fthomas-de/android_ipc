package android.support.v4.view.accessibility;

import android.view.View;
import android.os.Bundle;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanImpl extends AccessibilityNodeInfoCompat$AccessibilityNodeInfoIcsImpl
{

    AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanImpl()
    {


        this.<init>();
    }

    public void addChild(Object  r1, View  r2, int  i0)
    {


        AccessibilityNodeInfoCompatJellyBean.addChild(r1, r2, i0);
    }

    public Object findFocus(Object  r1, int  i0)
    {


        return AccessibilityNodeInfoCompatJellyBean.findFocus(r1, i0);
    }

    public Object focusSearch(Object  r1, int  i0)
    {


        return AccessibilityNodeInfoCompatJellyBean.focusSearch(r1, i0);
    }

    public int getMovementGranularities(Object  r1)
    {


        return AccessibilityNodeInfoCompatJellyBean.getMovementGranularities(r1);
    }

    public boolean isAccessibilityFocused(Object  r1)
    {


        return AccessibilityNodeInfoCompatJellyBean.isAccessibilityFocused(r1);
    }

    public boolean isVisibleToUser(Object  r1)
    {


        return AccessibilityNodeInfoCompatJellyBean.isVisibleToUser(r1);
    }

    public Object obtain(View  r1, int  i0)
    {


        return AccessibilityNodeInfoCompatJellyBean.obtain(r1, i0);
    }

    public boolean performAction(Object  r1, int  i0, Bundle  r2)
    {


        return AccessibilityNodeInfoCompatJellyBean.performAction(r1, i0, r2);
    }

    public void setAccessibilityFocused(Object  r1, boolean  z0)
    {


        AccessibilityNodeInfoCompatJellyBean.setAccesibilityFocused(r1, z0);
    }

    public void setMovementGranularities(Object  r1, int  i0)
    {


        AccessibilityNodeInfoCompatJellyBean.setMovementGranularities(r1, i0);
    }

    public void setParent(Object  r1, View  r2, int  i0)
    {


        AccessibilityNodeInfoCompatJellyBean.setParent(r1, r2, i0);
    }

    public void setSource(Object  r1, View  r2, int  i0)
    {


        AccessibilityNodeInfoCompatJellyBean.setSource(r1, r2, i0);
    }

    public void setVisibleToUser(Object  r1, boolean  z0)
    {


        AccessibilityNodeInfoCompatJellyBean.setVisibleToUser(r1, z0);
    }
}
