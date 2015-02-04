package android.support.v4.text;

import java.util.Locale;

public final class BidiFormatter
{
    private static final int DEFAULT_FLAGS = 2;
    private static final BidiFormatter DEFAULT_LTR_INSTANCE;
    private static final BidiFormatter DEFAULT_RTL_INSTANCE;
    private static TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC;
    private static final int DIR_LTR = -1;
    private static final int DIR_RTL = 1;
    private static final int DIR_UNKNOWN = 0;
    private static final String EMPTY_STRING = "";
    private static final int FLAG_STEREO_RESET = 2;
    private static final char LRE = '‪';
    private static final char LRM = '‎';
    private static final String LRM_STRING;
    private static final char PDF = '‬';
    private static final char RLE = '‫';
    private static final char RLM = '‏';
    private static final String RLM_STRING;
    private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    private final int mFlags;
    private final boolean mIsRtlContext;

    static
    {


        DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        LRM_STRING = Character.toString('\u200e');
        RLM_STRING = Character.toString('\u200f');
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
    }

    private BidiFormatter(boolean  z0, int  i0, TextDirectionHeuristicCompat  r1)
    {


        this.<init>();
        mIsRtlContext = z0;
        mFlags = i0;
        mDefaultTextDirectionHeuristicCompat = r1;
    }

    BidiFormatter(boolean  z0, int  i0, TextDirectionHeuristicCompat  r1, BidiFormatter$1  r2)
    {
        this(z0, i0, r1);


        this.<init>(z0, i0, r1);
    }

    static boolean access$000(Locale  r0)
    {


        return BidiFormatter.isRtlLocale(r0);
    }

    static TextDirectionHeuristicCompat access$100()
    {


        return DEFAULT_TEXT_DIRECTION_HEURISTIC;
    }

    static BidiFormatter access$200()
    {


        return DEFAULT_RTL_INSTANCE;
    }

    static BidiFormatter access$300()
    {


        return DEFAULT_LTR_INSTANCE;
    }

    private static int getEntryDir(String  r0)
    {


        return (new BidiFormatter$DirectionalityEstimator(r0, false)).getEntryDir();
    }

    private static int getExitDir(String  r0)
    {


        return (new BidiFormatter$DirectionalityEstimator(r0, false)).getExitDir();
    }

    public static BidiFormatter getInstance()
    {


        return (new BidiFormatter$Builder()).build();
    }

    public static BidiFormatter getInstance(Locale  r0)
    {


        return (new BidiFormatter$Builder(r0)).build();
    }

    public static BidiFormatter getInstance(boolean  z0)
    {


        return (new BidiFormatter$Builder(z0)).build();
    }

    public boolean getStereoReset()
    {

        boolean z0;
        if ((mFlags & 2) == 0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public boolean isRtl(String  r1)
    {


        return mDefaultTextDirectionHeuristicCompat.isRtl(r1, 0, r1.length());
    }

    public boolean isRtlContext()
    {


        return mIsRtlContext;
    }

    private static boolean isRtlLocale(Locale  r0)
    {

        boolean z0;
        z0 = true;

        if (TextUtilsCompat.getLayoutDirectionFromLocale(r0) != (int) 1)
        {
            z0 = false;
        }

        return z0;
    }

    private String markAfter(String  r1, TextDirectionHeuristicCompat  r2)
    {

        boolean z0;
        String r3;
        z0 = r2.isRtl(r1, 0, r1.length());

        label_2:
        {
            label_0:
            if (mIsRtlContext == false)
            {
                if (z0 == false)
                {
                    if (BidiFormatter.getExitDir(r1) != 1)
                    {
                        break label_0;
                    }
                }

                r3 = LRM_STRING;
                break label_2;
            }

            label_1:
            if (mIsRtlContext != false)
            {
                if (z0 != false)
                {
                    if (BidiFormatter.getExitDir(r1) != -1)
                    {
                        break label_1;
                    }
                }

                r3 = RLM_STRING;
                break label_2;
            }

            r3 = "";
        } //end label_2:


        return r3;
    }

    private String markBefore(String  r1, TextDirectionHeuristicCompat  r2)
    {

        boolean z0;
        String r3;
        z0 = r2.isRtl(r1, 0, r1.length());

        label_5:
        {
            label_3:
            if (mIsRtlContext == false)
            {
                if (z0 == false)
                {
                    if (BidiFormatter.getEntryDir(r1) != 1)
                    {
                        break label_3;
                    }
                }

                r3 = LRM_STRING;
                break label_5;
            }

            label_4:
            if (mIsRtlContext != false)
            {
                if (z0 != false)
                {
                    if (BidiFormatter.getEntryDir(r1) != -1)
                    {
                        break label_4;
                    }
                }

                r3 = RLM_STRING;
                break label_5;
            }

            r3 = "";
        } //end label_5:


        return r3;
    }

    public String unicodeWrap(String  r1)
    {


        return this.unicodeWrap(r1, mDefaultTextDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String  r1, TextDirectionHeuristicCompat  r2)
    {


        return this.unicodeWrap(r1, r2, true);
    }

    public String unicodeWrap(String  r1, TextDirectionHeuristicCompat  r2, boolean  z0)
    {

        boolean z1;
        StringBuilder r3;
        TextDirectionHeuristicCompat r4, r10;
        char c2;
        z1 = r2.isRtl(r1, 0, r1.length());
        r3 = new StringBuilder();

        if (this.getStereoReset() != false)
        {
            if (z0 != false)
            {
                if (z1 == false)
                {
                    r4 = TextDirectionHeuristicsCompat.LTR;
                }
                else
                {
                    r4 = TextDirectionHeuristicsCompat.RTL;
                }

                r3.append(this.markBefore(r1, r4));
            }
        }

        if (z1 == mIsRtlContext)
        {
            r3.append(r1);
        }
        else
        {
            if (z1 == false)
            {
                c2 = '\u202a';
            }
            else
            {
                c2 = '\u202b';
            }

            r3.append(c2);
            r3.append(r1);
            r3.append('\u202c');
        }

        if (z0 != false)
        {
            if (z1 == false)
            {
                r10 = TextDirectionHeuristicsCompat.LTR;
            }
            else
            {
                r10 = TextDirectionHeuristicsCompat.RTL;
            }

            r3.append(this.markAfter(r1, r10));
        }

        return r3.toString();
    }

    public String unicodeWrap(String  r1, boolean  z0)
    {


        return this.unicodeWrap(r1, mDefaultTextDirectionHeuristicCompat, z0);
    }
}
