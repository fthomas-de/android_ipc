package com.example.root.startlinkedactivity;

import android.content.Intent;

/**
 * Created by Florian Thomas on 10.02.2015.
 */
public class Util {
    public static void startActivityOnClick(MainActivity mainActivity){
        Intent intent = new Intent(mainActivity, MainActivity2.class);
        mainActivity.startActivity(intent);
    }
}
