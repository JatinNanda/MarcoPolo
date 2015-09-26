package marcopolo.marcopolo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Jay on 9/26/15.
 */
public class JoinActivity extends ActionBarActivity {
    protected Button codeButton;
    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_activity);
        codeButton = (Button) findViewById(R.id.codebutton);
        codeButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = ((EditText)
                        findViewById(R.id.codetext)).getText().toString();
                startActivity(new Intent(getApplicationContext(), GoogleMaps.class));
            }
        }));
    }
}
