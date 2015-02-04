package android.support.v4.view.accessibility;

import android.os.Build$VERSION;
import java.util.List;
import android.os.Bundle;

public class AccessibilityNodeProviderCompat
{
    private static final AccessibilityNodeProviderCompat$AccessibilityNodeProviderImpl IMPL;
    private final Object mProvider;

    static
    {


        if (Build$VERSION.SDK_INT < 19)
        {
            if (Build$VERSION.SDK_INT < 16)
            {
                IMPL = new AccessibilityNodeProviderCompat$AccessibilityNodeProviderStubImpl();
            }
            else
            {
                IMPL = new AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl();
            }
        }
        else
        {
            IMPL = new AccessibilityNodeProviderCompat$AccessibilityNodeProviderKitKatImpl();
        }
    }

    public AccessibilityNodeProviderCompat()
    {


        this.<init>();
        mProvider = IMPL.newAccessibilityNodeProviderBridge(this);
    }

    public AccessibilityNodeProviderCompat(Object  r1)
    {


        this.<init>();
        mProvider = r1;
    }

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int  i0)
    {


        return null;
    }

    public List findAccessibilityNodeInfosByText(String  r1, int  i0)
    {


        return null;
    }

    public AccessibilityNodeInfoCompat findFocus(int  i0)
    {


        return null;
    }

    public Object getProvider()
    {


        return mProvider;
    }

    public boolean performAction(int  i0, int  i1, Bundle  r1)
    {


        return false;
    }
}
