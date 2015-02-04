package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;
import android.graphics.drawable.Drawable;
import android.view.accessibility.AccessibilityEvent;
import android.os.Bundle;
import android.graphics.Paint;

class ViewCompat$BaseViewCompatImpl implements android.support.v4.view.ViewCompat$ViewCompatImpl
{

    ViewCompat$BaseViewCompatImpl()
    {


        this.<init>();
    }

    public boolean canScrollHorizontally(View  r1, int  i0)
    {


        return false;
    }

    public boolean canScrollVertically(View  r1, int  i0)
    {


        return false;
    }

    public int getAccessibilityLiveRegion(View  r1)
    {


        return 0;
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View  r1)
    {


        return null;
    }

    public float getAlpha(View  r1)
    {


        return 1.0F;
    }

    long getFrameTime()
    {


        return 10L;
    }

    public int getImportantForAccessibility(View  r1)
    {


        return 0;
    }

    public int getLabelFor(View  r1)
    {


        return 0;
    }

    public int getLayerType(View  r1)
    {


        return 0;
    }

    public int getLayoutDirection(View  r1)
    {


        return 0;
    }

    public int getMeasuredHeightAndState(View  r1)
    {


        return r1.getMeasuredHeight();
    }

    public int getMeasuredState(View  r1)
    {


        return 0;
    }

    public int getMeasuredWidthAndState(View  r1)
    {


        return r1.getMeasuredWidth();
    }

    public int getOverScrollMode(View  r1)
    {


        return 2;
    }

    public ViewParent getParentForAccessibility(View  r1)
    {


        return r1.getParent();
    }

    public boolean hasTransientState(View  r1)
    {


        return false;
    }

    public boolean isOpaque(View  r1)
    {

        boolean z0;
        Drawable r2;
        z0 = false;
        r2 = r1.getBackground();

        if (r2 != null)
        {
            if (r2.getOpacity() == -1)
            {
                z0 = true;
            }
        }

        return z0;
    }

    public void onInitializeAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {

    }

    public void onInitializeAccessibilityNodeInfo(View  r1, AccessibilityNodeInfoCompat  r2)
    {

    }

    public void onPopulateAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {

    }

    public boolean performAccessibilityAction(View  r1, int  i0, Bundle  r2)
    {


        return false;
    }

    public void postInvalidateOnAnimation(View  r1)
    {


        r1.postInvalidateDelayed(this.getFrameTime());
    }

    public void postInvalidateOnAnimation(View  r1, int  i0, int  i1, int  i2, int  i3)
    {


        r1.postInvalidateDelayed(this.getFrameTime(), i0, i1, i2, i3);
    }

    public void postOnAnimation(View  r1, Runnable  r2)
    {


        r1.postDelayed(r2, this.getFrameTime());
    }

    public void postOnAnimationDelayed(View  r1, Runnable  r2, long  l0)
    {


        r1.postDelayed(r2, this.getFrameTime() + l0);
    }

    public int resolveSizeAndState(int  i0, int  i1, int  i2)
    {


        return View.resolveSize(i0, i1);
    }

    public void setAccessibilityDelegate(View  r1, AccessibilityDelegateCompat  r2)
    {

    }

    public void setAccessibilityLiveRegion(View  r1, int  i0)
    {

    }

    public void setHasTransientState(View  r1, boolean  z0)
    {

    }

    public void setImportantForAccessibility(View  r1, int  i0)
    {

    }

    public void setLabelFor(View  r1, int  i0)
    {

    }

    public void setLayerPaint(View  r1, Paint  r2)
    {

    }

    public void setLayerType(View  r1, int  i0, Paint  r2)
    {

    }

    public void setLayoutDirection(View  r1, int  i0)
    {

    }

    public void setOverScrollMode(View  r1, int  i0)
    {

    }
}
