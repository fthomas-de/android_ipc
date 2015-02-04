package android.support.v4.view.accessibility;

import android.os.Build$VERSION;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Rect;
import android.os.Bundle;

public class AccessibilityNodeInfoCompat
{
    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COPY = 16384;
    public static final int ACTION_CUT = 65536;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 32;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
    public static final int ACTION_PASTE = 32768;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
    public static final int ACTION_SCROLL_BACKWARD = 8192;
    public static final int ACTION_SCROLL_FORWARD = 4096;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 131072;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    private static final AccessibilityNodeInfoCompat$AccessibilityNodeInfoImpl IMPL;
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    private final Object mInfo;

    static
    {


        if (Build$VERSION.SDK_INT < 19)
        {
            if (Build$VERSION.SDK_INT < 18)
            {
                if (Build$VERSION.SDK_INT < 16)
                {
                    if (Build$VERSION.SDK_INT < 14)
                    {
                        IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoStubImpl();
                    }
                    else
                    {
                        IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoIcsImpl();
                    }
                }
                else
                {
                    IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanImpl();
                }
            }
            else
            {
                IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanMr2Impl();
            }
        }
        else
        {
            IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoKitKatImpl();
        }
    }

    public AccessibilityNodeInfoCompat(Object  r1)
    {


        this.<init>();
        mInfo = r1;
    }

    public void addAction(int  i0)
    {


        IMPL.addAction(mInfo, i0);
    }

    public void addChild(View  r1)
    {


        IMPL.addChild(mInfo, r1);
    }

    public void addChild(View  r1, int  i0)
    {


        IMPL.addChild(mInfo, r1, i0);
    }

    public boolean equals(Object  r1)
    {

        boolean z0;
        AccessibilityNodeInfoCompat r5;
        z0 = true;

        if (this != r1)
        {
            if (r1 != null)
            {
                if (this.getClass() == r1.getClass())
                {
                    r5 = (AccessibilityNodeInfoCompat) r1;

                    if (mInfo != null)
                    {
                        if (mInfo.equals(r5.mInfo) == false)
                        {
                            z0 = false;
                        }
                    }
                    else
                    {
                        if (r5.mInfo != null)
                        {
                            z0 = false;
                        }
                    }
                }
                else
                {
                    z0 = false;
                }
            }
            else
            {
                z0 = false;
            }
        }

        return z0;
    }

    public List findAccessibilityNodeInfosByText(String  r1)
    {

        ArrayList r2;
        List r5;
        int i0, i1;
        r2 = new ArrayList();
        r5 = IMPL.findAccessibilityNodeInfosByText(mInfo, r1);
        i0 = r5.size();
        i1 = 0;

        while (i1 < i0)
        {
            r2.add(new AccessibilityNodeInfoCompat(r5.get(i1)));
            i1 = i1 + 1;
        }

        return r2;
    }

    public AccessibilityNodeInfoCompat findFocus(int  i0)
    {


        return AccessibilityNodeInfoCompat.wrapNonNullInstance(IMPL.findFocus(mInfo, i0));
    }

    public AccessibilityNodeInfoCompat focusSearch(int  i0)
    {


        return AccessibilityNodeInfoCompat.wrapNonNullInstance(IMPL.focusSearch(mInfo, i0));
    }

    private static String getActionSymbolicName(int  i0)
    {

        String r0;
        label_0:
        switch (i0)
        {
            case 1:
                r0 = "ACTION_FOCUS";
                break label_0;

            case 2:
                r0 = "ACTION_CLEAR_FOCUS";
                break label_0;

            case 4:
                r0 = "ACTION_SELECT";
                break label_0;

            case 8:
                r0 = "ACTION_CLEAR_SELECTION";
                break label_0;

            case 16:
                r0 = "ACTION_CLICK";
                break label_0;

            case 32:
                r0 = "ACTION_LONG_CLICK";
                break label_0;

            case 64:
                r0 = "ACTION_ACCESSIBILITY_FOCUS";
                break label_0;

            case 128:
                r0 = "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                break label_0;

            case 256:
                r0 = "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                break label_0;

            case 512:
                r0 = "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                break label_0;

            case 1024:
                r0 = "ACTION_NEXT_HTML_ELEMENT";
                break label_0;

            case 2048:
                r0 = "ACTION_PREVIOUS_HTML_ELEMENT";
                break label_0;

            case 4096:
                r0 = "ACTION_SCROLL_FORWARD";
                break label_0;

            case 8192:
                r0 = "ACTION_SCROLL_BACKWARD";
                break label_0;

            case 16384:
                r0 = "ACTION_COPY";
                break label_0;

            case 32768:
                r0 = "ACTION_PASTE";
                break label_0;

            case 65536:
                r0 = "ACTION_CUT";
                break label_0;

            case 131072:
                r0 = "ACTION_SET_SELECTION";
                break label_0;

            default:
                r0 = "ACTION_UNKNOWN";
                break label_0;
        }

        return r0;
    }

