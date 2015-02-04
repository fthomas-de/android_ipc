package android.support.v4.text;

import java.util.Locale;

public final class BidiFormatter$Builder
{
    private int mFlags;
    private boolean mIsRtlContext;
    private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

    public BidiFormatter$Builder()
    {


        this.<init>();
        this.initialize(BidiFormatter.access$000(Locale.getDefault()));
    }

    public BidiFormatter$Builder(Locale  r1)
    {


        this.<init>();
        this.initialize(BidiFormatter.access$000(r1));
    }

    public BidiFormatter$Builder(boolean  z0)
    {


        this.<init>();
        this.initialize(z0);
    }

    public BidiFormatter build()
    {

        BidiFormatter r4;
        label_0:
        {
            if (mFlags == 2)
            {
                if (mTextDirectionHeuristicCompat == BidiFormatter.access$100())
                {
                    r4 = BidiFormatter$Builder.getDefaultInstanceFromContext(mIsRtlContext);
                    break label_0;
                }
            }

            r4 = new BidiFormatter(mIsRtlContext, mFlags, mTextDirectionHeuristicCompat, null);
        } //end label_0:


        return r4;
    }

    private static BidiFormatter getDefaultInstanceFromContext(boolean  z0)
    {

        BidiFormatter r0;
        if (z0 == false)
        {
            r0 = BidiFormatter.access$300();
        }
        else
        {
            r0 = BidiFormatter.access$200();
        }

        return r0;
    }

    private void initialize(boolean  z0)
    {


        mIsRtlContext = z0;
        mTextDirectionHeuristicCompat = BidiFormatter.access$100();
        mFlags = 2;
    }

    public BidiFormatter$Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat  r1)
    {


        mTextDirectionHeuristicCompat = r1;
        return this;
    }

    public BidiFormatter$Builder stereoReset(boolean  z0)
    {


        if (z0 == false)
        {
            mFlags = mFlags & -3;
        }
        else
        {
            mFlags = mFlags | 2;
        }

        return this;
    }
}
