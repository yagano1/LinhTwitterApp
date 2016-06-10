package com.example.linh.twiterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

/**
 * Created by linh on 6/10/2016.
 */
public class InitialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Session activeSession = SessionRecorder.recordInitialSessionState(
                Twitter.getSessionManager().getActiveSession()
        );

        if (activeSession != null) {
            startMainAcitivity();
        } else {
            startLoginActivity();
        }
    }

    private void startMainAcitivity() {
        startActivity(new Intent(this,MainAcitivy.class));
    }


    private void startLoginActivity() {

        startActivity(new Intent(this, LoginAcitivy.class));
    }
}