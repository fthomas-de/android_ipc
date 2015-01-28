package com.broadcast.unibremen.broadcastreceiver;
        // http://www.techotopia.com/index.php/Android_Broadcast_Intents_and_Broadcast_Receivers
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle e = intent.getExtras();
        String s = e.getString("myString");
        Toast.makeText(context, "Broadcast Intent Detected: " + s,

                Toast.LENGTH_LONG).show();
    }
}
