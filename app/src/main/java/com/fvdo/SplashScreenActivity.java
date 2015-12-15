package com.fvdo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;

import org.json.JSONObject;

public class SplashScreenActivity extends AppCompatActivity {

    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;
    String TAG = "SplashScreenActivity";
    String fbid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //intitialize sdk
        FacebookSdk.sdkInitialize(getApplicationContext());


        setContentView(R.layout.activity_splash_screen);


        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.

                updateWithToken(currentAccessToken);

            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();

        updateWithToken(accessToken);

    }


    private void updateWithToken(AccessToken currentAccessToken) {

        if (currentAccessToken != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {


                    GraphRequestAsyncTask request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject user, GraphResponse graphResponse) {
//                            Log.d(TAG, user.optString("email"));
//                            Log.d(TAG, user.optString("name"));
//                            Log.d(TAG, user.optString("id"));

//                            fbid = user.optString("id");
                            System.out.println("Check fbid splash : " + fbid);

                        }
                    }).executeAsync();


                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    i.putExtra("fbid", fbid);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);

                    finish();
                }
            }, 3000);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }
}
