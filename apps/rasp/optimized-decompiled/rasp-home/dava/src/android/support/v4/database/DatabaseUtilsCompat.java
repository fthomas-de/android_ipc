package android.support.v4.database;

import android.text.TextUtils;

public class DatabaseUtilsCompat
{

    private DatabaseUtilsCompat()
    {


        this.<init>();
    }

    public static String[] appendSelectionArgs(String[]  r0, String[]  r1)
    {

        String[] r2;
        label_0:
        {
            if (r0 != null)
            {
                if (r0.length != 0)
                {
                    r2 = new String[r0.length + r1.length];
                    System.arraycopy(r0, 0, r2, 0, r0.length);
                    System.arraycopy(r1, 0, r2, r0.length, r1.length);
                    break label_0;
                }
            }

            r2 = r1;
        } //end label_0:


        return r2;
    }

    public static String concatenateWhere(String  r0, String  r1)
    {


        if (TextUtils.isEmpty(r0) == false)
        {
            if (TextUtils.isEmpty(r1) == false)
            {
                r1 = (new StringBuilder()).append("(").append(r0).append(") AND (").append(r1).append(")").toString();
            }
            else
            {
                r1 = r0;
            }
        }

        return r1;
    }
}
