package android.support.v4.widget;

import android.view.ViewGroup$MarginLayoutParams;
import android.content.Context;
import android.util.AttributeSet;
import android.content.res.TypedArray;
import android.view.ViewGroup$LayoutParams;
import android.graphics.Paint;

public class SlidingPaneLayout$LayoutParams extends ViewGroup$MarginLayoutParams
{
    private static final int[] ATTRS;
    Paint dimPaint;
    boolean dimWhenOffset;
    boolean slideable;
    public float weight;

    static
    {

        int[] r0;
        r0 = new int[1];
        r0[0] = 16843137;
        ATTRS = r0;
    }

    public SlidingPaneLayout$LayoutParams()
    {
        super(-1, -1);


        this.<init>(-1, -1);
        weight = 0.0F;
    }

    public SlidingPaneLayout$LayoutParams(int  i0, int  i1)
    {
        super(i0, i1);


        this.<init>(i0, i1);
        weight = 0.0F;
    }

    public SlidingPaneLayout$LayoutParams(Context  r1, AttributeSet  r2)
    {
        super(r1, r2);

        TypedArray r4;
        this.<init>(r1, r2);
        weight = 0.0F;
        r4 = r1.obtainStyledAttributes(r2, ATTRS);
        weight = r4.getFloat(0, 0.0F);
        r4.recycle();
    }

    public SlidingPaneLayout$LayoutParams(SlidingPaneLayout$LayoutParams  r1)
    {
        super(r1);


        this.<init>(r1);
        weight = 0.0F;
        weight = r1.weight;
    }

    public SlidingPaneLayout$LayoutParams(ViewGroup$LayoutParams  r1)
    {
        super(r1);


        this.<init>(r1);
        weight = 0.0F;
    }

    public SlidingPaneLayout$LayoutParams(ViewGroup$MarginLayoutParams  r1)
    {
        super(r1);


        this.<init>(r1);
        weight = 0.0F;
    }
}
