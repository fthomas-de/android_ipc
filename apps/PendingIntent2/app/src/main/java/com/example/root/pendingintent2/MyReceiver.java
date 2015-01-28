package com.example.root.pendingintent2;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Times Up!!!!", Toast.LENGTH_LONG).show();
    }

}
