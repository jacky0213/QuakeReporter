package com.example.android.quakereport;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.quakereport.json.JSON_Features;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Jacky on 2016/8/23.
 */
public class QuakeFragment extends Fragment{

    public static final String TAG = "QuakeFragment";
    QuakeActivity _activity;
    JSON_Features features;
    Gson gson;
    QuakeAdapter quakeAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_adapter, container, false);
        Log.v(TAG, TAG + " - Fragment switched");

        //init fields
        _activity = (QuakeActivity) getActivity();


        String result = getArguments().getString("JSON_RESULT");
        gson = new Gson();
        features = gson.fromJson(result, JSON_Features.class);

        ArrayList<QuakeFlavor> quakeFlavor = QuakeUtils.extractEarthquakes(features.getFeatures());
        quakeAdapter = new QuakeAdapter(_activity, quakeFlavor);


        Log.v(TAG, getArguments().getString("JSON_RESULT"));

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.quakeList);
        listView.setAdapter(quakeAdapter);

        return rootView;
    }
}
