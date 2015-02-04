package com.example.rasp_home;

import android.app.Fragment;
import android.view.View;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View$OnClickListener;

public class Home$HomeFragment extends Fragment
{
    private static final String DEFAULT_ADDRESS = "mylilraspi.raspctl.com";
    private static final int PORT = 1892;
    private String address;
    private ImageButton plug1;
    View$OnClickListener plug1_handler;
    private Boolean plug1_status;
    private ImageButton plug2;
    View$OnClickListener plug2_handler;
    private Boolean plug2_status;
    private ImageButton refresh;
    View$OnClickListener refresh_handler;
    private ImageButton restart;
    View$OnClickListener restart_handler;
    private View rootView;
    private ImageButton set;
    View$OnClickListener set_handler;
    private TextView status;

    public Home$HomeFragment()
    {


        this.<init>();
        address = "mylilraspi.raspctl.com";
        plug1_status = Boolean.valueOf(false);
        plug2_status = Boolean.valueOf(false);
        plug1_handler = new Home$HomeFragment$1(this);
        plug2_handler = new Home$HomeFragment$2(this);
        set_handler = new Home$HomeFragment$3(this);
        refresh_handler = new Home$HomeFragment$4(this);
        restart_handler = new Home$HomeFragment$5(this);
    }

    static Boolean access$0(Home$HomeFragment  r0)
    {


        return r0.plug1_status;
    }

    static void access$1(Home$HomeFragment  r0, Boolean  r1)
    {


        r0.plug1_status = r1;
    }

    static Boolean access$2(Home$HomeFragment  r0)
    {


        return r0.plug2_status;
    }

    static void access$3(Home$HomeFragment  r0, Boolean  r1)
    {


        r0.plug2_status = r1;
    }

    static View access$4(Home$HomeFragment  r0)
    {


        return r0.rootView;
    }

    static void access$5(Home$HomeFragment  r0, String  r1)
    {


        r0.address = r1;
    }

    static String access$6(Home$HomeFragment  r0)
    {


        return r0.address;
    }

    static void access$7(Home$HomeFragment  r0, String  r1)
    {


        r0.showToast(r1);
    }

    static TextView access$8(Home$HomeFragment  r0)
    {


        return r0.status;
    }

    public static Home$HomeFragment newInstance(int  i0)
    {


        return new Home$HomeFragment();
    }

    public View onCreateView(LayoutInflater  r1, ViewGroup  r2, Bundle  r3)
    {

        Object n0;
        Home$HomeFragment$AsyncTaskRunner r4, r5;
        String[] r35, r38;
        n0 = null;
        rootView = r1.inflate(2130903041, r2, false);
        plug1 = (ImageButton) rootView.findViewById(2131230723);
        plug1.setOnClickListener(plug1_handler);
        plug2 = (ImageButton) rootView.findViewById(2131230724);
        plug2.setOnClickListener(plug2_handler);
        set = (ImageButton) rootView.findViewById(2131230722);
        set.setOnClickListener(set_handler);
        refresh = (ImageButton) rootView.findViewById(2131230726);
        refresh.setOnClickListener(refresh_handler);
        restart = (ImageButton) rootView.findViewById(2131230725);
        restart.setOnClickListener(restart_handler);
        status = (TextView) rootView.findViewById(2131230727);
        r4 = new Home$HomeFragment$AsyncTaskRunner(this, n0);
        r35 = new String[1];
        r35[(int) 0] = "GETAS1";
        r4.execute(r35);
        r5 = new Home$HomeFragment$AsyncTaskRunner(this, n0);
        r38 = new String[1];
        r38[(int) 0] = "GETBS2";
        r5.execute(r38);
        return rootView;
    }

    public void onResume()
    {

        Home$HomeFragment$AsyncTaskRunner r1;
        String[] r3;
        r1 = new Home$HomeFragment$AsyncTaskRunner(this, null);
        r3 = new String[1];
        r3[0] = "GET101";
        r1.execute(r3);
        this.onResume();
    }

    private void showToast(String  r1)
    {


        Toast.makeText(this.getActivity(), r1, 0).show();
    }
}
