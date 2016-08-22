package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jacky on 2016/8/22.
 */
public class QuakeUtils {

    public static final String TAG = "QuakeUtils";
    public static final String info = "{\"type\":\"FeatureCollection\",\"metadata\":{\"generated\":1471863301000,\"url\":\"http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson\",\"title\":\"USGS All Earthquakes, Past Hour\",\"status\":200,\"api\":\"1.5.2\",\"count\":7},\"features\":[{\"type\":\"Feature\",\"properties\":{\"mag\":2.19,\"place\":\"0km ESE of Pahala, Hawaii\",\"time\":1471862032990,\"updated\":1471862240500,\"tz\":-600,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/hv61383306\",\"detail\":\"http://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/hv61383306.geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"automatic\",\"tsunami\":0,\"sig\":74,\"net\":\"hv\",\"code\":\"61383306\",\"ids\":\",hv61383306,\",\"sources\":\",hv,\",\"types\":\",general-link,geoserve,origin,phase-data,\",\"nst\":59,\"dmin\":0.04829,\"rms\":0.15,\"gap\":87,\"magType\":\"md\",\"type\":\"earthquake\",\"title\":\"M 2.2 - 0km ESE of Pahala, Hawaii\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-155.4774933,19.2043343,38.77]},\"id\":\"hv61383306\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":0.57,\"place\":\"7km N of Banning, CA\",\"time\":1471861722630,\"updated\":1471862059955,\"tz\":-420,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/ci37668192\",\"detail\":\"http://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/ci37668192.geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"automatic\",\"tsunami\":0,\"sig\":5,\"net\":\"ci\",\"code\":\"37668192\",\"ids\":\",ci37668192,\",\"sources\":\",ci,\",\"types\":\",general-link,geoserve,nearby-cities,origin,phase-data,scitech-link,\",\"nst\":12,\"dmin\":0.07918,\"rms\":0.15,\"gap\":87,\"magType\":\"ml\",\"type\":\"earthquake\",\"title\":\"M 0.6 - 7km N of Banning, CA\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-116.8815,33.9848333,9.77]},\"id\":\"ci37668192\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":1,\"place\":\"85km WNW of Cape Yakataga, Alaska\",\"time\":1471861602000,\"updated\":1471862239277,\"tz\":-480,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/ak13867971\",\"detail\":\"http://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/ak13867971.geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"automatic\",\"tsunami\":0,\"sig\":15,\"net\":\"ak\",\"code\":\"13867971\",\"ids\":\",ak13867971,\",\"sources\":\",ak,\",\"types\":\",general-link,geoserve,origin,\",\"nst\":null,\"dmin\":null,\"rms\":0.66,\"gap\":null,\"magType\":\"ml\",\"type\":\"earthquake\",\"title\":\"M 1.0 - 85km WNW of Cape Yakataga, Alaska\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-143.8152,60.4081,5]},\"id\":\"ak13867971\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":2.6,\"place\":\"87km E of Sutton-Alpine, Alaska\",\"time\":1471861569000,\"updated\":1471862641352,\"tz\":-480,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/ak13867965\",\"detail\":\"http://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/ak13867965.geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"automatic\",\"tsunami\":0,\"sig\":104,\"net\":\"ak\",\"code\":\"13867965\",\"ids\":\",ak13867965,\",\"sources\":\",ak,\",\"types\":\",general-link,geoserve,origin,\",\"nst\":null,\"dmin\":null,\"rms\":0.81,\"gap\":null,\"magType\":\"ml\",\"type\":\"earthquake\",\"title\":\"M 2.6 - 87km E of Sutton-Alpine, Alaska\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-147.1961,61.8775,15]},\"id\":\"ak13867965\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":0.86,\"place\":\"11km N of Cabazon, CA\",\"time\":1471861421460,\"updated\":1471861635648,\"tz\":-420,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/ci37668184\",\"detail\":\"http://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/ci37668184.geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"automatic\",\"tsunami\":0,\"sig\":11,\"net\":\"ci\",\"code\":\"37668184\",\"ids\":\",ci37668184,\",\"sources\":\",ci,\",\"types\":\",general-link,geoserve,nearby-cities,origin,phase-data,scitech-link,\",\"nst\":27,\"dmin\":0.02198,\"rms\":0.22,\"gap\":89,\"magType\":\"ml\",\"type\":\"earthquake\",\"title\":\"M 0.9 - 11km N of Cabazon, CA\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-116.8028333,34.0135,17.74]},\"id\":\"ci37668184\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":0.73,\"place\":\"14km S of Big Bear Lake, CA\",\"time\":1471861248980,\"updated\":1471861469621,\"tz\":-420,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/ci37668176\",\"detail\":\"http://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/ci37668176.geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"automatic\",\"tsunami\":0,\"sig\":8,\"net\":\"ci\",\"code\":\"37668176\",\"ids\":\",ci37668176,\",\"sources\":\",ci,\",\"types\":\",general-link,geoserve,nearby-cities,origin,phase-data,scitech-link,\",\"nst\":18,\"dmin\":0.0297,\"rms\":0.17,\"gap\":91,\"magType\":\"ml\",\"type\":\"earthquake\",\"title\":\"M 0.7 - 14km S of Big Bear Lake, CA\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-116.9275,34.1225,7.12]},\"id\":\"ci37668176\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":1.6,\"place\":\"86km NNW of Talkeetna, Alaska\",\"time\":1471860469000,\"updated\":1471861035011,\"tz\":-480,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/ak13867963\",\"detail\":\"http://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/ak13867963.geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"automatic\",\"tsunami\":0,\"sig\":39,\"net\":\"ak\",\"code\":\"13867963\",\"ids\":\",ak13867963,\",\"sources\":\",ak,\",\"types\":\",general-link,geoserve,origin,\",\"nst\":null,\"dmin\":null,\"rms\":0.52,\"gap\":null,\"magType\":\"ml\",\"type\":\"earthquake\",\"title\":\"M 1.6 - 86km NNW of Talkeetna, Alaska\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-150.8274,63.0292,100]},\"id\":\"ak13867963\"}],\"bbox\":[-155.4774933,19.2043343,5,-116.8028333,63.0292,100]}";

    private QuakeUtils() {
    }

    public static ArrayList<QuakeFlavor> extractEarthquakes() {

        ArrayList<QuakeFlavor> quakeFlavor = new ArrayList<>();


        try{

            JSONObject root = new JSONObject(info);
            JSONArray featuresArr = root.getJSONArray("features");
            Log.i(TAG, String.valueOf(featuresArr.length()));
            for (int i=0; i<featuresArr.length(); i++){

                JSONObject featureObj = featuresArr.getJSONObject(i);
                JSONObject propertiesObj = featureObj.getJSONObject("properties");

                //Magnitude attribute
                Double magnitude = propertiesObj.getDouble("mag");

                //Place attribute
                String place = propertiesObj.getString("place");

                //Time attribute
                long time = propertiesObj.getLong("time");

                //URL attribute
                String url = propertiesObj.getString("url");

                Log.i(TAG, String.valueOf(magnitude) + " / " + place + " / " + String.valueOf(time) + " / " + url);
                quakeFlavor.add(new QuakeFlavor(magnitude, place, time, url));
            }

        } catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return quakeFlavor;
    }



}
