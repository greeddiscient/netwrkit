package com.example.djurus.netwrkit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private TextView userName;
    private TextView userCompany;
    private TextView userPosition;
    private TextView userSkills;
    private TextView userFunFact;
    private Button viewOnMap;

    private ArrayList<Person> attendeeList;
    private Person user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userName = (TextView) findViewById(R.id.userName);
        userCompany = (TextView) findViewById(R.id.userCompany);
        userPosition = (TextView) findViewById(R.id.userPosition);
        userSkills= (TextView) findViewById(R.id.userSkills);
        userFunFact = (TextView) findViewById(R.id.userFunFact);
        viewOnMap = (Button) findViewById(R.id.viewOnMap);

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson=new Gson();
        Type type = new TypeToken<ArrayList<Person>>(){}.getType();
        String jsonAttendeeList=sharedPreferences.getString("attendeeList","");
        attendeeList = gson.fromJson(jsonAttendeeList,type);
        Intent intent =getIntent();
        Bundle extras=intent.getExtras();
        String name = extras.getString("name");
        Log.d("name",name);
        for(int i=0;i<attendeeList.size();i++){
            if(attendeeList.get(i).getName().equals(name)){
                user = attendeeList.get(i);
            }
        }
        userName.setText(user.getName());
        userCompany.setText(user.getCompany());
        userPosition.setText(user.getOccupation());

        viewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ProfileActivity.this, MapsActivity.class);
                String name = user.getName();
                myIntent.putExtra("name", name);
                ProfileActivity.this.startActivity(myIntent);
            }
        });

    }
}
