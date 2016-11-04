package com.example.djurus.netwrkit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    private ArrayList<Person> attendeeList = new ArrayList<Person>();
    private ArrayList<Person> filteredList = new ArrayList<Person>();
    private PersonAdapter adapter;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Button mapView = (Button)findViewById(R.id.map_view_button);
        Button filterButton = (Button) findViewById(R.id.filterbutton);





        attendeeList.add( new Person("Michaela Reid","Finance","Student", "UC Berkeley","Yoga"));
        attendeeList.add( new Person("Julie Nottingham","Healthcare","Consultant", "Bain","Climbing"));
        attendeeList.add( new Person("Chris Hemskin","Technology","Consultant", "Accenture","Gaming"));
        attendeeList.add( new Person("Patrick Pattington","Education","Designer", "Khan Academy","Fashion"));
        attendeeList.add( new Person("Lori Rosen","Finance","Student", "UC Berkeley","History"));

        adapter = new PersonAdapter(getApplicationContext(), attendeeList);
        list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent myIntent = new Intent(MainActivity.this, QuestDetailsActivity.class);
//                myIntent.putExtra("questnumber", position);
//                ListActivity.this.startActivity(myIntent);
//            }
//        });


        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(ListActivity.this, MapsActivity.class);
                ListActivity.this.startActivity(myIntent);
            }
        });
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredList=filter("Finance");
                adapter = new PersonAdapter(getApplicationContext(), filteredList);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
            }
        });

    }

    public class PersonAdapter extends ArrayAdapter<Person> {
        private final ArrayList<Person> values;
        private final Context context;
        public PersonAdapter(Context context, ArrayList<Person> values) {
            super(context, R.layout.personview, values);
            this.values=values;
            this.context=context;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View personView = inflater.inflate(R.layout.personview, parent, false);

            TextView personName = (TextView) personView.findViewById(R.id.personName);
            personName.setText(values.get(position).getName());

            TextView personOccupation= (TextView) personView.findViewById(R.id.personOccupation);
            personOccupation.setText(values.get(position).getOccupation());

            TextView personCompany = (TextView) personView.findViewById(R.id.personCompany);
            personCompany.setText(values.get(position).getCompany());

            return personView;
        }
    }
    public ArrayList<Person> filter(String criteria){
        ArrayList<Person> result = new ArrayList<>();
        for (int i=0;i<attendeeList.size();i++){
            Person person =attendeeList.get(i);
            if (person.getIndustry().equals(criteria)){
                result.add(person);
            }
        }
        return result;
    }
}
