package android.support.v4.widget;

import android.view.ViewGroup$MarginLayoutParams;
import android.content.Context;
import android.util.AttributeSet;
import android.content.res.TypedArray;
import android.view.ViewGroup$LayoutParams;

public class DrawerLayout$LayoutParams extends ViewGroup$MarginLayoutParams
{
    public int gravity;
    boolean isPeeking;
    boolean knownOpen;
    float onScreen;

    public DrawerLayout$LayoutParams(int  i0, int  i1)
    {
        super(i0, i1);


        this.<init>(i0, i1);
        gravity = 0;
    }

    public DrawerLayout$LayoutParams(int  i0, int  i1, int  i2)
    {
        this(i0, i1);


        this.<init>(i0, i1);
        gravity = i2;
    }

    public DrawerLayout$LayoutParams(Context  r1, AttributeSet  r2)
    {
        super(r1, r2);

        TypedArray r4;
        this.<init>(r1, r2);
        gravity = 0;
        r4 = r1.obtainStyledAttributes(r2, DrawerLayout.access$100());
        gravity = r4.getInt(0, 0);
        r4.recycle();
    }

    public DrawerLayout$LayoutParams(DrawerLayout$LayoutParams  r1)
    {
        super(r1);


        this.<init>(r1);
        gravity = 0;
        gravity = r1.gravity;
    }

    public DrawerLayout$LayoutParams(ViewGroup$LayoutParams  r1)
    {
        super(r1);


        this.<init>(r1);
        gravity = 0;
    }

    public DrawerLayout$LayoutParams(ViewGroup$MarginLayoutParams  r1)
    {
        super(r1);


        this.<init>(r1);
        gravity = 0;
    }
}
