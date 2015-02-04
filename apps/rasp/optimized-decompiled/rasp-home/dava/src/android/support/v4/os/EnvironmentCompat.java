package android.support.v4.os;

import java.io.File;
import java.io.IOException;
import android.os.Build$VERSION;
import android.os.Environment;
import android.util.Log;

public class EnvironmentCompat
{
    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";

    public EnvironmentCompat()
    {


        this.<init>();
    }

    public static String getStorageState(File  r0)
    {

        String r6, $r7, $r10, $r11;
        File $r8;
        boolean $z0;
        label_2:
        if (Build$VERSION.SDK_INT < 19)
        {
            label_1:
            {
                label_0:
                {
                    try
                    {
                        $r7 = r0.getCanonicalPath();
                    }
                    catch (IOException $r12)
                    {
                        break label_0;
                    }

                    $r8 = Environment.getExternalStorageDirectory();

                    try
                    {
                        $r10 = $r8.getCanonicalPath();
                    }
                    catch (IOException $r12)
                    {
                        break label_0;
                    }

                    $z0 = $r7.startsWith($r10);

                    if ($z0 == false)
                    {
                        break label_1;
                    }
                    else
                    {
                        $r11 = Environment.getExternalStorageState();

                        r6 = $r11;
                        break label_2;
                    }
                } //end label_0:


                Log.w("EnvironmentCompat", (new StringBuilder()).append("Failed to resolve canonical path: ").append($r12).toString());
            } //end label_1:


            r6 = "unknown";
        }
        else
        {
            r6 = EnvironmentCompatKitKat.getStorageState(r0);
        }

        return r6;
    }
}
