package com.example.djurus.netwrkit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.plus.internal.model.people.PersonEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    private ArrayList<Person> attendeeList = new ArrayList<Person>();
    private ArrayList<Person> filteredList = new ArrayList<Person>();
    private PersonAdapter adapter;
    private ListView list;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;

    private Button filterCompanyButton;
    private Button filterJobButton;
    private Button filterIndustryButton;
    private Button filterInterestsButton;
    private Button searchJobFilterButton;
    private Button searchIndustryFilterButton;
    private Button searchCompanyFilterButton;
    private Button searchInterestsFilterButton;
    private Button resetFilterButton;

    private RelativeLayout jobCats;
    private RelativeLayout industryCats;
    private ScrollView companyCats;
    private RelativeLayout interestsCats;
    private RelativeLayout filterOptions;
    private CheckBox engineerCheckBox;
    private CheckBox designerCheckBox;
    private CheckBox consultantCheckBox;
    private CheckBox researcherCheckBox;
    private CheckBox financeCheckBox;
    private CheckBox technologyCheckBox;
    private CheckBox educationCheckBox;
    private CheckBox healthcareCheckBox;

    private CheckBox accentureCheckBox;
    private CheckBox ucbCheckBox;
    private CheckBox bainCheckBox;
    private CheckBox khanCheckBox;
    private CheckBox prismCheckBox;
    private CheckBox salesforceCheckBox;
    private CheckBox appleCheckBox;
    private CheckBox uberCheckBox;
    private CheckBox googleCheckBox;


    private CheckBox yogaCheckBox;
    private CheckBox climbingCheckBox;
    private CheckBox gamingCheckBox;
    private CheckBox fashionCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button mapView = (Button)findViewById(R.id.map_view_button);
        Button filterButton = (Button) findViewById(R.id.filterbutton);
        filterCompanyButton = (Button) findViewById(R.id.filterCompanyButton);
        filterJobButton = (Button) findViewById(R.id.filterJobButton);
        filterIndustryButton = (Button) findViewById(R.id.filterIndustryButton);
        filterInterestsButton = (Button) findViewById(R.id.filterInterestsButton);
        searchJobFilterButton= (Button) findViewById(R.id.searchJobFilterButton);
        searchIndustryFilterButton = (Button) findViewById(R.id.searchIndustryFilterButton);
        searchInterestsFilterButton = (Button) findViewById(R.id.searchInterestsFilterButton);
        searchCompanyFilterButton = (Button) findViewById(R.id.searchCompanyFilterButton);
        resetFilterButton = (Button) findViewById(R.id.resetFilterButton);
        filterOptions=(RelativeLayout) findViewById(R.id.filterOptions);
        jobCats = (RelativeLayout) findViewById(R.id.jobCats);
        industryCats = (RelativeLayout) findViewById(R.id.industryCats);
        companyCats = (ScrollView) findViewById(R.id.companyCats);
        interestsCats = (RelativeLayout) findViewById(R.id.interestsCats);
        engineerCheckBox = (CheckBox)findViewById(R.id.engineerCheckBox);
        designerCheckBox = (CheckBox)findViewById(R.id.designerCheckBox);
        consultantCheckBox = (CheckBox)findViewById(R.id.consultantCheckBox);
        researcherCheckBox = (CheckBox)findViewById(R.id.researcherCheckBox);
        financeCheckBox = (CheckBox)findViewById(R.id.financeCheckBox);
        technologyCheckBox = (CheckBox)findViewById(R.id.technologyCheckBox);
        educationCheckBox = (CheckBox)findViewById(R.id.educationCheckBox);
        healthcareCheckBox = (CheckBox)findViewById(R.id.healthcareCheckBox);
        accentureCheckBox = (CheckBox)findViewById(R.id.accentureCheckBox);
        ucbCheckBox = (CheckBox)findViewById(R.id.ucbCheckBox);
        bainCheckBox = (CheckBox)findViewById(R.id.bainCheckBox);
        khanCheckBox = (CheckBox)findViewById(R.id.khanCheckBox);
        prismCheckBox = (CheckBox)findViewById(R.id.prismCheckBox);
        salesforceCheckBox = (CheckBox)findViewById(R.id.salesforceCheckBox);
        appleCheckBox = (CheckBox)findViewById(R.id.appleCheckBox);
        uberCheckBox = (CheckBox)findViewById(R.id.uberCheckBox);
        googleCheckBox = (CheckBox)findViewById(R.id.googleCheckBox);

        yogaCheckBox = (CheckBox)findViewById(R.id.yogaCheckBox);
        climbingCheckBox = (CheckBox)findViewById(R.id.climbingCheckBox);
        gamingCheckBox = (CheckBox)findViewById(R.id.gamingCheckBox);
        fashionCheckBox = (CheckBox)findViewById(R.id.fashionCheckBox);



        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        prefsEditor = sharedPreferences.edit();
        String jsonDisplayList=sharedPreferences.getString("displayList","");
        Gson gson=new Gson();
        Type type = new TypeToken<ArrayList<Person>>(){}.getType();
        if (jsonDisplayList.equals("")){
            filteredList = new ArrayList<>();
        }
        else{
            filteredList = gson.fromJson(jsonDisplayList, type);
        }






        String jsonAttendeeList=sharedPreferences.getString("attendeeList","");
        attendeeList = gson.fromJson(jsonAttendeeList,type);

        updateFilteredList();
        prefsEditor.putString("attendeeList", jsonAttendeeList);
        prefsEditor.commit();

        if (filteredList.size()==0){
            adapter = new PersonAdapter(getApplicationContext(), attendeeList);
            filteredList=attendeeList;
        }
        else{
            adapter = new PersonAdapter(getApplicationContext(), filteredList);
        }
        list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(ListActivity.this, ProfileActivity.class);
                String name = filteredList.get(position).getName();
                myIntent.putExtra("name", name);
                ListActivity.this.startActivity(myIntent);
            }
        });


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
                filterOptions.setVisibility(View.VISIBLE);
            }
        });
        filterJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterOptions.setVisibility(View.GONE);
                jobCats.setVisibility(View.VISIBLE);
            }
        });

        filterIndustryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterOptions.setVisibility(View.GONE);
                industryCats.setVisibility(View.VISIBLE);
            }
        });
        filterCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterOptions.setVisibility(View.GONE);
                companyCats.setVisibility(View.VISIBLE);
            }
        });
        filterInterestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterOptions.setVisibility(View.GONE);
                interestsCats.setVisibility(View.VISIBLE);
            }
        });

        searchJobFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredList= new ArrayList<Person>();
                filteredList=filterJob();
                String jsonFilteredList = new Gson().toJson(filteredList);
                prefsEditor.putString("displayList", jsonFilteredList);
                prefsEditor.commit();
                adapter = new PersonAdapter(getApplicationContext(), filteredList);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
                jobCats.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
            }
        });
        searchIndustryFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredList= new ArrayList<Person>();
                filteredList=filterIndustry();
                String jsonFilteredList = new Gson().toJson(filteredList);
                prefsEditor.putString("displayList", jsonFilteredList);
                prefsEditor.commit();
                adapter = new PersonAdapter(getApplicationContext(), filteredList);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
                industryCats.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
            }
        });
        searchCompanyFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredList= new ArrayList<Person>();
                filteredList=filterCompany();
                String jsonFilteredList = new Gson().toJson(filteredList);
                prefsEditor.putString("displayList", jsonFilteredList);
                prefsEditor.commit();
                adapter = new PersonAdapter(getApplicationContext(), filteredList);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
                companyCats.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
            }
        });
        searchInterestsFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredList= new ArrayList<Person>();
                filteredList=filterInterests();
                String jsonFilteredList = new Gson().toJson(filteredList);
                prefsEditor.putString("displayList", jsonFilteredList);
                prefsEditor.commit();
                adapter = new PersonAdapter(getApplicationContext(), filteredList);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
                interestsCats.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
            }
        });
        resetFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredList=attendeeList;
                String jsonFilteredList = new Gson().toJson(filteredList);
                prefsEditor.putString("displayList", jsonFilteredList);
                prefsEditor.commit();
                adapter = new PersonAdapter(getApplicationContext(), filteredList);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
                filterOptions.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
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

            final Person person=values.get(position);

            TextView personName = (TextView) personView.findViewById(R.id.personName);
            personName.setText(person.getName());

            TextView personOccupation= (TextView) personView.findViewById(R.id.personOccupation);
            personOccupation.setText(person.getOccupation());

            TextView personCompany = (TextView) personView.findViewById(R.id.personCompany);
            personCompany.setText(person.getCompany());
            TextView personIndustry = (TextView) personView.findViewById(R.id.personIndustry);
            personIndustry.setText(person.getIndustry());

            ImageView pp = (ImageView) personView.findViewById(R.id.pp);
            if(person.getImgSrc()!=null){
                int resID = getResources().getIdentifier(person.getImgSrc(),"drawable",getPackageName());
                pp.setImageResource(resID);
            }


            final ImageView star = (ImageView) personView.findViewById(R.id.star);
            if (!person.isStarred()){
                star.setImageResource(R.drawable.starunfilled);
            }
            else{
                star.setImageResource(R.drawable.starfilled);
            }

            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = person.getName();
                    for(int i=0;i<attendeeList.size();i++){
                        if(attendeeList.get(i).getName().equals(name)){
                            if (!attendeeList.get(i).isStarred()){
                                star.setImageResource(R.drawable.starfilled);
                                attendeeList.get(i).setStar(true);
                            }
                            else{
                                star.setImageResource(R.drawable.starunfilled);
                                attendeeList.get(i).setStar(false);
                            }
                        }
                    }
                    for(int i=0;i<attendeeList.size();i++){
                        Log.d("bool",attendeeList.get(i).getName()+String.valueOf(attendeeList.get(i).isStarred()));

                    }
                    String jsonAttendeeList = new Gson().toJson(attendeeList);
                    prefsEditor.putString("attendeeList", jsonAttendeeList);
                    prefsEditor.commit();
                }
            });

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
    public ArrayList<Person> filterJob(){
        ArrayList<Person> result = new ArrayList<>();
        boolean engineerCheck= engineerCheckBox.isChecked();
        boolean designerCheck= designerCheckBox.isChecked();
        boolean consultantCheck= consultantCheckBox.isChecked();
        boolean researcherCheck= researcherCheckBox.isChecked();
        for (int i=0;i<attendeeList.size();i++){
            Person person =attendeeList.get(i);
            if (engineerCheck){
                if (person.getOccupation().equals("Engineer")){
                    result.add(person);
                }
            }
            if (designerCheck){
                if (person.getOccupation().equals("Designer")){
                    result.add(person);
                }
            }
            if (consultantCheck){
                if (person.getOccupation().equals("Consultant")){
                    result.add(person);
                }
            }
            if (researcherCheck){
                if (person.getOccupation().equals("Researcher")){
                    result.add(person);
                }
            }



        }
        return result;
    }
    public ArrayList<Person> filterIndustry(){
        ArrayList<Person> result = new ArrayList<>();
        boolean financeCheck= financeCheckBox.isChecked();
        boolean technologyCheck= technologyCheckBox.isChecked();
        boolean educationCheck= educationCheckBox.isChecked();
        boolean healthcareCheck= healthcareCheckBox.isChecked();
        for (int i=0;i<attendeeList.size();i++){
            Person person =attendeeList.get(i);
            if (financeCheck){
                if (person.getIndustry().equals("Finance")){
                    result.add(person);
                }
            }
            if (technologyCheck){
                if (person.getIndustry().equals("Technology")){
                    result.add(person);
                }
            }
            if (educationCheck){
                if (person.getIndustry().equals("Education")){
                    result.add(person);
                }
            }
            if (healthcareCheck){
                if (person.getIndustry().equals("Healthcare")){
                    result.add(person);
                }
            }



        }
        return result;
    }
    public ArrayList<Person> filterCompany(){
        ArrayList<Person> result = new ArrayList<>();
        boolean accentureCheck= accentureCheckBox.isChecked();
        boolean ucbCheck= ucbCheckBox.isChecked();
        boolean bainCheck= bainCheckBox.isChecked();
        boolean khanCheck= khanCheckBox.isChecked();
        boolean prismCheck= prismCheckBox.isChecked();
        boolean salesforceCheck = salesforceCheckBox.isChecked();
        boolean appleCheck = appleCheckBox.isChecked();
        boolean uberCheck = uberCheckBox.isChecked();
        boolean googleCheck = googleCheckBox.isChecked();
        for (int i=0;i<attendeeList.size();i++){
            Person person =attendeeList.get(i);
            if (accentureCheck){
                if (person.getCompany().equals("Accenture")){
                    result.add(person);
                }
            }
            if (ucbCheck){
                if (person.getCompany().equals("UC Berkeley")){
                    result.add(person);
                }
            }
            if (bainCheck){
                if (person.getCompany().equals("Bain")){
                    result.add(person);
                }
            }
            if (khanCheck){
                if (person.getCompany().equals("Khan Academy")){
                    result.add(person);
                }
            }
            if (prismCheck){
                if (person.getCompany().equals("Prism")){
                    result.add(person);
                }
            }
            if (salesforceCheck){
                if (person.getCompany().equals("Salesforce")){
                    result.add(person);
                }
            }
            if (appleCheck){
                if (person.getCompany().equals("Apple")){
                    result.add(person);
                }
            }
            if (uberCheck){
                if (person.getCompany().equals("Uber")){
                    result.add(person);
                }
            }
            if (googleCheck){
                if (person.getCompany().equals("Google")){
                    result.add(person);
                }
            }
        }
        return result;
    }
    public ArrayList<Person> filterInterests(){
        ArrayList<Person> result = new ArrayList<>();
        boolean yogaCheck= yogaCheckBox.isChecked();
        boolean climbingCheck= climbingCheckBox.isChecked();
        boolean gamingCheck= gamingCheckBox.isChecked();
        boolean fashionCheck= fashionCheckBox.isChecked();
        for (int i=0;i<attendeeList.size();i++){
            Person person =attendeeList.get(i);
            if (yogaCheck){
                if (person.getInterests().equals("Yoga")){
                    result.add(person);
                }
            }
            if (climbingCheck){
                if (person.getInterests().equals("Climbing")){
                    result.add(person);
                }
            }
            if (gamingCheck){
                if (person.getInterests().equals("Gaming")){
                    result.add(person);
                }
            }
            if (fashionCheck){
                if (person.getInterests().equals("Fashion")){
                    result.add(person);
                }
            }
        }
        return result;
    }
    public Person matchName(String name){
        for(int i=0;i<attendeeList.size();i++){
            if(attendeeList.get(i).getName().equals(name)){
                return attendeeList.get(i);
            }
        }
        return null;
    }
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        Gson gson=new Gson();
        Type type = new TypeToken<ArrayList<Person>>(){}.getType();
        String jsonAttendeeList=sharedPreferences.getString("attendeeList","");
        attendeeList = gson.fromJson(jsonAttendeeList,type);
        updateFilteredList();
        adapter.notifyDataSetChanged();
        for(int i=0;i<attendeeList.size();i++){
            Log.d("Attendeelist",attendeeList.get(i).getName()+String.valueOf(attendeeList.get(i).isStarred()));

        }
        for(int i=0;i<filteredList.size();i++){
            Log.d("filteredlist",filteredList.get(i).getName()+String.valueOf(filteredList.get(i).isStarred()));

        }
    }
    public void updateFilteredList(){
        for(int i=0;i<filteredList.size();i++){
            for(int j=0;j<attendeeList.size();j++){
                if(filteredList.get(i).getName().equals(attendeeList.get(j).getName())){
                    filteredList.get(i).setStar(attendeeList.get(j).isStarred());
                }
            }
        }
    }
}
