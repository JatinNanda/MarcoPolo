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
        int groupSize = MainActivity.db.findGroupSize();
        while(visited.size() < groupSize) {
           list = MainActivity.db.fetchLocations();
            for(LatLng l: list) {
                if (!visited.contains(l)) {
                    visited.add(l);
                }
            }
        }

        LatLng groupMeet = Database.findGroupLoc(list);
        map.addMarker(new MarkerOptions().position(groupMeet).title("Meeting point").icon(BitmapDescriptorFactory.
                defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(groupMeet, 14.0f));
        MainActivity.db.addDistance(groupMeet);


        float[] distances = MainActivity.db.fetchDistances(visited.size());
        for(int x = 0; x < distances.length; x++) {
            map.addMarker(new MarkerOptions().position(list.get(x)).title("Distance away: " + distances[x]));
        }

        //map.setMyLocationEnabled(true);
        //map.setLocationSource(gps);


    }
}