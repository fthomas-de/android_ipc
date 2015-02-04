package android.support.v4.text;

import java.util.Locale;

class TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale extends TextDirectionHeuristicsCompat$TextDirectionHeuristicImpl
{
    public static final TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale INSTANCE;

    static
    {


        INSTANCE = new TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale();
    }

    public TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale()
    {
        super(null);


        this.<init>(null);
    }

    protected boolean defaultIsRtl()
    {

        boolean z0;
        z0 = true;

        if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) != (int) 1)
        {
            z0 = false;
        }

        return z0;
    }
}
