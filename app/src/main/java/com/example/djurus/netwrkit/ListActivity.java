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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    private ArrayList<Person> attendeeList = new ArrayList<Person>();
    private ArrayList<Person> filteredList = new ArrayList<Person>();
    private PersonAdapter adapter;
    private ListView list;
    private Button filterCompanyButton;
    private Button filterJobButton;
    private Button filterIndustryButton;
    private Button filterInterestsButton;
    private Button searchJobFilterButton;
    private Button searchIndustryFilterButton;
    private Button searchCompanyFilterButton;
    private Button searchInterestsFilterButton;

    private RelativeLayout jobCats;
    private RelativeLayout industryCats;
    private RelativeLayout companyCats;
    private RelativeLayout interestsCats;
    private RelativeLayout filterOptions;
    private CheckBox engineerCheckBox;
    private CheckBox designerCheckBox;
    private CheckBox pmCheckBox;
    private CheckBox researcherCheckBox;
    private CheckBox financeCheckBox;
    private CheckBox technologyCheckBox;
    private CheckBox educationCheckBox;
    private CheckBox healthcareCheckBox;
    private CheckBox accentureCheckBox;
    private CheckBox ucbCheckBox;
    private CheckBox bainCheckBox;
    private CheckBox khanCheckBox;
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
        filterOptions=(RelativeLayout) findViewById(R.id.filterOptions);
        jobCats = (RelativeLayout) findViewById(R.id.jobCats);
        industryCats = (RelativeLayout) findViewById(R.id.industryCats);
        companyCats = (RelativeLayout) findViewById(R.id.companyCats);
        interestsCats = (RelativeLayout) findViewById(R.id.interestsCats);
        engineerCheckBox = (CheckBox)findViewById(R.id.engineerCheckBox);
        designerCheckBox = (CheckBox)findViewById(R.id.designerCheckBox);
        pmCheckBox = (CheckBox)findViewById(R.id.pmCheckBox);
        researcherCheckBox = (CheckBox)findViewById(R.id.researcherCheckBox);
        financeCheckBox = (CheckBox)findViewById(R.id.financeCheckBox);
        technologyCheckBox = (CheckBox)findViewById(R.id.technologyCheckBox);
        educationCheckBox = (CheckBox)findViewById(R.id.educationCheckBox);
        healthcareCheckBox = (CheckBox)findViewById(R.id.healthcareCheckBox);
        accentureCheckBox = (CheckBox)findViewById(R.id.accentureCheckBox);
        ucbCheckBox = (CheckBox)findViewById(R.id.ucbCheckBox);
        bainCheckBox = (CheckBox)findViewById(R.id.bainCheckBox);
        khanCheckBox = (CheckBox)findViewById(R.id.khanCheckBox);
        yogaCheckBox = (CheckBox)findViewById(R.id.yogaCheckBox);
        climbingCheckBox = (CheckBox)findViewById(R.id.climbingCheckBox);
        gamingCheckBox = (CheckBox)findViewById(R.id.gamingCheckBox);
        fashionCheckBox = (CheckBox)findViewById(R.id.fashionCheckBox);


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
                adapter = new PersonAdapter(getApplicationContext(), filteredList);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
                interestsCats.setVisibility(View.GONE);
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
        boolean pmCheck= pmCheckBox.isChecked();
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
            if (pmCheck){
                if (person.getOccupation().equals("Product Manager")){
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
