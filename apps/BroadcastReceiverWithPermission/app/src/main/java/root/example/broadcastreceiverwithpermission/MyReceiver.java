package root.example.broadcastreceiverwithpermission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by fthomas on 12.03.15.
 */
public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle e = intent.getExtras();
        String s = e.getString("_myIdentifier_");
        Toast.makeText(context, "msg with permission: " + s,

                Toast.LENGTH_LONG).show();
    }

}
