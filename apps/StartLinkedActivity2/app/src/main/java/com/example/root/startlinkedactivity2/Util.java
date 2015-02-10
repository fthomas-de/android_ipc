package com.example.root.startlinkedactivity2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Florian Thomas on 10.02.2015.
 */
public class Util extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
    }

    public void startActivityOnClick(Intent intent){
        startActivity(intent);
    }
}
