package com.example.root.broadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ConnectivityChangeReceiver extends BroadcastReceiver {
    public ConnectivityChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle e = intent.getExtras();
        String s = e.getString("_myIdentifier_");
        Toast.makeText(context, "Broadcast2 Intent Detected: " + s,

                Toast.LENGTH_LONG).show();
    }
}
