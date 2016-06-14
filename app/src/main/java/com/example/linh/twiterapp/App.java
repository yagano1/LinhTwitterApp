package com.example.linh.twiterapp;

/**
 * Created by linh on 6/10/2016.
 */

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.io.File;

import io.fabric.sdk.android.Fabric;

/**
 * This class represents the Application and extends Application it is used to initiate the
 * application.
 */

public class App extends Application {



    private static App singleton;
    private TwitterAuthConfig authConfig;


    public static App getInstance() {
        return singleton;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        authConfig = new TwitterAuthConfig(TwitterValues.CONSUMER_KEY, TwitterValues.CONSUMER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
        Crashlytics.setBool("Crash", areCrashesEnabled());
    }

    public boolean areCrashesEnabled() {
        SharedPreferences preferences;
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return preferences.getBoolean("are_crashes_enabled", false);
    }


}
