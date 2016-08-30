package com.example.android.quakereport;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.android.quakereport.json.JSON_Features;
import com.google.gson.Gson;

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
    private View rootView;

    private JSON_Features features;
    private Gson gson;
    private ArrayList<QuakeFlavor> quakeFlavor;
    private MainMenuAdapter mainMenuAdapter;
    private int menuItemNum = TAG_PAST_Arr.length;

    private ProgressBar progress;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.menu_adapter, container, false);

        Log.v(TAG, TAG + " - Fragment switched");

        //init fields
        _activity = (QuakeActivity) getActivity();
        progress = (ProgressBar) rootView.findViewById(R.id.progress);

        //Extract JSON result from GSON
        gson = new Gson();
        //Retrieve result from bundle
        quakeFlavor = new ArrayList<QuakeFlavor>();
        for(int i = 0; i < menuItemNum; i++){
            String result = getArguments().getString(TAG_PAST_Arr[i]);
            setMenuItems(result);
        }
        return rootView;
    }

    public void setMenuItems(String result){
        features = gson.fromJson(result, JSON_Features.class);
        JSON_Features.MetadataBean metadataRecord = features.getMetadata();
        String title = metadataRecord.getTitle();
        long time = Long.parseLong(metadataRecord.getGenerated());
        int count = Integer.parseInt(metadataRecord.getCount());
        String url = metadataRecord.getUrl();
        Log.i(TAG, title + " / " + time + " / " + count + " / " + url);
        quakeFlavor.add(new QuakeFlavor(title, time, count, url));

        //Callback
        mainMenuAdapter = new MainMenuAdapter(_activity, quakeFlavor);
        mainMenuAdapter.setCallback(this);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.menuList);
        listView.setAdapter(mainMenuAdapter);

    }

    @Override
    public void onListItemClick(String result) {
        if(result != null){
            Bundle bundle = new Bundle();
            bundle.putString("JSON_RESULT", result);
            _activity.navigateToFragment(QuakeFragment.class, bundle, false);
        }
    }
}