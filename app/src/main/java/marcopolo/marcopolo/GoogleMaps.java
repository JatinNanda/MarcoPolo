package marcopolo.marcopolo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class GoogleMaps extends FragmentActivity implements OnMapReadyCallback {
    GPSTracker gps;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemaps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        gps = new GPSTracker(getApplicationContext());
        MainActivity.db.putLocation(new LatLng(GPSTracker.latitude, GPSTracker.longitude));
        MainActivity.db.putCode();

    }

    @Override
    public void onMapReady(GoogleMap map) {

        //LatLng myLocation = new LatLng(GPSTracker.latitude, GPSTracker.longitude);
        List<LatLng> list = MainActivity.db.fetchLocations();
        List<LatLng> visited = new ArrayList<LatLng>();
        while(visited.size() < MainActivity.db.findGroupSize()) {
           list = MainActivity.db.fetchLocations();
            for(LatLng l: list) {
                if (!visited.contains(l)) {
                    map.addMarker(new MarkerOptions().position(l).title("This is Jay's phone"));
                    visited.add(l);
                }
            }
        }


        /*Database.locs = new ArrayList<LatLng>();
        Database.locs.add(myLocation);
        Database.locs.add(new LatLng(myLocation.latitude + 0.05, myLocation.longitude + 0.02));
        Database.locs.add(new LatLng(myLocation.latitude - 0.002, myLocation.longitude - 0.026));
        Database.locs.add(new LatLng(myLocation.latitude + 0.03, myLocation.longitude - 0.05));
        Database.locs.add(new LatLng(myLocation.latitude - 0.07, myLocation.longitude + 0.003));*/

        map.addMarker(new MarkerOptions().position(Database.findGroupLoc(list)).title("Meeting point").icon(BitmapDescriptorFactory.
                defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Database.findGroupLoc(list), 14.0f));
        //map.setMyLocationEnabled(true);
        //map.setLocationSource(gps);
    }
}