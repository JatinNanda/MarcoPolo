package marcopolo.marcopolo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;

public class MainActivity extends ActionBarActivity {

    protected Button smsScreen;
    protected Button joinButton;
    public static Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "CJlO41ySg6ratzskNwDVNzBManCyHcwSRRG6vT3y", "gGLoy9UFLR5XHEm8cFAlen0BRVIM7DNTYEHaA2gx");
        setContentView(R.layout.activity_main);
        smsScreen = (Button) findViewById(R.id.smsScreen);
        smsScreen.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SMSActivity.class));
            }
        }));
        joinButton = (Button) findViewById(R.id.joinbutton);
        joinButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), JoinActivity.class));
            }
        }));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate th e menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
