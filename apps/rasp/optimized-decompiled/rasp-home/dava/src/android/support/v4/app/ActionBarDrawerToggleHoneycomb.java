package android.support.v4.app;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.app.ActionBar;
import java.lang.reflect.Method;
import android.os.Build$VERSION;
import android.util.Log;
import android.widget.ImageView;

class ActionBarDrawerToggleHoneycomb
{
    private static final String TAG = "ActionBarDrawerToggleHoneycomb";
    private static final int[] THEME_ATTRS;

    static
    {

        int[] r0;
        r0 = new int[1];
        r0[0] = 16843531;
        THEME_ATTRS = r0;
    }

    ActionBarDrawerToggleHoneycomb()
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

        ActionBarDrawerToggleHoneycomb$SetIndicatorInfo r7, r18;
        ActionBar $r8;
        Method r9;
        Object[] r10;
        Integer $r11;
        int i3;
        CharSequence $r13;
        if (r0 == null)
        {
            r0 = r18;
            r18 = new ActionBarDrawerToggleHoneycomb$SetIndicatorInfo(r1);
        }

        r7 = (ActionBarDrawerToggleHoneycomb$SetIndicatorInfo) r0;

        label_1:
        if (r7.setHomeAsUpIndicator != null)
        {
            label_0:
            {
                try
                {
                    $r8 = r1.getActionBar();
                }
                catch (Exception $r15)
                {
                    break label_0;
                }

                try
                {
                    r9 = r7.setHomeActionContentDescription;
                }
                catch (Exception $r15)
                {
                    break label_0;
                }

                try
                {
                    r10 = new Object[1];
                }
                catch (Exception $r15)
                {
                    break label_0;
                }

                try
                {
                    $r11 = Integer.valueOf(i0);
                }
                catch (Exception $r15)
                {
                    break label_0;
                }

                try
                {
                    r10[0] = $r11;
                }
                catch (Exception $r15)
                {
                    break label_0;
                }

                r9.invoke($r8, r10);

                try
                {
                    i3 = Build$VERSION.SDK_INT;
                }
                catch (Exception $r15)
                {
                    break label_0;
                }

                if (i3 > 19)
                {
                    break label_1;
                }
                else
                {
                    try
                    {
                        $r13 = $r8.getSubtitle();
                    }
                    catch (Exception $r15)
                    {
                        break label_0;
                    }

                    try
                    {
                        $r8.setSubtitle($r13);
                        break label_1;
                    }
                    catch (Exception $r15)
                    {
                    }
                }
            } //end label_0:


            Log.w("ActionBarDrawerToggleHoneycomb", "Couldn\'t set content description via JB-MR2 API", $r15);
        }

        return r0;
    }

    public static Object setActionBarUpIndicator(Object  r0, Activity  r1, Drawable  r2, int  i0)
    {

        ActionBarDrawerToggleHoneycomb$SetIndicatorInfo r8, r24;
        ActionBar $r9;
        Method r10, r13;
        Object[] r11, r14;
        Integer $r15;
        if (r0 == null)
        {
            r0 = r24;
            r24 = new ActionBarDrawerToggleHoneycomb$SetIndicatorInfo(r1);
        }

        r8 = (ActionBarDrawerToggleHoneycomb$SetIndicatorInfo) r0;

        label_4:
        if (r8.setHomeAsUpIndicator == null)
        {
            if (r8.upIndicatorView == null)
            {
                Log.w("ActionBarDrawerToggleHoneycomb", "Couldn\'t set home-as-up indicator");
            }
            else
            {
                r8.upIndicatorView.setImageDrawable(r2);
            }
        }
        else
        {
            label_3:
            {
                label_2:
                {
                    try
                    {
                        $r9 = r1.getActionBar();
                    }
                    catch (Exception $r17)
                    {
                        break label_2;
                    }

                    try
                    {
                        r10 = r8.setHomeAsUpIndicator;
                    }
                    catch (Exception $r17)
                    {
                        break label_2;
                    }

                    try
                    {
                        r11 = new Object[1];
                    }
                    catch (Exception $r17)
                    {
                        break label_2;
                    }

                    try
                    {
                        r11[0] = r2;
                    }
                    catch (Exception $r17)
                    {
                        break label_2;
                    }

                    r10.invoke($r9, r11);

                    try
                    {
                        r13 = r8.setHomeActionContentDescription;
                    }
                    catch (Exception $r17)
                    {
                        break label_2;
                    }

                    try
                    {
                        r14 = new Object[1];
                    }
                    catch (Exception $r17)
                    {
                        break label_2;
                    }

                    try
                    {
                        $r15 = Integer.valueOf(i0);
                    }
                    catch (Exception $r17)
                    {
                        break label_2;
                    }

                    try
                    {
                        r14[0] = $r15;
                        break label_3;
                    }
                    catch (Exception $r17)
                    {
                    }
                } //end label_2:


                Log.w("ActionBarDrawerToggleHoneycomb", "Couldn\'t set home-as-up indicator via JB-MR2 API", $r17);
                break label_4;
            } //end label_3:


            r13.invoke($r9, r14);
        }

        return r0;
    }
}
