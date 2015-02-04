package android.support.v4.view;

import android.text.method.SingleLineTransformationMethod;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import java.util.Locale;

class PagerTitleStripIcs$SingleLineAllCapsTransform extends SingleLineTransformationMethod
{
    private static final String TAG = "SingleLineAllCapsTransform";
    private Locale mLocale;

    public PagerTitleStripIcs$SingleLineAllCapsTransform(Context  r1)
    {


        this.<init>();
        mLocale = r1.getResources().getConfiguration().locale;
    }

    public CharSequence getTransformation(CharSequence  r1, View  r2)
    {

        CharSequence r5;
        String r6;
        r5 = this.getTransformation(r1, r2);

        if (r5 == null)
        {
            r6 = null;
        }
        else
        {
            r6 = r5.toString().toUpperCase(mLocale);
        }

        return r6;
    }
}
