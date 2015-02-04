package android.support.v4.content;

import android.os.Build$VERSION;
import android.content.ComponentName;
import android.content.Intent;

public class IntentCompat
{
    public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
    public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
    public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
    public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;
    public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;
    private static final IntentCompat$IntentCompatImpl IMPL;

    static
    {

        int i0;
        i0 = Build$VERSION.SDK_INT;

        if (i0 < 15)
        {
            if (i0 < 11)
            {
                IMPL = new IntentCompat$IntentCompatImplBase();
            }
            else
            {
                IMPL = new IntentCompat$IntentCompatImplHC();
            }
        }
        else
        {
            IMPL = new IntentCompat$IntentCompatImplIcsMr1();
        }
    }

    private IntentCompat()
    {


        this.<init>();
    }

    public static Intent makeMainActivity(ComponentName  r0)
    {


        return IMPL.makeMainActivity(r0);
    }

    public static Intent makeMainSelectorActivity(String  r0, String  r1)
    {


        return IMPL.makeMainSelectorActivity(r0, r1);
    }

    public static Intent makeRestartActivityTask(ComponentName  r0)
    {


        return IMPL.makeRestartActivityTask(r0);
    }
}
