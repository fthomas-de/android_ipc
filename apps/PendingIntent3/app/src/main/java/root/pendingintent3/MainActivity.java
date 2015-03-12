package root.pendingintent3;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendIntent(View view) {
        Intent intent = new Intent(this, MainActivity2.class);

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        try {
            pIntent.send(this, 0, intent);
        } catch (PendingIntent.CanceledException e) {
            System.out.println("exception");
        }

    }
}
