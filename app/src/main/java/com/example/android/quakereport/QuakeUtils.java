package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jacky on 2016/8/22.
 */
public class QuakeUtils {

    public static final String TAG = "QuakeUtils";

    private QuakeUtils() { }

    public static ArrayList<QuakeFlavor> extractEarthquakes(String result) {

        ArrayList<QuakeFlavor> quakeFlavor = new ArrayList<>();

        try{

            JSONObject root = new JSONObject(result);
            JSONArray featuresArr = root.getJSONArray("features");

            int length = featuresArr.length();
            Log.i(TAG, String.valueOf("Earthquake record received: " + length));
            if(length >= 100){ length = 100; }

            for (int i=0; i<length; i++){

                JSONObject featureObj = featuresArr.getJSONObject(i);
                JSONObject propertiesObj = featureObj.getJSONObject("properties");

                //Magnitude attribute
                Double magnitude = propertiesObj.getDouble("mag");

                //Place attribute
                String place = propertiesObj.getString("place");

                //Time attribute
                long time = propertiesObj.getLong("time");

                //URL attribute
                String url = propertiesObj.getString("url");

                Log.i(TAG, "(" + i + ") " + String.valueOf(magnitude) + " / " + place + " / " + String.valueOf(time) + " / " + url);
                quakeFlavor.add(new QuakeFlavor(magnitude, place, time, url));
            }

        } catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return quakeFlavor;
    }



}
