package com.example.djurus.netwrkit;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        // Add a marker in SouthHall and move the camera
        LatLng rsf = new LatLng(37.868647,-122.262859);
        LatLng rsf1 = new LatLng(37.868500,-122.262859);
        LatLng rsf2 = new LatLng(37.868500,-122.262700);

        mMap.addMarker(new MarkerOptions().position(rsf).title("Shaun Dju"));
        mMap.addMarker(new MarkerOptions().position(rsf1).title("Jew Lie"));
        mMap.addMarker(new MarkerOptions().position(rsf2).title("Pratik Nada"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rsf,19.0f));
    }
}
