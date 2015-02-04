package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;
import android.view.View;
import java.util.List;
import android.graphics.Rect;

class AccessibilityNodeInfoCompatIcs
{

    AccessibilityNodeInfoCompatIcs()
    {


        this.<init>();
    }

    public static void addAction(Object  r0, int  i0)
    {


        ((AccessibilityNodeInfo) r0).addAction(i0);
    }

    public static void addChild(Object  r0, View  r1)
    {


        ((AccessibilityNodeInfo) r0).addChild(r1);
    }

    public static List findAccessibilityNodeInfosByText(Object  r0, String  r1)
    {


        return (List) ((AccessibilityNodeInfo) r0).findAccessibilityNodeInfosByText(r1);
    }

    public static int getActions(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).getActions();
    }

    public static void getBoundsInParent(Object  r0, Rect  r1)
    {


        ((AccessibilityNodeInfo) r0).getBoundsInParent(r1);
    }

    public static void getBoundsInScreen(Object  r0, Rect  r1)
    {


        ((AccessibilityNodeInfo) r0).getBoundsInScreen(r1);
    }

    public static Object getChild(Object  r0, int  i0)
    {


        return ((AccessibilityNodeInfo) r0).getChild(i0);
    }

    public static int getChildCount(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).getChildCount();
    }

    public static CharSequence getClassName(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).getClassName();
    }

    public static CharSequence getContentDescription(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).getContentDescription();
    }

    public static CharSequence getPackageName(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).getPackageName();
    }

    public static Object getParent(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).getParent();
    }

    public static CharSequence getText(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).getText();
    }

    public static int getWindowId(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).getWindowId();
    }

    public static boolean isCheckable(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isCheckable();
    }

    public static boolean isChecked(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isChecked();
    }

    public static boolean isClickable(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isClickable();
    }

    public static boolean isEnabled(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isEnabled();
    }

    public static boolean isFocusable(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isFocusable();
    }

    public static boolean isFocused(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isFocused();
    }

    public static boolean isLongClickable(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isLongClickable();
    }

    public static boolean isPassword(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isPassword();
    }

    public static boolean isScrollable(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isScrollable();
    }

    public static boolean isSelected(Object  r0)
    {


        return ((AccessibilityNodeInfo) r0).isSelected();
    }

    public static Object obtain()
    {


        return AccessibilityNodeInfo.obtain();
    }

    public static Object obtain(View  r0)
    {


        return AccessibilityNodeInfo.obtain(r0);
    }

    public static Object obtain(Object  r0)
    {


        return AccessibilityNodeInfo.obtain((AccessibilityNodeInfo) r0);
    }

    public static boolean performAction(Object  r0, int  i0)
    {


        return ((AccessibilityNodeInfo) r0).performAction(i0);
    }

    public static void recycle(Object  r0)
    {


        ((AccessibilityNodeInfo) r0).recycle();
    }

    public static void setBoundsInParent(Object  r0, Rect  r1)
    {


        ((AccessibilityNodeInfo) r0).setBoundsInParent(r1);
    }

    public static void setBoundsInScreen(Object  r0, Rect  r1)
    {


        ((AccessibilityNodeInfo) r0).setBoundsInScreen(r1);
    }

    public static void setCheckable(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setCheckable(z0);
    }

    public static void setChecked(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setChecked(z0);
    }

    public static void setClassName(Object  r0, CharSequence  r1)
    {


        ((AccessibilityNodeInfo) r0).setClassName(r1);
    }

    public static void setClickable(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setClickable(z0);
    }

    public static void setContentDescription(Object  r0, CharSequence  r1)
    {


        ((AccessibilityNodeInfo) r0).setContentDescription(r1);
    }

    public static void setEnabled(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setEnabled(z0);
    }

    public static void setFocusable(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setFocusable(z0);
    }

    public static void setFocused(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setFocused(z0);
    }

    public static void setLongClickable(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setLongClickable(z0);
    }

    public static void setPackageName(Object  r0, CharSequence  r1)
    {


        ((AccessibilityNodeInfo) r0).setPackageName(r1);
    }

    public static void setParent(Object  r0, View  r1)
    {


        ((AccessibilityNodeInfo) r0).setParent(r1);
    }

    public static void setPassword(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setPassword(z0);
    }

    public static void setScrollable(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setScrollable(z0);
    }

    public static void setSelected(Object  r0, boolean  z0)
    {


        ((AccessibilityNodeInfo) r0).setSelected(z0);
    }

    public static void setSource(Object  r0, View  r1)
    {


        ((AccessibilityNodeInfo) r0).setSource(r1);
    }

    public static void setText(Object  r0, CharSequence  r1)
    {


        ((AccessibilityNodeInfo) r0).setText(r1);
    }
}
