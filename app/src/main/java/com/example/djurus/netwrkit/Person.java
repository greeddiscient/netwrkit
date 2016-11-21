package com.example.djurus.netwrkit;

import com.google.android.gms.maps.model.LatLng;

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
    private LatLng location;
    private String imgSrc;
    private String skills;
    private boolean starred=false;
    public Person(String name, String industry,String occupation,String company, String interests, double lat, double lng){
        this.name = name;
        this.industry=industry;
        this.occupation=occupation;
        this.company=company;
        this.interests=interests;
        this.location = new LatLng(lat,lng);
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
    public LatLng getLocation(){
        return location;
    }
    public boolean isStarred(){
        return starred;
    }
    public void setStar(boolean b){
        starred = b;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSkills() {
        return skills;
    }
}