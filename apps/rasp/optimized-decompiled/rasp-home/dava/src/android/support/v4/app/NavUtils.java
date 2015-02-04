package android.support.v4.app;

import android.os.Build$VERSION;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.ComponentName;
import android.support.v4.content.IntentCompat;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;

public class NavUtils
{
    private static final NavUtils$NavUtilsImpl IMPL;
    public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
    private static final String TAG = "NavUtils";

    static
    {


        if (Build$VERSION.SDK_INT < 16)
        {
            IMPL = new NavUtils$NavUtilsImplBase();
        }
        else
        {
            IMPL = new NavUtils$NavUtilsImplJB();
        }
    }

    private NavUtils()
    {


        this.<init>();
    }

    public static Intent getParentActivityIntent(Activity  r0)
    {


        return IMPL.getParentActivityIntent(r0);
    }

    public static Intent getParentActivityIntent(Context  r0, ComponentName  r1) throws android.content.pm.PackageManager$NameNotFoundException
    {

        String r2;
        Intent r3;
        ComponentName r4;
        r2 = NavUtils.getParentActivityName(r0, r1);

        if (r2 != null)
        {
            r4 = new ComponentName(r1.getPackageName(), r2);

            if (NavUtils.getParentActivityName(r0, r4) != null)
            {
                r3 = (new Intent()).setComponent(r4);
            }
            else
            {
                r3 = IntentCompat.makeMainActivity(r4);
            }
        }
        else
        {
            r3 = null;
        }

        return r3;
    }

    public static Intent getParentActivityIntent(Context  r0, Class  r1) throws android.content.pm.PackageManager$NameNotFoundException
    {

        String r3;
        Intent r4;
        ComponentName r5;
        r3 = NavUtils.getParentActivityName(r0, new ComponentName(r0, r1));

        if (r3 != null)
        {
            r5 = new ComponentName(r0, r3);

            if (NavUtils.getParentActivityName(r0, r5) != null)
            {
                r4 = (new Intent()).setComponent(r5);
            }
            else
            {
                r4 = IntentCompat.makeMainActivity(r5);
            }
        }
        else
        {
            r4 = null;
        }

        return r4;
    }

    public static String getParentActivityName(Activity  r0)
    {

        ComponentName $r3;
        String $r4;
        label_1:
        {
            label_0:
            {
                $r3 = r0.getComponentName();

                try
                {
                    $r4 = NavUtils.getParentActivityName(r0, $r3);
                    break label_1;
                }
                catch (PackageManager$NameNotFoundException $r6)
                {
                }
            } //end label_0:


            throw new IllegalArgumentException($r6);
        } //end label_1:


        return $r4;
    }

    public static String getParentActivityName(Context  r0, ComponentName  r1) throws android.content.pm.PackageManager$NameNotFoundException
    {

        ActivityInfo r3;
        r3 = r0.getPackageManager().getActivityInfo(r1, 128);
        return IMPL.getParentActivityName(r0, r3);
    }

    public static void navigateUpFromSameTask(Activity  r0)
    {

        Intent r1;
        r1 = NavUtils.getParentActivityIntent(r0);

        if (r1 != null)
        {
            NavUtils.navigateUpTo(r0, r1);
            return;
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Activity ").append(r0.getClass().getSimpleName()).append(" does not have a parent activity name specified.").append(" (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> ").append(" element in your manifest?)").toString());
        }
    }

    public static void navigateUpTo(Activity  r0, Intent  r1)
    {


        IMPL.navigateUpTo(r0, r1);
    }

    public static boolean shouldUpRecreateTask(Activity  r0, Intent  r1)
    {


        return IMPL.shouldUpRecreateTask(r0, r1);
    }
}
