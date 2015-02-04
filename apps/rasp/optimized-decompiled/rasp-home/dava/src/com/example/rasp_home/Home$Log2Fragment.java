package com.example.rasp_home;

import android.app.Fragment;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View$OnClickListener;

public class Home$Log2Fragment extends Fragment
{
    private static final int LOGLEN = 28;
    private static final int PORT = 1892;
    private String address;
    private TextView log2;
    private String log_msg;
    private ImageButton refreshLog2;
    View$OnClickListener refreshLog2_handler;

    public Home$Log2Fragment()
    {


        this.<init>();
        address = "mylilraspi.raspctl.com";
        log_msg = "";
        refreshLog2_handler = new Home$Log2Fragment$1(this);
    }

    static String access$0(Home$Log2Fragment  r0)
    {


        return r0.address;
    }

    static void access$1(Home$Log2Fragment  r0, String  r1)
    {


        r0.log_msg = r1;
    }

    static TextView access$2(Home$Log2Fragment  r0)
    {


        return r0.log2;
    }

    static String access$3(Home$Log2Fragment  r0)
    {


        return r0.log_msg;
    }

    public static Home$Log2Fragment newInstance(int  i0)
    {


        return new Home$Log2Fragment();
    }

    public View onCreateView(LayoutInflater  r1, ViewGroup  r2, Bundle  r3)
    {

        View r4;
        Home$Log2Fragment$AsyncTaskRunner r6;
        String[] r12;
        r4 = r1.inflate(2130903043, r2, false);
        log2 = (TextView) r4.findViewById(2131230732);
        refreshLog2 = (ImageButton) r4.findViewById(2131230733);
        refreshLog2.setOnClickListener(refreshLog2_handler);
        r6 = new Home$Log2Fragment$AsyncTaskRunner(this, null);
        r12 = new String[1];
        r12[(int) 0] = "GETlg2";
        r6.execute(r12);
        return r4;
    }

    public void onResume()
    {


        this.onResume();
    }

    private void showToast(String  r1)
    {


        Toast.makeText(this.getActivity(), r1, 0).show();
    }
}
