package android.support.v4.widget;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.view.View;
import android.graphics.Rect;
import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.ViewCompat;
import java.util.LinkedList;
import java.util.Iterator;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityManager;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.ViewParent;
import android.support.v4.view.ViewParentCompat;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat
{
    private static final String DEFAULT_CLASS_NAME;
    public static final int INVALID_ID = -2147483648;
    private int mFocusedVirtualViewId;
    private int mHoveredVirtualViewId;
    private final AccessibilityManager mManager;
    private ExploreByTouchHelper$ExploreByTouchNodeProvider mNodeProvider;
    private final int[] mTempGlobalRect;
    private final Rect mTempParentRect;
    private final Rect mTempScreenRect;
    private final Rect mTempVisibleRect;
    private final View mView;

    static
    {


        DEFAULT_CLASS_NAME = class "android/view/View".getName();
    }

    public ExploreByTouchHelper(View  r1)
    {


        this.<init>();
        mTempScreenRect = new Rect();
        mTempParentRect = new Rect();
        mTempVisibleRect = new Rect();
        mTempGlobalRect = new int[2];
        mFocusedVirtualViewId = -2147483648;
        mHoveredVirtualViewId = -2147483648;

        if (r1 != null)
        {
            mView = r1;
            mManager = (AccessibilityManager) r1.getContext().getSystemService("accessibility");
            return;
        }
        else
        {
            throw new IllegalArgumentException("View may not be null");
        }
    }

    static AccessibilityNodeInfoCompat access$100(ExploreByTouchHelper  r0, int  i0)
    {


        return r0.createNode(i0);
    }

    static boolean access$200(ExploreByTouchHelper  r0, int  i0, int  i1, Bundle  r1)
    {


        return r0.performAction(i0, i1, r1);
    }

    private boolean clearAccessibilityFocus(int  i0)
    {

        boolean z2;
        if (this.isAccessibilityFocused(i0) == false)
        {
            z2 = false;
        }
        else
        {
            mFocusedVirtualViewId = -2147483648;
            mView.invalidate();
            this.sendEventForVirtualView(i0, 65536);
            z2 = true;
        }

        return z2;
    }

    private AccessibilityEvent createEvent(int  i0, int  i1)
    {

        AccessibilityEvent r1;
        label_0:
        switch (i0)
        {
            case -1:
                r1 = this.createEventForHost(i1);
                break label_0;

            default:
                r1 = this.createEventForChild(i0, i1);
                break label_0;
        }

        return r1;
    }

    private AccessibilityEvent createEventForChild(int  i0, int  i1)
    {

        AccessibilityEvent r1;
        r1 = AccessibilityEvent.obtain(i1);
        r1.setEnabled(true);
        r1.setClassName(DEFAULT_CLASS_NAME);
        this.onPopulateEventForVirtualView(i0, r1);

        if (r1.getText().isEmpty() != false)
        {
            if (r1.getContentDescription() == null)
            {
                throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
            }
        }

        r1.setPackageName(mView.getContext().getPackageName());
        AccessibilityEventCompat.asRecord(r1).setSource(mView, i0);
        return r1;
    }

    private AccessibilityEvent createEventForHost(int  i0)
    {

        AccessibilityEvent r1;
        r1 = AccessibilityEvent.obtain(i0);
        ViewCompat.onInitializeAccessibilityEvent(mView, r1);
        return r1;
    }

    private AccessibilityNodeInfoCompat createNode(int  i0)
    {

        AccessibilityNodeInfoCompat r1;
        label_1:
        switch (i0)
        {
            case -1:
                r1 = this.createNodeForHost();
                break label_1;

            default:
                r1 = this.createNodeForChild(i0);
                break label_1;
        }

        return r1;
    }

    private AccessibilityNodeInfoCompat createNodeForChild(int  i0)
    {

        AccessibilityNodeInfoCompat r1;
        int i1, i2, i3;
        r1 = AccessibilityNodeInfoCompat.obtain();
        r1.setEnabled(true);
        r1.setClassName(DEFAULT_CLASS_NAME);
        this.onPopulateNodeForVirtualView(i0, r1);

        if (r1.getText() == null)
        {
            if (r1.getContentDescription() == null)
            {
                throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
            }
        }

        r1.getBoundsInParent(mTempParentRect);

        if (mTempParentRect.isEmpty() == false)
        {
            i1 = r1.getActions();

            if ((i1 & 64) == 0)
            {
                if (i1 + 128 == 0)
                {
                    r1.setPackageName(mView.getContext().getPackageName());
                    r1.setSource(mView, i0);
                    r1.setParent(mView);

                    if (mFocusedVirtualViewId != i0)
                    {
                        r1.setAccessibilityFocused(false);
                        r1.addAction(64);
                    }
                    else
                    {
                        r1.setAccessibilityFocused(true);
                        r1.addAction(128);
                    }

                    if (this.intersectVisibleToUser(mTempParentRect) != false)
                    {
                        r1.setVisibleToUser(true);
                        r1.setBoundsInParent(mTempParentRect);
                    }

                    mView.getLocationOnScreen(mTempGlobalRect);
                    i2 = mTempGlobalRect[(int) 0];
                    i3 = mTempGlobalRect[(int) 1];
                    mTempScreenRect.set(mTempParentRect);
                    mTempScreenRect.offset(i2, i3);
                    r1.setBoundsInScreen(mTempScreenRect);
                    return r1;
                }
                else
                {
                    throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
                }
            }
            else
            {
                throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            }
        }
        else
        {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
    }

    private AccessibilityNodeInfoCompat createNodeForHost()
    {

        AccessibilityNodeInfoCompat r2;
        LinkedList r3;
        Iterator r4;
        Integer r7;
        r2 = AccessibilityNodeInfoCompat.obtain(mView);
        ViewCompat.onInitializeAccessibilityNodeInfo(mView, r2);
        r3 = new LinkedList();
        this.getVisibleVirtualViews(r3);
        r4 = r3.iterator();

        while (r4.hasNext() != false)
        {
            r7 = (Integer) r4.next();
            r2.addChild(mView, r7.intValue());
        }

        return r2;
    }

    public boolean dispatchHoverEvent(MotionEvent  r1)
    {

        boolean z0, z1;
        int i1;
        z0 = true;
        z1 = false;

        label_2:
        if (mManager.isEnabled() != false)
        {
            if (AccessibilityManagerCompat.isTouchExplorationEnabled(mManager) != false)
            {
                switch (r1.getAction())
                {
                    case 7:
                    case 9:
                        i1 = this.getVirtualViewAt(r1.getX(), r1.getY());
                        this.updateHoveredVirtualView(i1);

                        if (i1 == -2147483648)
                        {
                            z0 = false;
                        }

                        z1 = z0;
                        break label_2;

                    case 10:
                        if (mFocusedVirtualViewId == -2147483648)
                        {
                            break label_2;
                        }
                        else
                        {
                            this.updateHoveredVirtualView(-2147483648);
                            z1 = true;
                            break label_2;
                        }

                    case 8:
                    default:
                        break label_2;
                }
            }
        }

        return z1;
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View  r1)
    {


        if (mNodeProvider == null)
        {
            mNodeProvider = new ExploreByTouchHelper$ExploreByTouchNodeProvider(this, null);
        }

        return mNodeProvider;
    }

    public int getFocusedVirtualView()
    {


        return mFocusedVirtualViewId;
    }

    protected abstract int getVirtualViewAt(float  f0, float  f1);

    protected abstract void getVisibleVirtualViews(java.util.List  r0);

    private boolean intersectVisibleToUser(Rect  r1)
    {

        boolean z0;
        ViewParent r2;
        View r6;
        z0 = false;

        label_3:
        if (r1 != null)
        {
            if (r1.isEmpty() == false)
            {
                if (mView.getWindowVisibility() == 0)
                {
                    r2 = mView.getParent();

                    while (r2 instanceof View != false)
                    {
                        r6 = (View) r2;

                        if (ViewCompat.getAlpha(r6) - 0.0F <= 0)
                        {
                            break label_3;
                        }
                        else
                        {
                            if (r6.getVisibility() != 0)
                            {
                                break label_3;
                            }
                            else
                            {
                                r2 = r6.getParent();
                            }
                        }
                    }

                    if (r2 != null)
                    {
                        if (mView.getLocalVisibleRect(mTempVisibleRect) != false)
                        {
                            z0 = r1.intersect(mTempVisibleRect);
                        }
                    }
                }
            }
        }

        return z0;
    }

    public void invalidateRoot()
    {


        this.invalidateVirtualView(-1);
    }

    public void invalidateVirtualView(int  i0)
    {


        this.sendEventForVirtualView(i0, 2048);
    }

    private boolean isAccessibilityFocused(int  i0)
    {

        boolean z0;
        if (mFocusedVirtualViewId != i0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    private boolean manageFocusForChild(int  i0, int  i1, Bundle  r1)
    {

        boolean z0;
        label_4:
        switch (i1)
        {
            case 64:
                z0 = this.requestAccessibilityFocus(i0);
                break label_4;

            case 128:
                z0 = this.clearAccessibilityFocus(i0);
                break label_4;

            default:
                z0 = false;
                break label_4;
        }

        return z0;
    }

    protected abstract boolean onPerformActionForVirtualView(int  i0, int  i1, android.os.Bundle  r2);

    protected abstract void onPopulateEventForVirtualView(int  i0, android.view.accessibility.AccessibilityEvent  r1);

    protected abstract void onPopulateNodeForVirtualView(int  i0, android.support.v4.view.accessibility.AccessibilityNodeInfoCompat  r1);

    private boolean performAction(int  i0, int  i1, Bundle  r1)
    {

        boolean z0;
        label_5:
        switch (i0)
        {
            case -1:
                z0 = this.performActionForHost(i1, r1);
                break label_5;

            default:
                z0 = this.performActionForChild(i0, i1, r1);
                break label_5;
        }

        return z0;
    }

    private boolean performActionForChild(int  i0, int  i1, Bundle  r1)
    {

        boolean z0;
        label_6:
        switch (i1)
        {
            case 64:
            case 128:
                z0 = this.manageFocusForChild(i0, i1, r1);
                break label_6;

            default:
                z0 = this.onPerformActionForVirtualView(i0, i1, r1);
                break label_6;
        }

        return z0;
    }

    private boolean performActionForHost(int  i0, Bundle  r1)
    {


        return ViewCompat.performAccessibilityAction(mView, i0, r1);
    }

    private boolean requestAccessibilityFocus(int  i0)
    {

        boolean z0;
        z0 = false;

        if (mManager.isEnabled() != false)
        {
            if (AccessibilityManagerCompat.isTouchExplorationEnabled(mManager) != false)
            {
                if (this.isAccessibilityFocused(i0) == false)
                {
                    mFocusedVirtualViewId = i0;
                    mView.invalidate();
                    this.sendEventForVirtualView(i0, 32768);
                    z0 = true;
                }
            }
        }

        return z0;
    }

    public boolean sendEventForVirtualView(int  i0, int  i1)
    {

        boolean z0;
        ViewParent r1;
        AccessibilityEvent r2;
        z0 = false;

        if (i0 != -2147483648)
        {
            if (mManager.isEnabled() != false)
            {
                r1 = mView.getParent();

                if (r1 != null)
                {
                    r2 = this.createEvent(i0, i1);
                    z0 = ViewParentCompat.requestSendAccessibilityEvent(r1, mView, r2);
                }
            }
        }

        return z0;
    }

    private void updateHoveredVirtualView(int  i0)
    {

        int i2;
        if (mHoveredVirtualViewId != i0)
        {
            i2 = mHoveredVirtualViewId;
            mHoveredVirtualViewId = i0;
            this.sendEventForVirtualView(i0, 128);
            this.sendEventForVirtualView(i2, 256);
        }
    }
}
