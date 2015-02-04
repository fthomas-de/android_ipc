package com.example.rasp_home;

import android.view.View$OnClickListener;
import android.view.View;

class Home$HomeFragment$2 implements android.view.View$OnClickListener
{
    final Home$HomeFragment this$1;

    Home$HomeFragment$2(Home$HomeFragment  r1)
    {


        this$1 = r1;
        this.<init>();
    }

    public void onClick(View  r1)
    {

        Home$HomeFragment$AsyncTaskRunner r2;
        String[] r6, r11;
        r2 = new Home$HomeFragment$AsyncTaskRunner(this$1, null);

        if (Home$HomeFragment.access$2(this$1).booleanValue() == false)
        {
            r11 = new String[(int) 1];
            r11[(int) 0] = "SETB21";
            r2.execute(r11);
            Home$HomeFragment.access$3(this$1, Boolean.valueOf(true));
        }
        else
        {
            r6 = new String[(int) 1];
            r6[(int) 0] = "SETB20";
            r2.execute(r6);
            Home$HomeFragment.access$3(this$1, Boolean.valueOf(false));
        }
    }
}
