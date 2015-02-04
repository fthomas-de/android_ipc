package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ResolveInfo;

class AccessibilityServiceInfoCompat$AccessibilityServiceInfoIcsImpl extends AccessibilityServiceInfoCompat$AccessibilityServiceInfoStubImpl
{

    AccessibilityServiceInfoCompat$AccessibilityServiceInfoIcsImpl()
    {


        this.<init>();
    }

    public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo  r1)
    {


        return AccessibilityServiceInfoCompatIcs.getCanRetrieveWindowContent(r1);
    }

    public int getCapabilities(AccessibilityServiceInfo  r1)
    {

        byte b0;
        if (this.getCanRetrieveWindowContent(r1) == false)
        {
            b0 = (byte) (byte) 0;
        }
        else
        {
            b0 = (byte) (byte) 1;
        }

        return b0;
    }

    public String getDescription(AccessibilityServiceInfo  r1)
    {


        return AccessibilityServiceInfoCompatIcs.getDescription(r1);
    }

    public String getId(AccessibilityServiceInfo  r1)
    {


        return AccessibilityServiceInfoCompatIcs.getId(r1);
    }

    public ResolveInfo getResolveInfo(AccessibilityServiceInfo  r1)
    {


        return AccessibilityServiceInfoCompatIcs.getResolveInfo(r1);
    }

    public String getSettingsActivityName(AccessibilityServiceInfo  r1)
    {


        return AccessibilityServiceInfoCompatIcs.getSettingsActivityName(r1);
    }
}
