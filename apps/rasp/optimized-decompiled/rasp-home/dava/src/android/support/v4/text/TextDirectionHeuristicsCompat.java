package android.support.v4.text;


public class TextDirectionHeuristicsCompat
{
    public static final TextDirectionHeuristicCompat ANYRTL_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristicCompat LOCALE;
    public static final TextDirectionHeuristicCompat LTR;
    public static final TextDirectionHeuristicCompat RTL;
    private static final int STATE_FALSE = 1;
    private static final int STATE_TRUE = 0;
    private static final int STATE_UNKNOWN = 2;

    static
    {

        Object n0;
        n0 = null;
        LTR = new TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(n0, false, n0);
        RTL = new TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(n0, true, n0);
        FIRSTSTRONG_LTR = new TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat$FirstStrong.INSTANCE, false, n0);
        FIRSTSTRONG_RTL = new TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat$FirstStrong.INSTANCE, true, n0);
        ANYRTL_LTR = new TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat$AnyStrong.INSTANCE_RTL, false, n0);
        LOCALE = TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale.INSTANCE;
    }

    public TextDirectionHeuristicsCompat()
    {


        this.<init>();
    }

    static int access$100(int  i0)
    {


        return TextDirectionHeuristicsCompat.isRtlTextOrFormat(i0);
    }

    static int access$200(int  i0)
    {


        return TextDirectionHeuristicsCompat.isRtlText(i0);
    }

    private static int isRtlText(int  i0)
    {

        byte b1;
        label_0:
        switch (i0)
        {
            case 0:
                b1 = (byte) (byte) 1;
                break label_0;

            case 1:
            case 2:
                b1 = (byte) (byte) 0;
                break label_0;

            default:
                b1 = (byte) (byte) 2;
                break label_0;
        }

        return b1;
    }

    private static int isRtlTextOrFormat(int  i0)
    {

        byte b1;
        label_1:
        switch (i0)
        {
            case 0:
            case 14:
            case 15:
                b1 = (byte) (byte) 1;
                break label_1;

            case 1:
            case 2:
            case 16:
            case 17:
                b1 = (byte) (byte) 0;
                break label_1;

            default:
                b1 = (byte) (byte) 2;
                break label_1;
        }

        return b1;
    }
}
