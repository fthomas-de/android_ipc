package android.support.v4.text;


class TextDirectionHeuristicsCompat$FirstStrong implements android.support.v4.text.TextDirectionHeuristicsCompat$TextDirectionAlgorithm
{
    public static final TextDirectionHeuristicsCompat$FirstStrong INSTANCE;

    static
    {


        INSTANCE = new TextDirectionHeuristicsCompat$FirstStrong();
    }

    private TextDirectionHeuristicsCompat$FirstStrong()
    {


        this.<init>();
    }

    public int checkRtl(CharSequence  r1, int  i0, int  i1)
    {

        int i2, i3, i4;
        i2 = 2;
        i3 = i0;
        i4 = i0 + i1;

        label_0:
        {
            while (i3 < i4)
            {
                if (i2 != 2)
                {
                    break label_0;
                }
                else
                {
                    i2 = TextDirectionHeuristicsCompat.access$100(Character.getDirectionality(r1.charAt(i3)));
                    i3 = i3 + 1;
                }
            }
        } //end label_0:


        return i2;
    }
}
