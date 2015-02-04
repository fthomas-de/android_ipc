package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup;
import android.os.Bundle;

public class AccessibilityDelegateCompat
{
    private static final Object DEFAULT_DELEGATE;
    private static final AccessibilityDelegateCompat$AccessibilityDelegateImpl IMPL;
    final Object mBridge;

    static
    {


        if (Build$VERSION.SDK_INT < 16)
        {
            if (Build$VERSION.SDK_INT < 14)
            {
                IMPL = new AccessibilityDelegateCompat$AccessibilityDelegateStubImpl();
            }
            else
            {
                IMPL = new AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl();
            }
        }
        else
        {
            IMPL = new AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl();
        }

        DEFAULT_DELEGATE = IMPL.newAccessiblityDelegateDefaultImpl();
    }

    public AccessibilityDelegateCompat()
    {


        this.<init>();
        mBridge = IMPL.newAccessiblityDelegateBridge(this);
    }

    public boolean dispatchPopulateAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        return IMPL.dispatchPopulateAccessibilityEvent(DEFAULT_DELEGATE, r1, r2);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View  r1)
    {


        return IMPL.getAccessibilityNodeProvider(DEFAULT_DELEGATE, r1);
    }

    Object getBridge()
    {


        return mBridge;
    }

    public void onInitializeAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        IMPL.onInitializeAccessibilityEvent(DEFAULT_DELEGATE, r1, r2);
    }

    public void onInitializeAccessibilityNodeInfo(View  r1, AccessibilityNodeInfoCompat  r2)
    {


        IMPL.onInitializeAccessibilityNodeInfo(DEFAULT_DELEGATE, r1, r2);
    }

    public void onPopulateAccessibilityEvent(View  r1, AccessibilityEvent  r2)
    {


        IMPL.onPopulateAccessibilityEvent(DEFAULT_DELEGATE, r1, r2);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup  r1, View  r2, AccessibilityEvent  r3)
    {


        return IMPL.onRequestSendAccessibilityEvent(DEFAULT_DELEGATE, r1, r2, r3);
    }

    public boolean performAccessibilityAction(View  r1, int  i0, Bundle  r2)
    {


        return IMPL.performAccessibilityAction(DEFAULT_DELEGATE, r1, i0, r2);
    }

    public void sendAccessibilityEvent(View  r1, int  i0)
    {


        IMPL.sendAccessibilityEvent(DEFAULT_DELEGATE, r1, i0);
    }

    public void sendAccessibilityEventUnchecked(View  r1, AccessibilityEvent  r2)
    {


        IMPL.sendAccessibilityEventUnchecked(DEFAULT_DELEGATE, r1, r2);
    }
}
