package android.support.v4.view;

import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import android.util.AttributeSet;
import android.content.res.TypedArray;

public class ViewPager$LayoutParams extends ViewGroup$LayoutParams
{
    int childIndex;
    public int gravity;
    public boolean isDecor;
    boolean needsMeasure;
    int position;
    float widthFactor;

    public ViewPager$LayoutParams()
    {
        super(-1, -1);


        this.<init>(-1, -1);
        widthFactor = 0.0F;
    }

    public ViewPager$LayoutParams(Context  r1, AttributeSet  r2)
    {
        super(r1, r2);

        TypedArray r3;
        this.<init>(r1, r2);
        widthFactor = 0.0F;
        r3 = r1.obtainStyledAttributes(r2, ViewPager.access$400());
        gravity = r3.getInteger(0, 48);
        r3.recycle();
    }
}
