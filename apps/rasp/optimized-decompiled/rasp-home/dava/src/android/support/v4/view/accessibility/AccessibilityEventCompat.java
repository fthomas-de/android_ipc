package android.support.v4.view.accessibility;

import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityEventCompat
{
    private static final AccessibilityEventCompat$AccessibilityEventVersionImpl IMPL;
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 16384;
    public static final int TYPE_GESTURE_DETECTION_END = 524288;
    public static final int TYPE_GESTURE_DETECTION_START = 262144;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;
    public static final int TYPE_TOUCH_INTERACTION_END = 2097152;
    public static final int TYPE_TOUCH_INTERACTION_START = 1048576;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 65536;
    public static final int TYPE_VIEW_HOVER_ENTER = 128;
    public static final int TYPE_VIEW_HOVER_EXIT = 256;
    public static final int TYPE_VIEW_SCROLLED = 4096;
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 131072;
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;

    static
    {


        if (Build$VERSION.SDK_INT < 14)
        {
            IMPL = new AccessibilityEventCompat$AccessibilityEventStubImpl();
        }
        else
        {
            IMPL = new AccessibilityEventCompat$AccessibilityEventIcsImpl();
        }
    }

    private AccessibilityEventCompat()
    {


        this.<init>();
    }

    public static void appendRecord(AccessibilityEvent  r0, AccessibilityRecordCompat  r1)
    {


        IMPL.appendRecord(r0, r1.getImpl());
    }

    public static AccessibilityRecordCompat asRecord(AccessibilityEvent  r0)
    {


        return new AccessibilityRecordCompat(r0);
    }

    public static AccessibilityRecordCompat getRecord(AccessibilityEvent  r0, int  i0)
    {


        return new AccessibilityRecordCompat(IMPL.getRecord(r0, i0));
    }

    public static int getRecordCount(AccessibilityEvent  r0)
    {


        return IMPL.getRecordCount(r0);
    }
}
