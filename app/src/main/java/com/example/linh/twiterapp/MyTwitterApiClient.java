package com.example.linh.twiterapp;

import android.content.Context;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by linh on 6/9/2016.
 */
public class MyTwitterApiClient extends TwitterApiClient {

    /**
     * Must be instantiated after {@link TwitterCore} has been
     * initialized via {@link Fabric#with(Context, Kit[])}.
     *
     * @param session Session to be used to create the API calls.
     * @throws IllegalArgumentException if TwitterSession argument is null
     */
    public MyTwitterApiClient(TwitterSession session) {
        super(session);
    }

    /**
     * Provide CustomService with defined endpoints
     */
    public CustomService getCustomService() {
        return getService(CustomService.class);
    }

    interface CustomService {
        @GET("/1.1/users/show.json")
        void show(@Query("user_id") long id, Callback<User> cb);
    }
}
