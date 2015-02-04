package android.support.v4.util;


public class DebugUtils
{

    public DebugUtils()
    {


        this.<init>();
    }

    public static void buildShortClassTag(Object  r0, StringBuilder  r1)
    {

        String r3;
        int i0;
        if (r0 != null)
        {
            r3 = r0.getClass().getSimpleName();

            label_0:
            {
                if (r3 != null)
                {
                    if (r3.length() > 0)
                    {
                        break label_0;
                    }
                }

                r3 = r0.getClass().getName();
                i0 = r3.lastIndexOf(46);

                if (i0 > 0)
                {
                    r3 = r3.substring(i0 + 1);
                }
            } //end label_0:


            r1.append(r3);
            r1.append('{');
            r1.append(Integer.toHexString(System.identityHashCode(r0)));
        }
        else
        {
            r1.append("null");
        }
    }
}
