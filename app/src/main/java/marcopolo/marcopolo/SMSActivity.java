package marcopolo.marcopolo;

/**
 * Created by Jay on 9/25/15.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import java.util.ArrayList;

public class SMSActivity extends Activity implements OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smslayout);


        findViewById(R.id.sendbutton).setOnClickListener(this);
    }
    public void onClick(View v) {
        String phoneNumber = ((EditText)
                findViewById(R.id.editText)).getText().toString();
        try {
            SmsManager.getDefault().sendTextMessage(phoneNumber, null, "G45XXXYY"
                    , null, null);
        } catch (Exception e) {
            AlertDialog.Builder alertDialogBuilder = new
                    AlertDialog.Builder(this);
            AlertDialog dialog = alertDialogBuilder.create();


            dialog.setMessage(e.getMessage());


            dialog.show();

        }
    }
    public ArrayList<String> parseNumbers(String numbers) {
        SmsManager s = SmsManager.getDefault();
        return s.divideMessage(numbers);
    }

}
