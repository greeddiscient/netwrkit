package com.example.djurus.netwrkit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private SharedPreferences sharedPreferences;
    private GoogleMap mMap;
    private ArrayList<Person> displayList = new ArrayList<>();
    private ArrayList<Person> attendeeList = new ArrayList<>();
    private Person user;
    private boolean isFullMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String jsonDisplayList=sharedPreferences.getString("displayList","");
        Gson gson=new Gson();
        Type type = new TypeToken<ArrayList<Person>>(){}.getType();
        displayList = gson.fromJson(jsonDisplayList, type);
        String jsonAttendeeList=sharedPreferences.getString("attendeeList","");
        attendeeList = gson.fromJson(jsonAttendeeList,type);
        Intent intent =getIntent();
        Bundle extras=intent.getExtras();
        if (extras == null) {
            isFullMap=true;
        }
        else{
            for(int i=0;i<attendeeList.size();i++){
                if(attendeeList.get(i).getName().equals(extras.getString("name"))){
                    user = attendeeList.get(i);
                }
            }
            isFullMap = false;
        }


        if (displayList==null){
            displayList=attendeeList;
        }



        Button listView = (Button)findViewById(R.id.list_view_button);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MapsActivity.this, ListActivity.class);
                MapsActivity.this.startActivity(myIntent);
            }
        });
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
//        mMap.setMyLocationEnabled(true);
        // Add a marker in SouthHall and move the camera
        mMap.addMarker(new MarkerOptions().position(new LatLng(37.868319, -122.263307)).title("ME").icon(BitmapDescriptorFactory.fromResource(R.drawable.youpin)));
        if(isFullMap){
            for (int i=0;i<displayList.size();i++){
                Person person = displayList.get(i);
                mMap.addMarker(new MarkerOptions().position(person.getLocation()).title(person.getName()));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.868647,-122.262859),17.0f));
        }
        else{
            mMap.addMarker(new MarkerOptions().position(user.getLocation()).title(user.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user.getLocation(),17.0f));
        }

    }
}
