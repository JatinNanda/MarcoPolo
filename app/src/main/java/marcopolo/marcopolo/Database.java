package marcopolo.marcopolo;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jay on 9/26/15.
 */
public class Database {
    //public static ArrayList<LatLng> locs;
    private String code;
    private ParseObject parse;
    static final int len = 5;
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd;
    //private int groupSize;
    //private int tableSize;
    private static boolean a;

    public Database(int groupSize) {
        StringBuilder sb = new StringBuilder( len );
        rnd = new Random();
        for( int i = 0; i < len; i++ ) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
            code = sb.toString();
        }
        parse = new ParseObject("testObject");
        parse.put("Size", groupSize);
    }
    public Database(String code) {
        this.code = code;
        parse = new ParseObject("testObject");
    }
    public void putLocation(LatLng loc) {
        parse.put("Latitude", "" + loc.latitude);
        parse.put("Longitude","" + loc.longitude);
        parse.saveInBackground();
    }
    public void putCode() {
        parse.put("Code", code);
        parse.saveInBackground();
    }
    public static LatLng findGroupLoc(List<LatLng> locs) {
        double finalLat = 0.0;
        double finalLong = 0.0;
        for(LatLng l : locs) {
            finalLat += l.latitude;
            finalLong += l.longitude;
        }
        return new LatLng(finalLat / locs.size(), finalLong / locs.size());
    }
    public String getCode() {
        return code;
    }
    public List<LatLng> fetchLocations() {
        List<ParseObject> list = fetch();
        List<LatLng> ans = new ArrayList<LatLng>();
        for(ParseObject p : list) {
            LatLng l = new LatLng(Double.parseDouble((String) p.get("Latitude")),
                    Double.parseDouble((String) p.get("Longitude")));
            ans.add(l);
        }
        return ans;
    }
    public List<ParseObject> fetch() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("testObject");
        query.whereEqualTo("Code", getCode());
        List<ParseObject> ans = new ArrayList<ParseObject>();
        try {
            ans =  query.find();
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        return ans;
    }
    public static boolean codeExists(String code) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("testObject");
        query.whereEqualTo("Code", code);
        //a = false;
        boolean ans = false;
        try {
            ans = query.find().size() > 0;
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        return ans;
    }
    public static void setQueryData(List<ParseObject> list) {
        a = list.size() > 0;
        System.out.println("SIZE: " + list.size());
    }
    public static boolean getQueryData() {
        return a;
    }
    public int findGroupSize() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("testObject");
        query.whereEqualTo("Code", code);
        query.whereExists("Size");
        int ans = 0;
        try {
            ans = Integer.parseInt(query.getFirst().get("Size") + "");
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        return ans + 1;
    }
}
