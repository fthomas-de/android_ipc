package android.support.v4.view;

import java.util.Comparator;
import android.view.View;

class ViewPager$ViewPositionComparator implements java.util.Comparator
{

    ViewPager$ViewPositionComparator()
    {


        this.<init>();
    }

    public int compare(View  r1, View  r2)
    {

        ViewPager$LayoutParams r5, r6;
        int i0;
        r5 = (ViewPager$LayoutParams) r1.getLayoutParams();
        r6 = (ViewPager$LayoutParams) r2.getLayoutParams();

        if (r5.isDecor == r6.isDecor)
        {
            i0 = r5.position - r6.position;
        }
        else
        {
            if (r5.isDecor == false)
            {
                i0 = -1;
            }
            else
            {
                i0 = 1;
            }
        }

        return i0;
    }

    public int compare(Object  r1, Object  r2)
    {


        return this.compare((View) r1, (View) r2);
    }
}
