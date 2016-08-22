package com.example.android.quakereport;

/**
 * Created by Jacky on 2016/8/22.
 */
public class QuakeFlavor {

    private double magnitude;
    private String location;
    private long date;
    private String url;

    public QuakeFlavor(double magnitude, String location, long date, String url){
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
        this.url = url;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public String getLocation(){
        return location;
    }

    public long getDate(){
        return date;
    }

    public String getURL(){
        return url;
    }

}
