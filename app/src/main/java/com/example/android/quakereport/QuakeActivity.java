/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class QuakeActivity extends AppCompatActivity {

    public static final String TAG = "QuakeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        /** Create a fake list of earthquake locations.
         ArrayList<QuakeFlavor> quakeFlavor = new ArrayList<QuakeFlavor>();

         quakeFlavor.add(new QuakeFlavor(7.2, "San Francisco", "Feb 2, 2016"));
         quakeFlavor.add(new QuakeFlavor(6.1, "London", "July 20, 2015"));
         quakeFlavor.add(new QuakeFlavor(3.9, "Tokyo", "Nov 10, 2014"));
         quakeFlavor.add(new QuakeFlavor(5.4, "Mexico City", "May 3, 2014"));
         quakeFlavor.add(new QuakeFlavor(2.8, "Moscow", "Jan 31, 2013"));
         quakeFlavor.add(new QuakeFlavor(4.9, "Rio de Janeiro", "Aug 19, 2012"));
         quakeFlavor.add(new QuakeFlavor(1.6, "Paris", "Oct 30, 2011"));

         QuakeAdapter quakeAdapter = new QuakeAdapter(this, quakeFlavor);
         */

        if (savedInstanceState == null) {
            navigateToFragment(MainMenuFragment.class);
        }

        /*
        QuakeUpdate qu = new QuakeUpdate("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson");
        try {
            qu.request(this, new QuakeUpdate.VolleyCallback() {
                @Override
                public void onSuccess(String result) throws JSONException {
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON_RESULT", result);
                    navigateToFragment(QuakeFragment.class, bundle);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
    }

    public boolean navigateToFragment(Class fragmentClass) {
        return navigateToFragment(fragmentClass, null);
    }

    /**
     * Show the specified fragment in the main container
     * @param fragmentClass
     * @param args optional bundle to pass on to the fragment
     * @return true = a new fragment was instantiated, false = current fragment has been reused
     */
    public boolean navigateToFragment (Class fragmentClass, Bundle args){
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.container);
        if (currentFragment != null && currentFragment.getClass().equals(fragmentClass)) {
            return false;
        }

        Fragment fragment = null;

        if (fragmentClass.equals(QuakeFragment.class)) {
            fragment = new QuakeFragment();
        } else if (fragmentClass.equals(MainMenuFragment.class)) {
            fragment = new MainMenuFragment();
        }

        //Pass value to fragment
        if (fragment != null) {
            //pass args
            if (args != null) {
                fragment.setArguments(args);
            }
            //replace fragment in container
            String Tag = fragmentClass.toString();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.zoom_enter, R.animator.zoom_exit, R.animator.zoom_pop_enter, R.animator.zoom_pop_exit);
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        FragmentManager fm = this.getFragmentManager();
        if (fm.getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            fm.popBackStack();
        }
    }
}
