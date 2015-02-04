package com.example.root.startactivity2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startActivity(View view){
        int x=(Math.random()<0.5)?0:1;

        Intent intent;
        if(x==1){
            intent = new Intent(this, MainActivity2.class);
        } else {
            intent = new Intent(this, MainActivity3.class);
        }
        startActivity(intent);
    }
}
