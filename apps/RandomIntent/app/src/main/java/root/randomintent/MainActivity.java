package root.randomintent;

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

    public void randomIntent(View view) {
        Intent intent;

        double rand = Math.random();

        if(rand < 0.5) {
            intent = new Intent();
            intent.setAction("_myAction_");
            intent.addCategory("_myCategory_");
            intent.putExtra("_myIdentifier_", "Info von ri");
            System.out.println("implicite");
            sendBroadcast(intent);
        } else {
            intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
            System.out.println("explicite");
        }
    }

}
