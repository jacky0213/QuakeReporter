package com.example.android.quakereport;

/**
 * Created by Jacky on 2016/8/22.
 */
public class QuakeFlavor {

    private double magnitude;
    private String location;
    private long date;
    private String url;
    private String topic;
    private int recordNum;

    //For main menu list view
    public QuakeFlavor(String topic, long date, int recordNum){
        this.topic = topic;
        this.date = date;
        this.recordNum = recordNum;
    }

    //For json result content
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

    public String getTopic(){ return topic; }

    public int getRecordNum(){ return recordNum; }

}
