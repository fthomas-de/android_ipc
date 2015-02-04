package android.support.v4.text;

import java.util.Locale;

public class TextUtilsCompat
{
    private static String ARAB_SCRIPT_SUBTAG;
    private static String HEBR_SCRIPT_SUBTAG;
    public static final Locale ROOT;

    static
    {


        ROOT = new Locale("", "");
        ARAB_SCRIPT_SUBTAG = "Arab";
        HEBR_SCRIPT_SUBTAG = "Hebr";
    }

    public TextUtilsCompat()
    {


        this.<init>();
    }

    private static int getLayoutDirectionFromFirstChar(Locale  r0)
    {

        byte b0;
        b0 = (byte) (byte) 0;

        switch (Character.getDirectionality(r0.getDisplayName(r0).charAt(0)))
        {
            case 1:
            case 2:
                b0 = (byte) (byte) 1;

            default:
                return b0;
        }
    }

    public static int getLayoutDirectionFromLocale(Locale  r0)
    {

        String r2;
        int i0;
        label_1:
        {
            label_0:
            if (r0 != null)
            {
                if (r0.equals(ROOT) == false)
                {
                    r2 = ICUCompat.getScript(ICUCompat.addLikelySubtags(r0.toString()));

                    if (r2 != null)
                    {
                        if (r2.equalsIgnoreCase(ARAB_SCRIPT_SUBTAG) == false)
                        {
                            if (r2.equalsIgnoreCase(HEBR_SCRIPT_SUBTAG) == false)
                            {
                                break label_0;
                            }
                        }

                        i0 = 1;
                        break label_1;
                    }
                    else
                    {
                        i0 = TextUtilsCompat.getLayoutDirectionFromFirstChar(r0);
                        break label_1;
                    }
                }
            }

            i0 = 0;
        } //end label_1:


        return i0;
    }

    public static String htmlEncode(String  r0)
    {

        StringBuilder r1;
        int i0;
        char c2;
        r1 = new StringBuilder();
        i0 = 0;

        while (i0 < r0.length())
        {
            c2 = r0.charAt(i0);

            label_2:
            switch (c2)
            {
                case 34:
                    r1.append("&quot;");
                    break label_2;

                case 38:
                    r1.append("&amp;");
                    break label_2;

                case 39:
                    r1.append("&#39;");
                    break label_2;

                case 60:
                    r1.append("&lt;");
                    break label_2;

                case 62:
                    r1.append("&gt;");
                    break label_2;

                default:
                    r1.append(c2);
                    break label_2;
            }

            i0 = i0 + 1;
        }

        return r1.toString();
    }
}
