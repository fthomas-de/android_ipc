package example.root.startlinkedactivity3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Util extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);
    }

    public static void startActivity(MainActivity1 mainActivity1) {
        Intent intent = new Intent(mainActivity1, MainActivity2.class);
        mainActivity1.startActivity(intent);
    }
}
