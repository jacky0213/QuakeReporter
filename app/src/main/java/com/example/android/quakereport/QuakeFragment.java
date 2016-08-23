package com.example.android.quakereport;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jacky on 2016/8/23.
 */
public class QuakeFragment extends Fragment{

    public static final String TAG = "QuakeFragment";
    QuakeActivity _activity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_adapter, container, false);
        Log.v(TAG, TAG + " - Fragment switched");

        //init fields
        _activity = (QuakeActivity) getActivity();

        //init UI
        ArrayList<QuakeFlavor> quakeFlavor = QuakeUtils.extractEarthquakes(getArguments().getString("JSON_RESULT"));
        QuakeAdapter quakeAdapter = new QuakeAdapter(_activity, quakeFlavor);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.quakeList);
        listView.setAdapter(quakeAdapter);

        return rootView;
    }
}
