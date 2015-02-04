package android.support.v4.app;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.Method;
import android.view.ViewGroup;
import android.widget.ImageView;

class ActionBarDrawerToggleHoneycomb$SetIndicatorInfo
{
    public Method setHomeActionContentDescription;
    public Method setHomeAsUpIndicator;
    public ImageView upIndicatorView;

    ActionBarDrawerToggleHoneycomb$SetIndicatorInfo(Activity  r1)
    {

        Class r2, r4, r13, r16;
        String r3, r14;
        View r5, r7, r8, r9;
        Class[] r10, r15;
        Method $r11, $r17;
        ViewGroup r21;
        this.<init>();

        label_1:
        {
            label_0:
            {
                r2 = class "android/app/ActionBar";

                r3 = "setHomeAsUpIndicator";

                r10 = new Class[1];

                r4 = class "android/graphics/drawable/Drawable";

                r10[0] = r4;

                try
                {
                    $r11 = r2.getDeclaredMethod(r3, r10);
                }
                catch (NoSuchMethodException $r19)
                {
                    break label_0;
                }

                setHomeAsUpIndicator = $r11;

                r13 = class "android/app/ActionBar";

                r14 = "setHomeActionContentDescription";

                r15 = new Class[1];

                r16 = Integer.TYPE;

                r15[0] = r16;

                try
                {
                    $r17 = r13.getDeclaredMethod(r14, r15);
                }
                catch (NoSuchMethodException $r19)
                {
                    break label_0;
                }

                setHomeActionContentDescription = $r17;
                break label_1;
            } //end label_0:


            r5 = r1.findViewById(16908332);

            if (r5 != null)
            {
                r21 = (ViewGroup) r5.getParent();

                if (r21.getChildCount() == 2)
                {
                    r7 = r21.getChildAt(0);
                    r8 = r21.getChildAt(1);

                    if (r7.getId() != 16908332)
                    {
                        r9 = r7;
                    }
                    else
                    {
                        r9 = r8;
                    }

                    if (r9 instanceof ImageView != false)
                    {
                        upIndicatorView = (ImageView) r9;
                    }
                }
            }
        } //end label_1:

    }
}
