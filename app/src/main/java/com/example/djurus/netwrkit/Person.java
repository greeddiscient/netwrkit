package com.example.djurus.netwrkit;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by djurus on 11/3/16.
 */

public class Person {
    private String industry;
    private String occupation;
    private String company;
    private String name;
    private String interests;
    public Person(String name, String industry,String occupation,String company, String interests){
        this.name = name;
        this.industry=industry;
        this.occupation=occupation;
        this.company=company;
        this.interests=interests;
    }
    public String getName(){
        return name;
    }
    public String getIndustry(){
        return industry;
    }
    public String getOccupation(){
        return occupation;
    }
    public String getCompany(){
        return company;
    }
    public String getInterests(){
        return interests;
    }
}