package com.example.android.quakereport;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jacky on 2016/8/23.
 */
public class MainMenuFragment extends Fragment implements MainMenuAdapter.MainMenuCallback {

    public static final String TAG = "MainMenuFragment";

    public static final String TAG_PAST_HOUR = "Past Hour";
    public static final String TAG_PAST_DAY = "Past Day";
    public static final String TAG_PAST_WEEK = "Past Week";
    public static final String TAG_PAST_MONTH = "Past Month";

    QuakeActivity _activity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_adapter, container, false);

        Log.v(TAG, TAG + " - Fragment switched");

        //init fields
        _activity = (QuakeActivity) getActivity();

        //init UI
        ArrayList<QuakeFlavor> quakeFlavor = new ArrayList<QuakeFlavor>();
        quakeFlavor.add(new QuakeFlavor(TAG_PAST_HOUR));
        quakeFlavor.add(new QuakeFlavor(TAG_PAST_DAY));
        quakeFlavor.add(new QuakeFlavor(TAG_PAST_WEEK));
        quakeFlavor.add(new QuakeFlavor(TAG_PAST_MONTH));
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(_activity, quakeFlavor);
        mainMenuAdapter.setCallback(this);
        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.quakeList);
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
}

/*Bundle bundle = new Bundle();
        bundle.putString("JSON_RESULT", result);
        _activity.navigateToFragment(QuakeFragment.class, bundle);*/
