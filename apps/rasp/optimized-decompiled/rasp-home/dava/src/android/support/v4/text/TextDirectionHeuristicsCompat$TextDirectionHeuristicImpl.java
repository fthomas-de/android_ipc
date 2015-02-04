package android.support.v4.text;

import java.nio.CharBuffer;

abstract class TextDirectionHeuristicsCompat$TextDirectionHeuristicImpl implements android.support.v4.text.TextDirectionHeuristicCompat
{
    private final TextDirectionHeuristicsCompat$TextDirectionAlgorithm mAlgorithm;

    public TextDirectionHeuristicsCompat$TextDirectionHeuristicImpl(TextDirectionHeuristicsCompat$TextDirectionAlgorithm  r1)
    {


        this.<init>();
        mAlgorithm = r1;
    }

    protected abstract boolean defaultIsRtl();

    private boolean doCheck(CharSequence  r1, int  i0, int  i1)
    {

        boolean z0;
        label_0:
        switch (mAlgorithm.checkRtl(r1, i0, i1))
        {
            case 0:
                z0 = true;
                break label_0;

            case 1:
                z0 = false;
                break label_0;

            default:
                z0 = this.defaultIsRtl();
                break label_0;
        }

        return z0;
    }

    public boolean isRtl(CharSequence  r1, int  i0, int  i1)
    {

        boolean z0;
        if (r1 != null)
        {
            if (i0 >= 0)
            {
                if (i1 >= 0)
                {
                    if (r1.length() - i1 >= i0)
                    {
                        if (mAlgorithm != null)
                        {
                            z0 = this.doCheck(r1, i0, i1);
                        }
                        else
                        {
                            z0 = this.defaultIsRtl();
                        }

                        return z0;
                    }
                }
            }
        }

        throw new IllegalArgumentException();
    }

    public boolean isRtl(char[]  r1, int  i0, int  i1)
    {


        return this.isRtl(CharBuffer.wrap(r1), i0, i1);
    }
}
