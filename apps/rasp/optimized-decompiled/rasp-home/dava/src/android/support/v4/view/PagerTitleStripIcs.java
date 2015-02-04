package android.support.v4.view;

import android.widget.TextView;

class PagerTitleStripIcs
{

    PagerTitleStripIcs()
    {


        this.<init>();
    }

    public static void setSingleLineAllCaps(TextView  r0)
    {


        r0.setTransformationMethod(new PagerTitleStripIcs$SingleLineAllCapsTransform(r0.getContext()));
    }
}
