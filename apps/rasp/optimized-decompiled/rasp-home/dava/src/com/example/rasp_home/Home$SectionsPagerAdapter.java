package com.example.rasp_home;

import android.support.v13.app.FragmentPagerAdapter;
import android.app.FragmentManager;
import android.app.Fragment;
import java.util.Locale;

public class Home$SectionsPagerAdapter extends FragmentPagerAdapter
{
    final Home this$0;

    public Home$SectionsPagerAdapter(Home  r1, FragmentManager  r2)
    {
        super(r2);


        this$0 = r1;
        this.<init>(r2);
    }

    public int getCount()
    {


        return 3;
    }

    public Fragment getItem(int  i0)
    {

        Fragment r1;
        r1 = null;

        label_0:
        switch (i0)
        {
            case 0:
                r1 = Home$HomeFragment.newInstance(i0 + 1);
                break label_0;

            case 1:
                r1 = Home$Log1Fragment.newInstance(i0 + 1);
                break label_0;

            case 2:
                r1 = Home$Log2Fragment.newInstance(i0 + 1);
                break label_0;

            default:
                break label_0;
        }

        return r1;
    }

    public CharSequence getPageTitle(int  i0)
    {

        Locale r1;
        String r2;
        r1 = Locale.getDefault();

        label_1:
        switch (i0)
        {
            case 0:
                r2 = this$0.getString(2131034113).toUpperCase(r1);
                break label_1;

            case 1:
                r2 = this$0.getString(2131034114).toUpperCase(r1);
                break label_1;

            case 2:
                r2 = this$0.getString(2131034115).toUpperCase(r1);
                break label_1;

            default:
                r2 = null;
                break label_1;
        }

        return r2;
    }
}
