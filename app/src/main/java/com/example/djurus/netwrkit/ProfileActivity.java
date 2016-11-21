package com.example.djurus.netwrkit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private Button bookmarkButton;
    private Button unbookmarkButton;
    private int index;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;

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
        bookmarkButton = (Button) findViewById(R.id.bookmarkButton);
        unbookmarkButton = (Button) findViewById(R.id.unbookmarkButton);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        prefsEditor = sharedPreferences.edit();
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
                index=i;
                user = attendeeList.get(i);
            }
        }
        userName.setText(user.getName());
        userCompany.setText(user.getCompany());
        userPosition.setText(user.getOccupation());
        if(user.isStarred()){
            unbookmarkButton.setVisibility(View.VISIBLE);
            bookmarkButton.setVisibility(View.GONE);
        }
        else{
            bookmarkButton.setVisibility(View.VISIBLE);
            unbookmarkButton.setVisibility(View.GONE);
        }

        ImageView pp = (ImageView) findViewById(R.id.profilePicture);
        if(user.getImgSrc()!=null){
            int resID = getResources().getIdentifier(user.getImgSrc(),"drawable",getPackageName());
            pp.setImageResource(resID);
        }


        viewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ProfileActivity.this, MapsActivity.class);
                String name = user.getName();
                myIntent.putExtra("name", name);
                ProfileActivity.this.startActivity(myIntent);
            }
        });
        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendeeList.get(index).setStar(true);
                bookmarkButton.setVisibility(View.GONE);
                unbookmarkButton.setVisibility(View.VISIBLE);
                String jsonAttendeeList = new Gson().toJson(attendeeList);
                prefsEditor.putString("attendeeList", jsonAttendeeList);
                prefsEditor.commit();
            }
        });
        unbookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendeeList.get(index).setStar(false);
                bookmarkButton.setVisibility(View.VISIBLE);
                unbookmarkButton.setVisibility(View.GONE);
                String jsonAttendeeList = new Gson().toJson(attendeeList);
                prefsEditor.putString("attendeeList", jsonAttendeeList);
                prefsEditor.commit();
            }
        });
    }
}
