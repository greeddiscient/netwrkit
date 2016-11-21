package com.example.djurus.netwrkit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;
    private ArrayList<Person> attendeeList = new ArrayList<Person>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        attendeeList.add( new Person("Chris Hemskin","Technology","Consultant", "Accenture","Gaming",37.868500,-122.262700));
        attendeeList.add( new Person("Patrick Pattington","Education","Designer", "Khan Academy","Fashion",37.868647,-122.262700));
        attendeeList.add( new Person("Alven Wang","Technology","Student", "UC Berkeley","Product Design",37.868400,-122.262859));
        attendeeList.add( new Person("Ashley Hwang","Healthcare","Engineer", "Prism","Scifi novels",37.868733, -122.263800));
        attendeeList.add( new Person("Philip Visco","Technology","Designer", "Salesforce","Cooking",37.868400,-122.262700));
        attendeeList.add( new Person("Alex Lee","Education","Student", "UC Berkeley","Academia",37.868598, -122.263543));
        attendeeList.add( new Person("Rosa Hernandez","Technology","Engineer", "Apple","Shoes",37.868826, -122.263349));
        attendeeList.add( new Person("Martin Lawrence","Technology","Designer", "Uber","Golfing",37.868758, -122.263596));
        attendeeList.add( new Person("Julie No","Technology","Designer", "Instabook","Yoga",37.868647,-122.262859));
        attendeeList.get(attendeeList.size()-1).setImgSrc("julie");
        attendeeList.add( new Person("Astika Gupta","Technology","Engineer", "Instabook","Climbing",37.868500,-122.262859));
        attendeeList.get(attendeeList.size()-1).setImgSrc("astika");
        attendeeList.add( new Person("Wilson Wong","Technology","Consultant", "Accenture","Basketball",37.868931, -122.262595));
        attendeeList.add( new Person("Nancy Nguyen","Education","Designer", "IDEO","Social Media",37.868711, -122.262280));
        attendeeList.add( new Person("Paul Patinsky","Finance","Engineer", "Uber","Skiing",37.868470, -122.262344));
        attendeeList.add( new Person("Ellen Gorospe","Healthcare","Consultant", "Deloitte","Movies",37.868368, -122.263798));
        attendeeList.add( new Person("Sanjay Gupta","Technology","Student", "UC Berkeley","Rafting",37.868694, -122.262532));
        attendeeList.add( new Person("Madhura Chandra","Education","Engineer", "Google","Singing",37.868732, -122.263205));
        attendeeList.add( new Person("Gunjan Desai","Finance","Consultant", "Bain","Dancing",37.868656, -122.263666));
        attendeeList.add( new Person("Ajay Balu","Healthcare","Designer", "Autodesk","Photography",37.868523, -122.263651));
        attendeeList.add( new Person("Alice LInder","Technology","Engineer", "Google","Music",37.868834, -122.263089));
        attendeeList.add( new Person("Ariana Venti","Education","Student", "UC Berkeley","Cars",37.868529, -122.263218));

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        prefsEditor = sharedPreferences.edit();
        String jsonAttendeeList = new Gson().toJson(attendeeList);
        prefsEditor.putString("attendeeList", jsonAttendeeList);
        prefsEditor.commit();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        }, 1500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), ListActivity.class));
            }
        }, 1500);
    }
}
