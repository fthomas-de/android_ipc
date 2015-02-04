package com.broadcast.unibremen.sendbroadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//implicite
public class SendBroadcastActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broadcast);
    }

    public void broadcastIntent(View view)
    {
        Intent intent = new Intent();
        intent.setAction("_myAction_");
        intent.addCategory("_myCategory_");
        intent.putExtra("_myIdentifier_", "geheime infos");
        sendBroadcast(intent);
    }
}
