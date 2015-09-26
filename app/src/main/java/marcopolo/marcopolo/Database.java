package marcopolo.marcopolo;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Jay on 9/26/15.
 */
public class Database {
    public static ArrayList<LatLng> locs;
    public static LatLng findLoc() {
        double finalLat = 0.0;
        double finalLong = 0.0;
        for(LatLng l : locs) {
            finalLat += l.latitude;
            finalLong += l.longitude;
        }
        return new LatLng(finalLat / locs.size(), finalLong / locs.size());
    }
}
