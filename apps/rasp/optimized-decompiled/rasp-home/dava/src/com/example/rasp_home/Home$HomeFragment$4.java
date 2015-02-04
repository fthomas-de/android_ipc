package com.example.rasp_home;

import android.view.View$OnClickListener;
import android.view.View;

class Home$HomeFragment$4 implements android.view.View$OnClickListener
{
    final Home$HomeFragment this$1;

    Home$HomeFragment$4(Home$HomeFragment  r1)
    {


        this$1 = r1;
        this.<init>();
    }

    public void onClick(View  r1)
    {

        Home$HomeFragment$AsyncTaskRunner r2;
        String[] r5;
        r2 = new Home$HomeFragment$AsyncTaskRunner(this$1, null);
        r5 = new String[1];
        r5[0] = "GET101";
        r2.execute(r5);
    }
}
