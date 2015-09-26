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

public class GoogleMaps extends FragmentActivity implements OnMapReadyCallback {
    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemaps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        gps = new GPSTracker(getApplicationContext());
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Add a marker in Sydney, Australia, and move the camera.

        LatLng myLocation = new LatLng(GPSTracker.latitude, GPSTracker.longitude);
        Database.locs = new ArrayList<LatLng>();
        Database.locs.add(myLocation);
        Database.locs.add(new LatLng(myLocation.latitude + 0.05, myLocation.longitude + 0.02));
        Database.locs.add(new LatLng(myLocation.latitude - 0.002, myLocation.longitude - 0.026));
        Database.locs.add(new LatLng(myLocation.latitude + 0.03, myLocation.longitude - 0.05));
        Database.locs.add(new LatLng(myLocation.latitude - 0.07, myLocation.longitude + 0.003));

        map.addMarker(new MarkerOptions().position(Database.findLoc()).title("Meeting point").icon(BitmapDescriptorFactory.
                defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        //Database.locs.add(dest);
        for(LatLng aLocation : Database.locs) {
            map.addMarker(new MarkerOptions().position(aLocation).title("This is Jay's phone"));
        }
        //map.addMarker(new MarkerOptions().position(myLocation).title("This is Jay's phone"));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Database.findLoc(), 14.0f));
        map.setMyLocationEnabled(true);
        map.setLocationSource(gps);
    }
}