package root.example.broadcastreceiverwithpermission;

import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private MyReceiver myReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyReceiver();
        intentFilter = new IntentFilter("_myAction_");
        intentFilter.addCategory("_myCategory_");

        registerReceiver(myReceiver, intentFilter, "root.example.mypermission", null);
    }


    @Override
    public void onDestroy() {

        unregisterReceiver(myReceiver);

    }
}
