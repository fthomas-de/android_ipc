package com.example.rasp_home;

import android.view.View$OnClickListener;
import android.view.View;
import android.text.Editable;
import android.widget.EditText;

class Home$HomeFragment$3 implements android.view.View$OnClickListener
{
    final Home$HomeFragment this$1;

    Home$HomeFragment$3(Home$HomeFragment  r1)
    {


        this$1 = r1;
        this.<init>();
    }

    public void onClick(View  r1)
    {


        Home$HomeFragment.access$5(this$1, ((EditText) Home$HomeFragment.access$4(this$1).findViewById(2131230721)).getText().toString());

        if (Home$HomeFragment.access$6(this$1).equals("") != false)
        {
            Home$HomeFragment.access$5(this$1, "mylilraspi.raspctl.com");
        }

        Home$HomeFragment.access$7(this$1, Home$HomeFragment.access$6(this$1));
    }
}
