package android.support.v4.app;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.app.ActionBar;

class ActionBarDrawerToggleJellybeanMR2
{
    private static final String TAG = "ActionBarDrawerToggleImplJellybeanMR2";
    private static final int[] THEME_ATTRS;

    static
    {

        int[] r0;
        r0 = new int[1];
        r0[0] = 16843531;
        THEME_ATTRS = r0;
    }

    ActionBarDrawerToggleJellybeanMR2()
    {


        this.<init>();
    }

    public static Drawable getThemeUpIndicator(Activity  r0)
    {

        TypedArray r2;
        Drawable r3;
        r2 = r0.obtainStyledAttributes(THEME_ATTRS);
        r3 = r2.getDrawable(0);
        r2.recycle();
        return r3;
    }

    public static Object setActionBarDescription(Object  r0, Activity  r1, int  i0)
    {

        ActionBar r2;
        r2 = r1.getActionBar();

        if (r2 != null)
        {
            r2.setHomeActionContentDescription(i0);
        }

        return r0;
    }

    public static Object setActionBarUpIndicator(Object  r0, Activity  r1, Drawable  r2, int  i0)
    {

        ActionBar r3;
        r3 = r1.getActionBar();

        if (r3 != null)
        {
            r3.setHomeAsUpIndicator(r2);
            r3.setHomeActionContentDescription(i0);
        }

        return r0;
    }
}
