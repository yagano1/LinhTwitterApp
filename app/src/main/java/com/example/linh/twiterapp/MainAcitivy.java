package com.example.linh.twiterapp;



import android.app.ListActivity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.LoginEvent;
import com.twitter.sdk.android.Twitter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

public class MainAcitivy extends ListActivity
implements NavigationView.OnNavigationItemSelectedListener {

    protected static final String TAG = MainAcitivy.class.getSimpleName();
    private static final String TOKEN_URL = "https://api.twitter.com/oauth2/token";
    private static final String LIST_URL = "https://api.twitter.com/1.1/search/tweets.json?q=linh&max_id=1";
    private ArrayAdapter<JSONObject> tweetAdapter;



    ImageView imageView ;
    TextView textViewUserName;
    TextView textViewEmail;
    String username;
    public static RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_acitivy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main_acitivy);
        imageView = (ImageView) headerLayout.findViewById(R.id.avatar);
        textViewUserName = (TextView) headerLayout.findViewById(R.id.userName);
        textViewEmail = (TextView) headerLayout.findViewById(R.id.email);
        textViewUserName.setText(username);
        mRequestQueue = Volley.newRequestQueue(this);

//        StringRequest request = new TokenRequest(Request.Method.POST, TOKEN_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject object = new JSONObject(response);
//                    Log.w(TAG, object.optString("access_token"));
//                    TwitterValues.ACCESS_TOKEN = object.optString("access_token");
//                    onSearchRequested();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Error during request");
//                error.printStackTrace();
//            }
//        });
//        mRequestQueue.add(request);
        final TextView testtest = (TextView) findViewById(R.id.twtweet);

        StringRequest test =   new TweetsRequest(LIST_URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray tweets = object.optJSONArray("statuses");
                    int count = tweets.length();
                    for (int i = 0; i < count; i++) {
                        if (object != null) {

                            testtest.setText(tweets.optJSONObject(i).optString("text","<No Text"));

                        }
                        else {
                            Log.d("bbbb","ko co gi ca");
                            testtest.setText("Asdasdada");

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error during request");
                error.printStackTrace();
            }
        });
        mRequestQueue.add(test);



    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_acitivy, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_send) {
            Twitter.getSessionManager().clearActiveSession();
            SessionRecorder.recordSessionInactive("About: accounts deactivated");
            Answers.getInstance().logLogin(new LoginEvent().putMethod("Twitter").putSuccess(false));
            this.finish();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
