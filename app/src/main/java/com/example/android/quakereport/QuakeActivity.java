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
import android.util.Log;

public class QuakeActivity extends AppCompatActivity {

    public static final String TAG = "QuakeActivity";

    boolean clearBackStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        if (savedInstanceState == null) {
            navigateToFragment(SplashFragment.class);
        }
    }

    public boolean navigateToFragment(Class fragmentClass) {
        return navigateToFragment(fragmentClass, null, false);
    }

    /**
     * Show the specified fragment in the main container
     * @param fragmentClass
     * @param args optional bundle to pass on to the fragment
     * @return true = a new fragment was instantiated, false = current fragment has been reused
     */
    public boolean navigateToFragment (Class fragmentClass, Bundle args, Boolean reset){
        clearBackStack = false;

        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.container);
        String Tag = fragmentClass.toString();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        Fragment fragment = null;
        if (currentFragment != null && currentFragment.getClass().equals(fragmentClass) && reset == false) {
            return false;
        } else if (currentFragment != null && currentFragment.getClass().equals(fragmentClass) && reset == true){
            transaction.remove(currentFragment);
            fragment = currentFragment;
            clearBackStack = false;
            Log.i(TAG, "QuakeFragment selected, reset");
        } else if (fragmentClass.equals(QuakeFragment.class)) {
            fragment = new QuakeFragment();
            Log.i(TAG, "QuakeFragment selected, no reset");
        } else if (fragmentClass.equals(MainMenuFragment.class)) {
            fragment = new MainMenuFragment();
            Log.i(TAG, "MainMenuFragment selected, no reset");
        } else if (fragmentClass.equals(SplashFragment.class)) {
            fragment = new SplashFragment();
            Log.i(TAG, "SplashFragment selected, no reset");
        }

        //Pass value to fragment
        if (fragment != null) {
            //pass args
            if (args != null) {
                fragment.setArguments(args);
            }
            //replace fragment in container
            transaction.setCustomAnimations(R.animator.zoom_enter, R.animator.zoom_exit, R.animator.zoom_pop_enter, R.animator.zoom_pop_exit);
            transaction.replace(R.id.container, fragment, Tag);
            if (!clearBackStack) {
                transaction.addToBackStack("");
            }
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
