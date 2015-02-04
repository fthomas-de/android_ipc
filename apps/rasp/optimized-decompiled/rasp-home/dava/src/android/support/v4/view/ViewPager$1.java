package android.support.v4.view;

import java.util.Comparator;

final class ViewPager$1 implements java.util.Comparator
{

    ViewPager$1()
    {


        this.<init>();
    }

    public int compare(ViewPager$ItemInfo  r1, ViewPager$ItemInfo  r2)
    {


        return r1.position - r2.position;
    }

    public int compare(Object  r1, Object  r2)
    {


        return this.compare((ViewPager$ItemInfo) r1, (ViewPager$ItemInfo) r2);
    }
}