    public int getActions()
    {


        return IMPL.getActions(mInfo);
    }

    public void getBoundsInParent(Rect  r1)
    {


        IMPL.getBoundsInParent(mInfo, r1);
    }

    public void getBoundsInScreen(Rect  r1)
    {


        IMPL.getBoundsInScreen(mInfo, r1);
    }

    public AccessibilityNodeInfoCompat getChild(int  i0)
    {


        return AccessibilityNodeInfoCompat.wrapNonNullInstance(IMPL.getChild(mInfo, i0));
    }

    public int getChildCount()
    {


        return IMPL.getChildCount(mInfo);
    }

    public CharSequence getClassName()
    {


        return IMPL.getClassName(mInfo);
    }

    public CharSequence getContentDescription()
    {


        return IMPL.getContentDescription(mInfo);
    }

    public Object getInfo()
    {


        return mInfo;
    }

    public int getLiveRegion()
    {


        return IMPL.getLiveRegion(mInfo);
    }

    public int getMovementGranularities()
    {


        return IMPL.getMovementGranularities(mInfo);
    }

    public CharSequence getPackageName()
    {


        return IMPL.getPackageName(mInfo);
    }

    public AccessibilityNodeInfoCompat getParent()
    {


        return AccessibilityNodeInfoCompat.wrapNonNullInstance(IMPL.getParent(mInfo));
    }

    public CharSequence getText()
    {


        return IMPL.getText(mInfo);
    }

    public String getViewIdResourceName()
    {


        return IMPL.getViewIdResourceName(mInfo);
    }

    public int getWindowId()
    {


        return IMPL.getWindowId(mInfo);
    }

    public int hashCode()
    {

        int i0;
        if (mInfo != null)
        {
            i0 = mInfo.hashCode();
        }
        else
        {
            i0 = 0;
        }

        return i0;
    }

    public boolean isAccessibilityFocused()
    {


        return IMPL.isAccessibilityFocused(mInfo);
    }

    public boolean isCheckable()
    {


        return IMPL.isCheckable(mInfo);
    }

    public boolean isChecked()
    {


        return IMPL.isChecked(mInfo);
    }

    public boolean isClickable()
    {


        return IMPL.isClickable(mInfo);
    }

    public boolean isEnabled()
    {


        return IMPL.isEnabled(mInfo);
    }

    public boolean isFocusable()
    {


        return IMPL.isFocusable(mInfo);
    }

    public boolean isFocused()
    {


        return IMPL.isFocused(mInfo);
    }

    public boolean isLongClickable()
    {


        return IMPL.isLongClickable(mInfo);
    }

    public boolean isPassword()
    {


        return IMPL.isPassword(mInfo);
    }

    public boolean isScrollable()
    {


        return IMPL.isScrollable(mInfo);
    }

    public boolean isSelected()
    {


        return IMPL.isSelected(mInfo);
    }

    public boolean isVisibleToUser()
    {


        return IMPL.isVisibleToUser(mInfo);
    }

