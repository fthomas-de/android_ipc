package android.support.v4.text;


class TextDirectionHeuristicsCompat$AnyStrong implements android.support.v4.text.TextDirectionHeuristicsCompat$TextDirectionAlgorithm
{
    public static final TextDirectionHeuristicsCompat$AnyStrong INSTANCE_LTR;
    public static final TextDirectionHeuristicsCompat$AnyStrong INSTANCE_RTL;
    private final boolean mLookForRtl;

    static
    {


        INSTANCE_RTL = new TextDirectionHeuristicsCompat$AnyStrong(true);
        INSTANCE_LTR = new TextDirectionHeuristicsCompat$AnyStrong(false);
    }

    private TextDirectionHeuristicsCompat$AnyStrong(boolean  z0)
    {


        this.<init>();
        mLookForRtl = z0;
    }

    public int checkRtl(CharSequence  r1, int  i0, int  i1)
    {

        byte b2;
        boolean z0;
        int i4, i5;
        b2 = (byte) (byte) 1;
        z0 = false;
        i4 = i0;
        i5 = i0 + i1;

        label_1:
        {
            while (i4 < i5)
            {
                label_0:
                switch (TextDirectionHeuristicsCompat.access$200(Character.getDirectionality(r1.charAt(i4))))
                {
                    case 0:
                        if (mLookForRtl == false)
                        {
                            z0 = true;
                            break label_0;
                        }
                        else
                        {
                            b2 = (byte) (byte) 0;
                            break label_1;
                        }

                    case 1:
                        if (mLookForRtl == false)
                        {
                            break label_1;
                        }
                        else
                        {
                            z0 = true;
                            break label_0;
                        }

                    default:
                        break label_0;
                }

                i4 = i4 + 1;
            }

            if (z0 == false)
            {
                b2 = (byte) (byte) 2;
            }
            else
            {
                if (mLookForRtl == false)
                {
                    b2 = (byte) (byte) 0;
                }
            }
        } //end label_1:


        return b2;
    }
}
