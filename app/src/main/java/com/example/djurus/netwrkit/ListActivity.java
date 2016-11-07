package com.example.djurus.netwrkit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        filteredList = gson.fromJson(jsonDisplayList, type);


        attendeeList.add( new Person("Michaela Reid","Finance","Student", "UC Berkeley","Yoga",37.868647,-122.262859));
        attendeeList.add( new Person("Julie Nottingham","Healthcare","Consultant", "Bain","Climbing",37.868500,-122.262859));
        attendeeList.add( new Person("Chris Hemskin","Technology","Consultant", "Accenture","Gaming",37.868500,-122.262700));
        attendeeList.add( new Person("Patrick Pattington","Education","Designer", "Khan Academy","Fashion",37.868647,-122.262700));
        attendeeList.add( new Person("Lori Rosen","Finance","Student", "UC Berkeley","History",37.868400,-122.262859));
        attendeeList.add( new Person("Ashley Hwang","Healthcare","Engineer", "Prism","Scifi novels",37.868733, -122.263800));
        attendeeList.add( new Person("Philip Visco","Technology","Designer", "Salesforce","Cooking",37.868400,-122.262700));
        attendeeList.add( new Person("Alex Lee","Education","Student", "UC Berkeley","Academia",37.868598, -122.263543));
        attendeeList.add( new Person("Rosa Hernandez","Technology","Engineer", "Apple","Shoes",37.868826, -122.263349));
        attendeeList.add( new Person("Martin Lawrence","Technology","Designer", "Uber","Golfing",37.868758, -122.263596));
        attendeeList.add( new Person("Wilson Wong","Technology","Consultant", "Accenture","Basketball",37.868931, -122.262595));
        attendeeList.add( new Person("Nancy Nguyen","Education","Designer", "Idea","Social Media",37.868711, -122.262280));
        attendeeList.add( new Person("Paul Patinsky","Finance","Engineer", "Uber","Skiing",37.868470, -122.262344));
        attendeeList.add( new Person("Ellen Gorospe","Healthcare","Consultant", "Deloitte","Movies",37.868368, -122.263798));
        attendeeList.add( new Person("Sanjay Gupta","Technology","Student", "UC Berkeley","Rafting",37.868694, -122.262532));
        attendeeList.add( new Person("Madhura Chandra","Education","Engineer", "Google","Singing",37.868732, -122.263205));
        attendeeList.add( new Person("Gunjan Desai","Finance","Consultant", "Bain","Dancing",37.868656, -122.263666));
        attendeeList.add( new Person("Ajay Balu","Healthcare","Designer", "Autodesk","Photography",37.868523, -122.263651));
        attendeeList.add( new Person("Alice LInder","Technology","Engineer", "Google","Music",37.868834, -122.263089));
        attendeeList.add( new Person("Ariana Venti","Education","Student", "UC Berkeley","Cars",37.868529, -122.263218));

        String jsonAttendeeList = new Gson().toJson(attendeeList);
        prefsEditor.putString("attendeeList", jsonAttendeeList);
        prefsEditor.commit();
        if (filteredList==null){
            adapter = new PersonAdapter(getApplicationContext(), attendeeList);
        }
        else{
            adapter = new PersonAdapter(getApplicationContext(), filteredList);
        }
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

            TextView personName = (TextView) personView.findViewById(R.id.personName);
            personName.setText(values.get(position).getName());

            TextView personOccupation= (TextView) personView.findViewById(R.id.personOccupation);
            personOccupation.setText(values.get(position).getOccupation());

            TextView personCompany = (TextView) personView.findViewById(R.id.personCompany);
            personCompany.setText(values.get(position).getCompany());
            TextView personIndustry = (TextView) personView.findViewById(R.id.personIndustry);
            personIndustry.setText(values.get(position).getIndustry());

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
}
