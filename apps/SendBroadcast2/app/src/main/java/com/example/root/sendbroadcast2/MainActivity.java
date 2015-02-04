package com.example.root.sendbroadcast2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBroadcast(){
        Intent intent = new Intent();
        intent.setComponent(this, test.class);
        sendBroadcast(intent);
    }
}
