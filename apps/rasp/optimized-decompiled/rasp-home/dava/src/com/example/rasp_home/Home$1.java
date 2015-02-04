package com.example.rasp_home;

import android.support.v4.view.ViewPager$SimpleOnPageChangeListener;
import android.app.ActionBar;

class Home$1 extends ViewPager$SimpleOnPageChangeListener
{
    final Home this$0;
    private final ActionBar val$actionBar;

    Home$1(Home  r1, ActionBar  r2)
    {


        this$0 = r1;
        val$actionBar = r2;
        this.<init>();
    }

    public void onPageSelected(int  i0)
    {


        val$actionBar.setSelectedNavigationItem(i0);
    }
}
