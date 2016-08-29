package com.example.android.quakereport;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Jacky on 2016/8/23.
 */
public class MainMenuFragment extends Fragment implements MainMenuAdapter.MainMenuCallback {

    public static final String TAG = "MainMenuFragment";

    public static final String TAG_PAST_HOUR = "Past Hour";
    public static final String TAG_PAST_DAY = "Past Day";
    public static final String TAG_PAST_WEEK = "Past Week";
    public static final String[] TAG_PAST_Arr = {TAG_PAST_HOUR, TAG_PAST_DAY, TAG_PAST_WEEK};

    private QuakeActivity _activity;
    private ArrayList<QuakeFlavor> quakeFlavor;
    private MainMenuAdapter mainMenuAdapter;
    private int menuItemNum = TAG_PAST_Arr.length;
    private Boolean readyBool = false;

    private ProgressBar progress;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_adapter, container, false);

        Log.v(TAG, TAG + " - Fragment switched");

        //init fields
        _activity = (QuakeActivity) getActivity();
        progress = (ProgressBar) rootView.findViewById(R.id.progress);

        //init UI
        quakeFlavor = new ArrayList<QuakeFlavor>();
        for(int i = 0; i < menuItemNum; i++){
            try {
                String result = getArguments().getString(TAG_PAST_Arr[i]);
                extractJSON(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Callback
        mainMenuAdapter = new MainMenuAdapter(_activity, quakeFlavor);
        mainMenuAdapter.setCallback(this);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.menuList);
        listView.setAdapter(mainMenuAdapter);

        return rootView;
    }

    @Override
    public void onListItemClick(String result) {
        if(result != null){
            Bundle bundle = new Bundle();
            bundle.putString("JSON_RESULT", result);
            _activity.navigateToFragment(QuakeFragment.class, bundle);
        }
    }

    public void extractJSON (String result) throws JSONException {
        JSONObject reader = new JSONObject(result).getJSONObject("metadata");
        long time = 0;
        int count = 0;
        String title = null;

        title = reader.getString("title");
        time = reader.getLong("generated");
        count = reader.getInt("count");

        Log.i(TAG, title + " / " + time + " / " + count);
        quakeFlavor.add(new QuakeFlavor(title, time, count));
    }




}