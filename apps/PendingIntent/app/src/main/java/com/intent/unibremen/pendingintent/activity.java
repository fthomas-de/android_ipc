package com.intent.unibremen.pendingintent;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// http://iserveandroid.blogspot.de/2011/03/how-to-launch-pending-intent.html
public class activity extends Activity {
    Button b1;
    PendingIntent pendingIntent;
    Context mContext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this.getApplicationContext();
        b1 = (Button) findViewById(R.id.button);
        Intent intent = new Intent();
        intent.setClass(mContext, activity2.class);
        pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        System.out.println("gogo");
        try {
            pendingIntent.send(mContext, 0, intent);
            System.out.println("Sending contentIntent done: ");

        } catch (PendingIntent.CanceledException e) {
            // the stack trace isn't very helpful here.  Just log the exception message.
            System.out.println("Sending contentIntent failed: ");
        }
    }


}
