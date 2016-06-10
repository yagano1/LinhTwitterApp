package com.example.linh.twiterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.LoginEvent;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;

import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginAcitivy extends Activity {



    private TwitterLoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();

    }

    private void setupView() {
        setUpTwitterButton();
    }

    private void setUpTwitterButton() {
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                SessionRecorder.recordSessionActive("Login: twitter account active", result.data);
                Answers.getInstance().logLogin(new LoginEvent().putMethod("Twitter").putSuccess(true));
                startActivity(new Intent(LoginAcitivy.this,MainAcitivy.class));
            }

            @Override
            public void failure(TwitterException exception) {
                Answers.getInstance().logLogin(new LoginEvent().putMethod("Twitter").putSuccess(false));
                Crashlytics.logException(exception);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


}
