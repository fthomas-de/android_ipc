package com.intent.unibremen.pendingintent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class activity2 extends Activity{

    TextView v1;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        v1 = (TextView)findViewById(R.id.text);
        v1.setTextSize(30);
        v1.setText("Welcome to pendingIntent");
    }

}