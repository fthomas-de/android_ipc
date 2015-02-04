package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.os.Bundle;
import android.graphics.Paint;

public class ViewCompat
{
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    private static final long FAKE_FRAME_TIME = 10l;
    static final ViewCompat$ViewCompatImpl IMPL;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    public static final int LAYER_TYPE_HARDWARE = 2;
    public static final int LAYER_TYPE_NONE = 0;
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
    public static final int MEASURED_SIZE_MASK = 16777215;
    public static final int MEASURED_STATE_MASK = -16777216;
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;
    public static final int OVER_SCROLL_ALWAYS = 0;
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    public static final int OVER_SCROLL_NEVER = 2;

    static
    {

        int i0;
        i0 = Build$VERSION.SDK_INT;

        if (i0 < 19)
        {
            if (i0 < 17)
            {
                if (i0 < 16)
                {
                    if (i0 < 14)
                    {
                        if (i0 < 11)
                        {
                            if (i0 < 9)
                            {
                                IMPL = new ViewCompat$BaseViewCompatImpl();
                            }
                            else
                            {
                                IMPL = new ViewCompat$GBViewCompatImpl();
                            }
                        }
                        else
                        {
                            IMPL = new ViewCompat$HCViewCompatImpl();
                        }
                    }
                    else
                    {
                        IMPL = new ViewCompat$ICSViewCompatImpl();
                    }
                }
                else
                {
                    IMPL = new ViewCompat$JBViewCompatImpl();
                }
            }
            else
            {
                IMPL = new ViewCompat$JbMr1ViewCompatImpl();
            }
        }
        else
        {
            IMPL = new ViewCompat$KitKatViewCompatImpl();
        }
    }

    public ViewCompat()
    {


        this.<init>();
    }

    public static boolean canScrollHorizontally(View  r0, int  i0)
    {


        return IMPL.canScrollHorizontally(r0, i0);
    }

    public static boolean canScrollVertically(View  r0, int  i0)
    {


        return IMPL.canScrollVertically(r0, i0);
    }

    public int getAccessibilityLiveRegion(View  r1)
    {


        return IMPL.getAccessibilityLiveRegion(r1);
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View  r0)
    {


        return IMPL.getAccessibilityNodeProvider(r0);
    }

    public static float getAlpha(View  r0)
    {


        return IMPL.getAlpha(r0);
    }

    public static int getImportantForAccessibility(View  r0)
    {


        return IMPL.getImportantForAccessibility(r0);
    }

    public static int getLabelFor(View  r0)
    {


        return IMPL.getLabelFor(r0);
    }

    public static int getLayerType(View  r0)
    {


        return IMPL.getLayerType(r0);
    }

    public static int getLayoutDirection(View  r0)
    {


        return IMPL.getLayoutDirection(r0);
    }

    public static int getMeasuredHeightAndState(View  r0)
    {


        return IMPL.getMeasuredHeightAndState(r0);
    }

    public static int getMeasuredState(View  r0)
    {


        return IMPL.getMeasuredState(r0);
    }

    public static int getMeasuredWidthAndState(View  r0)
    {


        return IMPL.getMeasuredWidthAndState(r0);
    }

    public static int getOverScrollMode(View  r0)
    {


        return IMPL.getOverScrollMode(r0);
    }

    public static ViewParent getParentForAccessibility(View  r0)
    {


        return IMPL.getParentForAccessibility(r0);
    }

    public static boolean hasTransientState(View  r0)
    {


        return IMPL.hasTransientState(r0);
    }

    public static boolean isOpaque(View  r0)
    {


        return IMPL.isOpaque(r0);
    }

    public static void onInitializeAccessibilityEvent(View  r0, AccessibilityEvent  r1)
    {


        IMPL.onInitializeAccessibilityEvent(r0, r1);
    }

    public static void onInitializeAccessibilityNodeInfo(View  r0, AccessibilityNodeInfoCompat  r1)
    {


        IMPL.onInitializeAccessibilityNodeInfo(r0, r1);
    }

    public static void onPopulateAccessibilityEvent(View  r0, AccessibilityEvent  r1)
    {


        IMPL.onPopulateAccessibilityEvent(r0, r1);
    }

    public static boolean performAccessibilityAction(View  r0, int  i0, Bundle  r1)
    {


        return IMPL.performAccessibilityAction(r0, i0, r1);
    }

    public static void postInvalidateOnAnimation(View  r0)
    {


        IMPL.postInvalidateOnAnimation(r0);
    }

    public static void postInvalidateOnAnimation(View  r0, int  i0, int  i1, int  i2, int  i3)
    {


        IMPL.postInvalidateOnAnimation(r0, i0, i1, i2, i3);
    }

    public static void postOnAnimation(View  r0, Runnable  r1)
    {


        IMPL.postOnAnimation(r0, r1);
    }

    public static void postOnAnimationDelayed(View  r0, Runnable  r1, long  l0)
    {


        IMPL.postOnAnimationDelayed(r0, r1, l0);
    }

    public static int resolveSizeAndState(int  i0, int  i1, int  i2)
    {


        return IMPL.resolveSizeAndState(i0, i1, i2);
    }

    public static void setAccessibilityDelegate(View  r0, AccessibilityDelegateCompat  r1)
    {


        IMPL.setAccessibilityDelegate(r0, r1);
    }

    public void setAccessibilityLiveRegion(View  r1, int  i0)
    {


        IMPL.setAccessibilityLiveRegion(r1, i0);
    }

    public static void setHasTransientState(View  r0, boolean  z0)
    {


        IMPL.setHasTransientState(r0, z0);
    }

    public static void setImportantForAccessibility(View  r0, int  i0)
    {


        IMPL.setImportantForAccessibility(r0, i0);
    }

    public static void setLabelFor(View  r0, int  i0)
    {


        IMPL.setLabelFor(r0, i0);
    }

    public static void setLayerPaint(View  r0, Paint  r1)
    {


        IMPL.setLayerPaint(r0, r1);
    }

    public static void setLayerType(View  r0, int  i0, Paint  r1)
    {


        IMPL.setLayerType(r0, i0, r1);
    }

    public static void setLayoutDirection(View  r0, int  i0)
    {


        IMPL.setLayoutDirection(r0, i0);
    }

    public static void setOverScrollMode(View  r0, int  i0)
    {


        IMPL.setOverScrollMode(r0, i0);
    }
}
