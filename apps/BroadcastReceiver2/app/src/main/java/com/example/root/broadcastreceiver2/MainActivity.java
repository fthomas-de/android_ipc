package com.example.root.broadcastreceiver2;

import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

    private ConnectivityChangeReceiver myReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new ConnectivityChangeReceiver();
        intentFilter = new IntentFilter("_myAction_");
        intentFilter.addCategory("_myCategory_");

        registerReceiver(myReceiver, intentFilter);
    }


    @Override
    public void onDestroy() {

        unregisterReceiver(myReceiver);

    }
}
