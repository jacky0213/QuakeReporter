package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jacky on 2016/8/22.
 */
public class QuakeAdapter extends ArrayAdapter<QuakeFlavor>{

    private TextView placePart1Tv, placePart2Tv, dateTv, timeTv, magnitudeTv;
    double magnitude;
    String location;
    Context context;

    public QuakeAdapter(Context context, ArrayList<QuakeFlavor> quakeFlavor) {
        super(context, 0, quakeFlavor);
        this.context = context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link QuakeFlavor} object located at this position in the list
        final QuakeFlavor currentQuakeFlavor = getItem(position);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = currentQuakeFlavor.getURL();
                Uri uri = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(launchBrowser);
            }
        });

        //Magnitude attribute
        magnitudeTv = (TextView) listItemView.findViewById(R.id.magnitudeTv);
        magnitude = formatMagnitude(currentQuakeFlavor.getMagnitude());
        magnitudeTv.setText(String.valueOf(magnitude));

        GradientDrawable circle = (GradientDrawable) magnitudeTv.getBackground();
        int color = getMagnitudeColor(magnitude);
        circle.setColor(color);

        //Location attribute
        placePart1Tv = (TextView) listItemView.findViewById(R.id.placePart1Tv);
        placePart2Tv = (TextView) listItemView.findViewById(R.id.placePart2Tv);
        location = currentQuakeFlavor.getLocation();
        String placePart1;
        String placePart2;
        if(location.contains("of")){
            placePart1 = location.substring(0, location.indexOf(" of ") + 3 );
            placePart2 = location.substring(location.indexOf(" of ") + 3, location.length());
        } else {
            placePart1 = "Near the";
            placePart2 = location;
        }
        placePart1Tv.setText(placePart1);
        placePart2Tv.setText(placePart2);

        //Time attribute
        dateTv = (TextView) listItemView.findViewById(R.id.dateTv);
        timeTv = (TextView) listItemView.findViewById(R.id.timeTv);
        String dateToDisplay = formatDate(currentQuakeFlavor.getDate());
        String timeToDisplay = formatTime(currentQuakeFlavor.getDate());
        dateTv.setText(dateToDisplay);
        timeTv.setText(timeToDisplay);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int colorId = 0;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch(magnitudeFloor){
            case 2:  colorId = R.color.magnitude2;
                break;
            case 3:  colorId = R.color.magnitude3;
                break;
            case 4:  colorId = R.color.magnitude4;
                break;
            case 5:  colorId = R.color.magnitude5;
                break;
            case 6:  colorId = R.color.magnitude6;
                break;
            case 7:  colorId =  R.color.magnitude7;
                break;
            case 8:  colorId = R.color.magnitude8;
                break;
            case 9:  colorId = R.color.magnitude9;
                break;
            case 10:  colorId = R.color.magnitude10plus;
                break;
            default: colorId = R.color.magnitude1;
                break;
        }
        return ContextCompat.getColor(getContext(), colorId);
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
    private static double formatMagnitude(double mag){
        DecimalFormat dec = new DecimalFormat("0.0");
        return Double.parseDouble(dec.format(mag));
    }


}
