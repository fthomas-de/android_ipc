package com.example.rasp_home;

import android.view.View$OnClickListener;
import android.view.View;

class Home$Log1Fragment$1 implements android.view.View$OnClickListener
{
    final Home$Log1Fragment this$1;

    Home$Log1Fragment$1(Home$Log1Fragment  r1)
    {


        this$1 = r1;
        this.<init>();
    }

    public void onClick(View  r1)
    {

        Home$Log1Fragment$AsyncTaskRunner r2;
        String[] r5;
        r2 = new Home$Log1Fragment$AsyncTaskRunner(this$1, null);
        r5 = new String[1];
        r5[0] = "GETlg1";
        r2.execute(r5);
    }
}
