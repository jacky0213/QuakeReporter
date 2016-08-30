package com.example.android.quakereport;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jacky on 2016/8/22.
 */
public class QuakeUpdate {

    public static final String TAG = "QuakeUpdate";

    //Constructor
    public QuakeUpdate(){

    }



    //Interface for pass value to UI
    public interface VolleyCallback{
        void onSuccess(String result) throws JSONException;
    }

    public void request(Context context, String url, final VolleyCallback callback) throws JSONException {

        //Create VOLLEY request object
        RequestQueue activateQueue = Volley.newRequestQueue(context);

        //Create JSON request object
        //JsonObjectRequest (int Method, String URL, JSONObject content, OnResponseReceive, OnErrorReceive)
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            callback.onSuccess(response.toString());
                            Log.i(TAG , "Content received from server: " + response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                } catch (NullPointerException e){
                    //Happened at no network connection
                    //Show dialog and ask for network connection
                    e.printStackTrace();
                }
            }
        });

        //Send request to server
        activateQueue.add(jsonObjectRequest);
    }

}
