package com.example.android.quakereport;

import android.util.Log;

import com.example.android.quakereport.json.JSON_Features;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jacky on 2016/8/22.
 */
public class QuakeUtils{

    public static final String TAG = "QuakeUtils";

    public QuakeUtils() { }

    public static ArrayList<QuakeFlavor> extractEarthquakes(final List<JSON_Features.FeaturesBean> featureRecord) {

        final ArrayList<QuakeFlavor> quakeFlavor = new ArrayList<>();

        int size = featureRecord.size();
        if(size >= 100){
            size = 100;
        }
        for(int i = 0; i < size; i++){
            JSON_Features.FeaturesBean item = featureRecord.get(i);
            double mag = item.getProperties().getMag();
            String place = item.getProperties().getPlace();
            long time = item.getProperties().getTime();
            String url = item.getProperties().getUrl();
            Log.i(TAG, "(" + i + ") " + String.valueOf(mag) + " / " + place + " / " + String.valueOf(time) + " / " + url);
            quakeFlavor.add(new QuakeFlavor(mag, place, time, url));
        }

            /*
             quakeFlavor.add(new QuakeFlavor(magnitude, place, time, url));
            Log.i(TAG, "(" +""+ ") " + String.valueOf(magnitude) + " / " + place + " / " + String.valueOf(time) + " / " + url);
            /*
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
            */

        return quakeFlavor;
    }


}
