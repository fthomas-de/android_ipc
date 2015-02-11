package com.example.root.broadcastreceiver3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle e = intent.getExtras();
        String s = e.getString("_myIdentifier_");
        Toast.makeText(context, "Broadcast3 Intent Detected: " + s,
                Toast.LENGTH_LONG).show();
    }
}
