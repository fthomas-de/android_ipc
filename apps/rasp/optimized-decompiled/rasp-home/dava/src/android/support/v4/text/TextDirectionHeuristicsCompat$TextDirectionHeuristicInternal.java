package android.support.v4.text;


class TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal extends TextDirectionHeuristicsCompat$TextDirectionHeuristicImpl
{
    private final boolean mDefaultIsRtl;

    private TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat$TextDirectionAlgorithm  r1, boolean  z0)
    {
        super(r1);


        this.<init>(r1);
        mDefaultIsRtl = z0;
    }

    TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat$TextDirectionAlgorithm  r1, boolean  z0, TextDirectionHeuristicsCompat$1  r2)
    {
        this(r1, z0);


        this.<init>(r1, z0);
    }

    protected boolean defaultIsRtl()
    {


        return mDefaultIsRtl;
    }
}
