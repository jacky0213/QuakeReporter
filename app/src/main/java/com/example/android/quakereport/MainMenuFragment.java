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

    public static final String URL_PAST_HOUR = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";
    public static final String URL_PAST_DAY = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";
    public static final String URL_PAST_WEEK = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson";
    public static final String URL_PAST_MONTH = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";

    QuakeActivity _activity;
    ArrayList<QuakeFlavor> quakeFlavor;
    MainMenuAdapter mainMenuAdapter;
    String[] topic = {URL_PAST_HOUR, URL_PAST_DAY, URL_PAST_WEEK, URL_PAST_MONTH};
    int counter = topic.length;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_adapter, container, false);

        Log.v(TAG, TAG + " - Fragment switched");

        //init fields
        _activity = (QuakeActivity) getActivity();

        //init UI
        quakeFlavor = new ArrayList<QuakeFlavor>();
        quakeFlavor.add(new QuakeFlavor(", " + TAG_PAST_HOUR, 1472035185380L , 6));
        quakeFlavor.add(new QuakeFlavor(", " + TAG_PAST_DAY, 1472037876000L , 199));
        quakeFlavor.add(new QuakeFlavor(", " + TAG_PAST_WEEK, 1472037895000L , 1585));
        quakeFlavor.add(new QuakeFlavor(", " + TAG_PAST_MONTH, 1472037199000L , 8637));

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
   /*
    public void addListItem(String url) throws JSONException {
        QuakeUpdate qu = new QuakeUpdate(url);
        qu.request(_activity, new QuakeUpdate.VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                try {
                    extractJSON(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void extractJSON (String result) throws IOException {
        JsonReader reader = new JsonReader(new StringReader(result));
        long time = 0;
        int count = 0;
        String title = null;

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            if(name.equals("metedata")){
                while(reader.hasNext()){
                    if(name.equals("generated")){
                        time = reader.nextLong();
                    } else if (name.equals("count")){
                        count = reader.nextInt();
                    } else if (name.equals("title")) {
                        title = reader.nextString();
                    } else {
                        reader.skipValue();
                    }
                }
            } else {
                reader.skipValue();
            }

        }
        Log.i(TAG, title + " / " + time + " / " + count);
        quakeFlavor.add(new QuakeFlavor(title, time, count));
        counter --;
    }
    */




}