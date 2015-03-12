package root.example.sendbroadcastwithpermission;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendBroadcastWithPermission(View view) {
        Intent intent = new Intent();
        intent.setAction("_myAction_");
        intent.addCategory("_myCategory_");
        intent.putExtra("_myIdentifier_", "password123");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        sendBroadcast(intent, "root.example.mypermission");
    }
}
