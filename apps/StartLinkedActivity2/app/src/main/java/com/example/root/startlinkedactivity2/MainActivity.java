package com.example.root.startlinkedactivity2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startActivityOnClick(){
        Util u = new Util();
        Intent intent = new Intent(this, MainActivity2.class);
        u.startActivityOnClick(intent);
    }

}
