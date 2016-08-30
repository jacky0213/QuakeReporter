package com.example.android.quakereport;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

/**
 * Created by Jacky on 2016/8/29.
 */
public class SplashFragment extends Fragment{

    public static final String TAG = "SplashFragment";

    private final Bundle bundle = new Bundle();
    private final int menuItemNum = URL_Arr.length;
    private final QuakeUpdate qu = new QuakeUpdate();

    public static final String TAG_PAST_HOUR = "Past Hour";
    public static final String TAG_PAST_DAY = "Past Day";
    public static final String TAG_PAST_WEEK = "Past Week";
    public static final String[] TAG_PAST_Arr = {TAG_PAST_HOUR, TAG_PAST_DAY, TAG_PAST_WEEK};

    public static final String URL_PAST_HOUR = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";
    public static final String URL_PAST_DAY = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";
    public static final String URL_PAST_WEEK = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson";
    public static final String[] URL_Arr = {URL_PAST_HOUR, URL_PAST_DAY, URL_PAST_WEEK};

    public static int readyCount = 0;

    private QuakeActivity _activity;
    private TextView counterTv;
    private ProgressBar progress;
    private Handler handler;
    private Boolean readyBool = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.splash_fragment, container, false);
        Log.v(TAG, TAG + " - Fragment switched");

        //init fields
        _activity = (QuakeActivity) getActivity();
        progress = (ProgressBar) rootView.findViewById(R.id.progress);
        counterTv = (TextView) rootView.findViewById(R.id.counterTv);
        handler = new Handler();

        progress.setProgress(0);
        progress.setMax(TAG_PAST_Arr.length);

        final Thread t = new Thread() {
            @Override
            public void run() {
                while(readyBool == false) {
                    try {
                        sleep(200);
                        progress.setProgress(readyCount);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            counterTv.setText(progress.getProgress() + " / " + progress.getMax());
                        }
                    });
                }
            }

        };
        t.start();

        setMainMenuItem();

        return rootView;
    }

    public void setMainMenuItem(){
        try {
            qu.request(_activity, URL_Arr[readyCount], new QuakeUpdate.VolleyCallback() {
                @Override
                public void onSuccess(String result) throws JSONException {
                    if(readyCount == menuItemNum - 1){
                        bundle.putString(TAG_PAST_Arr[readyCount], result);
                        readyBool = true;

                        _activity.navigateToFragment(MainMenuFragment.class, bundle, false);
                        Log.i(TAG, "Ready Count: " + readyCount + " / readyBool: " + readyBool);
                    } else {
                        Log.i(TAG, "Ready Count: " + readyCount + " / readyBool: " + readyBool);
                        bundle.putString(TAG_PAST_Arr[readyCount], result);
                        readyCount++;
                        setMainMenuItem();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
