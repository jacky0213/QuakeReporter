package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jacky on 2016/8/23.
 */
public class MainMenuAdapter extends ArrayAdapter<QuakeFlavor>  {

    public static final String TAG = "MainMenuAdapter";
    Context context;
    Activity activity;
    private TextView topicTv, timeTv, recordNumTv;

    public static final String TAG_PAST_HOUR = "Past Hour";
    public static final String TAG_PAST_DAY = "Past Day";
    public static final String TAG_PAST_WEEK = "Past Week";
    public static final String TAG_PAST_MONTH = "Past Month";

    public static final String URL_PAST_HOUR = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";
    public static final String URL_PAST_DAY = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";
    public static final String URL_PAST_WEEK = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson";
    public static final String URL_PAST_MONTH = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";

    public MainMenuAdapter(Context context, ArrayList<QuakeFlavor> quakeFlavor) {
        super(context, 0, quakeFlavor);
        this.context = context;
    }

    private MainMenuCallback mainMenuCallback;

    public interface MainMenuCallback{
        void onListItemClick(String result);
    }

    public void setCallback(MainMenuCallback mainMenuCallback){
        this.mainMenuCallback = mainMenuCallback;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.menu_item, parent, false);
        }

        final QuakeFlavor currentQuakeFlavor = getItem(position);
        String topic = currentQuakeFlavor.getTopic();
        String[] topicArr = topic.split(", ");
        topic = topicArr[1];
        final String url = getURL(topic);

        Log.i(TAG, "The URL of " + topic + " is " + url);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                v.setEnabled(false);

                QuakeUpdate qu = new QuakeUpdate(url);
                try {
                    qu.request(context, new QuakeUpdate.VolleyCallback() {
                        @Override
                        public void  onSuccess(String result) throws JSONException {
                            Log.i(TAG, result);
                            if(mainMenuCallback != null){
                                mainMenuCallback.onListItemClick(result);
                            }
                            v.setEnabled(true);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        topicTv = (TextView) listItemView.findViewById(R.id.topicTv);
        topicTv.setText(topic);

        timeTv = (TextView) listItemView.findViewById(R.id.timeTv);
        String dateToDisplay = formatDate(currentQuakeFlavor.getDate());
        String timeToDisplay = formatTime(currentQuakeFlavor.getDate());
        timeTv.setText(dateToDisplay + " / " + timeToDisplay);

        recordNumTv = (TextView) listItemView.findViewById(R.id.recordNumTv);
        recordNumTv.setText(String.valueOf(currentQuakeFlavor.getRecordNum()));

        GradientDrawable circle = (GradientDrawable) recordNumTv.getBackground();
        circle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude1));
        return listItemView;
    }

    private String getURL(String topic){
        String url = null;

        switch(topic){
            case TAG_PAST_HOUR:
                url = URL_PAST_HOUR;
                break;
            case TAG_PAST_DAY:
                url = URL_PAST_DAY;
                break;
            case TAG_PAST_WEEK:
                url = URL_PAST_WEEK;
                break;
            case TAG_PAST_MONTH:
                url = URL_PAST_MONTH;
                break;
        }
        return url;
    }

    private static String formatDate(long date){
        Date dateObj = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObj);
    }
    private static String formatTime(long date){
        Date dateObj = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObj);
    }
}
