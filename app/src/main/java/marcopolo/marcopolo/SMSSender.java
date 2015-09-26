package marcopolo.marcopolo;

/**
 * Created by Jay on 9/25/15.
 */
import android.telephony.SmsManager;
import java.util.ArrayList;
public class SMSSender {
    public ArrayList<String> parseNumbers(String numbers) {
        SmsManager s = SmsManager.getDefault();
        return s.divideMessage(numbers);
    }

}
