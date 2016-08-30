package com.example.android.quakereport;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.quakereport.json.JSON_Features;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Jacky on 2016/8/23.
 */
public class QuakeFragment extends Fragment {

    public static final String TAG = "QuakeFragment";

    private QuakeActivity _activity;
    private SwipeRefreshLayout swipeContainer;
    private View rootView;

    private  JSON_Features features;
    private Gson gson;
    private QuakeAdapter quakeAdapter;
    private ArrayList<QuakeFlavor> quakeFlavor;
    private String url;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG, TAG + " - Fragment switched");

        rootView = inflater.inflate(R.layout.list_adapter, container, false);

        //init fields
        _activity = (QuakeActivity) getActivity();
        gson = new Gson();
        String result = getArguments().getString("JSON_RESULT");
        setMenuItems(result);

        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                quakeAdapter.clear();
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                QuakeUpdate qu = new QuakeUpdate();
                try {
                    Log.i(TAG, "Refresh page, The URL is " + url);
                    qu.request(_activity, url, new QuakeUpdate.VolleyCallback() {
                        @Override
                        public void onSuccess(String result) throws JSONException {
                            quakeAdapter.clear();
                            setMenuItems(result);
                            swipeContainer.setRefreshing(false);
                            Toast.makeText(_activity, "Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return rootView;
    }

    public void setMenuItems(String result){
        features = gson.fromJson(result, JSON_Features.class);
        quakeFlavor = QuakeUtils.extractEarthquakes(features.getFeatures());
        quakeAdapter = new QuakeAdapter(_activity, quakeFlavor);

        JSON_Features.MetadataBean metadataRecord = features.getMetadata();
        url = metadataRecord.getUrl();

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.quakeList);
        listView.setAdapter(quakeAdapter);
    }
}
