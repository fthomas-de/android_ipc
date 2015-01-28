package com.example.root.pendingintent2;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// http://www.sanfoundry.com/java-android-program-pending-intent/

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlert(v);
            }
        });
    }

    public void startAlert(View view) {
        EditText text = (EditText) findViewById(R.id.editText1);
        int time = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent pend_intent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 0, intent, 0);
        //calls the alarm
        AlarmManager alarmManager = (AlarmManager) getSystemService(
                ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (time * 1000), pend_intent);
    }
}