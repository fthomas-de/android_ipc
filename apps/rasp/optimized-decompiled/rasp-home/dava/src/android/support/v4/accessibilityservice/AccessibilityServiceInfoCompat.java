package android.support.v4.accessibilityservice;

import android.os.Build$VERSION;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ResolveInfo;

public class AccessibilityServiceInfoCompat
{
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    private static final AccessibilityServiceInfoCompat$AccessibilityServiceInfoVersionImpl IMPL;

    static
    {


        if (Build$VERSION.SDK_INT < 18)
        {
            if (Build$VERSION.SDK_INT < 14)
            {
                IMPL = new AccessibilityServiceInfoCompat$AccessibilityServiceInfoStubImpl();
            }
            else
            {
                IMPL = new AccessibilityServiceInfoCompat$AccessibilityServiceInfoIcsImpl();
            }
        }
        else
        {
            IMPL = new AccessibilityServiceInfoCompat$AccessibilityServiceInfoJellyBeanMr2();
        }
    }

    private AccessibilityServiceInfoCompat()
    {


        this.<init>();
    }

    public static String capabilityToString(int  i0)
    {

        String r0;
        label_0:
        switch (i0)
        {
            case 1:
                r0 = "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
                break label_0;

            case 2:
                r0 = "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
                break label_0;

            case 4:
                r0 = "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
                break label_0;

            case 8:
                r0 = "CAPABILITY_CAN_FILTER_KEY_EVENTS";
                break label_0;

            case 3:
            case 5:
            case 6:
            case 7:
            default:
                r0 = "UNKNOWN";
                break label_0;
        }

        return r0;
    }

    public static String feedbackTypeToString(int  i0)
    {

        StringBuilder r0;
        int i2;
        r0 = new StringBuilder();
        r0.append("[");

        label_1:
        while (i0 > 0)
        {
            i2 = 1 << Integer.numberOfTrailingZeros(i0);
            i0 = i0 & (i2 ^ -1);

            if (r0.length() > 1)
            {
                r0.append(", ");
            }

            switch (i2)
            {
                case 1:
                    r0.append("FEEDBACK_SPOKEN");
                    continue label_1;

                case 2:
                    r0.append("FEEDBACK_HAPTIC");
                    continue label_1;

                case 4:
                    r0.append("FEEDBACK_AUDIBLE");
                    continue label_1;

                case 8:
                    r0.append("FEEDBACK_VISUAL");
                    continue label_1;

                case 16:
                    r0.append("FEEDBACK_GENERIC");
                    continue label_1;

                default:
                    continue label_1;
            }
        }

        r0.append("]");
        return r0.toString();
    }

    public static String flagToString(int  i0)
    {

        String r0;
        label_2:
        switch (i0)
        {
            case 1:
                r0 = "DEFAULT";
                break label_2;

            case 2:
                r0 = "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
                break label_2;

            case 4:
                r0 = "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
                break label_2;

            case 8:
                r0 = "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
                break label_2;

            case 16:
                r0 = "FLAG_REPORT_VIEW_IDS";
                break label_2;

            case 32:
                r0 = "FLAG_REQUEST_FILTER_KEY_EVENTS";
                break label_2;

            default:
                r0 = null;
                break label_2;
        }

        return r0;
    }

    public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo  r0)
    {


        return IMPL.getCanRetrieveWindowContent(r0);
    }

    public static int getCapabilities(AccessibilityServiceInfo  r0)
    {


        return IMPL.getCapabilities(r0);
    }

    public static String getDescription(AccessibilityServiceInfo  r0)
    {


        return IMPL.getDescription(r0);
    }

    public static String getId(AccessibilityServiceInfo  r0)
    {


        return IMPL.getId(r0);
    }

    public static ResolveInfo getResolveInfo(AccessibilityServiceInfo  r0)
    {


        return IMPL.getResolveInfo(r0);
    }

    public static String getSettingsActivityName(AccessibilityServiceInfo  r0)
    {


        return IMPL.getSettingsActivityName(r0);
    }
}