    public static AccessibilityNodeInfoCompat obtain()
    {


        return AccessibilityNodeInfoCompat.wrapNonNullInstance(IMPL.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat  r0)
    {


        return AccessibilityNodeInfoCompat.wrapNonNullInstance(IMPL.obtain(r0.mInfo));
    }

    public static AccessibilityNodeInfoCompat obtain(View  r0)
    {


        return AccessibilityNodeInfoCompat.wrapNonNullInstance(IMPL.obtain(r0));
    }

    public static AccessibilityNodeInfoCompat obtain(View  r0, int  i0)
    {


        return AccessibilityNodeInfoCompat.wrapNonNullInstance(IMPL.obtain(r0, i0));
    }

    public boolean performAction(int  i0)
    {


        return IMPL.performAction(mInfo, i0);
    }

    public boolean performAction(int  i0, Bundle  r1)
    {


        return IMPL.performAction(mInfo, i0, r1);
    }

    public void recycle()
    {


        IMPL.recycle(mInfo);
    }

    public void setAccessibilityFocused(boolean  z0)
    {


        IMPL.setAccessibilityFocused(mInfo, z0);
    }

    public void setBoundsInParent(Rect  r1)
    {


        IMPL.setBoundsInParent(mInfo, r1);
    }

    public void setBoundsInScreen(Rect  r1)
    {


        IMPL.setBoundsInScreen(mInfo, r1);
    }

    public void setCheckable(boolean  z0)
    {


        IMPL.setCheckable(mInfo, z0);
    }

    public void setChecked(boolean  z0)
    {


        IMPL.setChecked(mInfo, z0);
    }

    public void setClassName(CharSequence  r1)
    {


        IMPL.setClassName(mInfo, r1);
    }

    public void setClickable(boolean  z0)
    {


        IMPL.setClickable(mInfo, z0);
    }

    public void setContentDescription(CharSequence  r1)
    {


        IMPL.setContentDescription(mInfo, r1);
    }

    public void setEnabled(boolean  z0)
    {


        IMPL.setEnabled(mInfo, z0);
    }

    public void setFocusable(boolean  z0)
    {


        IMPL.setFocusable(mInfo, z0);
    }

    public void setFocused(boolean  z0)
    {


        IMPL.setFocused(mInfo, z0);
    }

    public void setLiveRegion(int  i0)
    {


        IMPL.setLiveRegion(mInfo, i0);
    }

    public void setLongClickable(boolean  z0)
    {


        IMPL.setLongClickable(mInfo, z0);
    }

    public void setMovementGranularities(int  i0)
    {


        IMPL.setMovementGranularities(mInfo, i0);
    }

    public void setPackageName(CharSequence  r1)
    {


        IMPL.setPackageName(mInfo, r1);
    }

    public void setParent(View  r1)
    {


        IMPL.setParent(mInfo, r1);
    }

    public void setParent(View  r1, int  i0)
    {


        IMPL.setParent(mInfo, r1, i0);
    }

    public void setPassword(boolean  z0)
    {


        IMPL.setPassword(mInfo, z0);
    }

    public void setScrollable(boolean  z0)
    {


        IMPL.setScrollable(mInfo, z0);
    }

    public void setSelected(boolean  z0)
    {


        IMPL.setSelected(mInfo, z0);
    }

    public void setSource(View  r1)
    {


        IMPL.setSource(mInfo, r1);
    }

    public void setSource(View  r1, int  i0)
    {


        IMPL.setSource(mInfo, r1, i0);
    }

    public void setText(CharSequence  r1)
    {


        IMPL.setText(mInfo, r1);
    }

    public void setViewIdResourceName(String  r1)
    {


        IMPL.setViewIdResourceName(mInfo, r1);
    }

    public void setVisibleToUser(boolean  z0)
    {


        IMPL.setVisibleToUser(mInfo, z0);
    }

    public String toString()
    {

        StringBuilder r1;
        Rect r3;
        int i0, i1;
        r1 = new StringBuilder();
        r1.append(this.toString());
        r3 = new Rect();
        this.getBoundsInParent(r3);
        r1.append((new StringBuilder()).append("; boundsInParent: ").append(r3).toString());
        this.getBoundsInScreen(r3);
        r1.append((new StringBuilder()).append("; boundsInScreen: ").append(r3).toString());
        r1.append("; packageName: ").append(this.getPackageName());
        r1.append("; className: ").append(this.getClassName());
        r1.append("; text: ").append(this.getText());
        r1.append("; contentDescription: ").append(this.getContentDescription());
        r1.append("; viewId: ").append(this.getViewIdResourceName());
        r1.append("; checkable: ").append(this.isCheckable());
        r1.append("; checked: ").append(this.isChecked());
        r1.append("; focusable: ").append(this.isFocusable());
        r1.append("; focused: ").append(this.isFocused());
        r1.append("; selected: ").append(this.isSelected());
        r1.append("; clickable: ").append(this.isClickable());
        r1.append("; longClickable: ").append(this.isLongClickable());
        r1.append("; enabled: ").append(this.isEnabled());
        r1.append("; password: ").append(this.isPassword());
        r1.append((new StringBuilder()).append("; scrollable: ").append(this.isScrollable()).toString());
        r1.append("; [");
        i0 = this.getActions();

        while (i0 != 0)
        {
            i1 = 1 << Integer.numberOfTrailingZeros(i0);
            i0 = i0 & (i1 ^ -1);
            r1.append(AccessibilityNodeInfoCompat.getActionSymbolicName(i1));

            if (i0 != 0)
            {
                r1.append(", ");
            }
        }

        r1.append("]");
        return r1.toString();
    }

    static AccessibilityNodeInfoCompat wrapNonNullInstance(Object  r0)
    {

        AccessibilityNodeInfoCompat r1;
        if (r0 == null)
        {
            r1 = null;
        }
        else
        {
            r1 = new AccessibilityNodeInfoCompat(r0);
        }

        return r1;
    }
}
