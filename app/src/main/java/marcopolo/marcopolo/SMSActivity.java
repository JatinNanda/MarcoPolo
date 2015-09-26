package marcopolo.marcopolo;

/**
 * Created by Jay on 9/25/15.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SMSActivity extends Activity implements OnClickListener{

    Button mapScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smslayout);


        findViewById(R.id.sendbutton).setOnClickListener(this);

        mapScreen = (Button) findViewById(R.id.mapScreen);
        mapScreen.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
            }
        }));
    }
    public void onClick(View v) {
        String[] arr = ((EditText)
                findViewById(R.id.editText)).getText().toString().split(",");
        for(String phoneNumber:arr) {
            try {
                SmsManager.getDefault().sendTextMessage(phoneNumber, null, "Your Marco Polo Code is:G45XXXYY"
                        , null, null);
            } catch (Exception e) {
                AlertDialog.Builder alertDialogBuilder = new
                        AlertDialog.Builder(this);
                AlertDialog dialog = alertDialogBuilder.create();


                dialog.setMessage(e.getMessage());


                dialog.show();

            }
        }
    }
    public ArrayList<String> parseNumbers(String numbers) {
        SmsManager s = SmsManager.getDefault();
        return s.divideMessage(numbers);
    }

}
