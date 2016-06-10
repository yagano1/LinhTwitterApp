package com.example.linh.twiterapp;

/**
 * Created by linh on 6/9/2016.
 */
import com.twitter.sdk.android.core.Session;

import com.crashlytics.android.Crashlytics;

public class SessionRecorder {
    public static Session recordInitialSessionState(Session twitterSession) {
        if (twitterSession != null) {
            recordSessionActive("Splash: user with active Twitter session", twitterSession);
            return twitterSession;
        } else {
            recordSessionInactive("Splash: anonymous user");
            return null;
        }
    }

    public static void recordSessionActive(String message, Session session) {
        recordSessionActive(message, String.valueOf(session.getId()));
    }

    public static void recordSessionInactive(String message) {
        recordSessionState(message, null, false);
    }

    private static void recordSessionActive(String message, String userIdentifier) {
        recordSessionState(message, userIdentifier, true);
    }

    private static void recordSessionState(String message, String userIdentifier,boolean active) {

        Crashlytics.setUserIdentifier(userIdentifier);

    }
}