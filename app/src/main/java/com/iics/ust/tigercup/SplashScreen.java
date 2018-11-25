package com.iics.ust.tigercup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Log.d("Log","The application has started");

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String key = pref.getString("key", null);

                Intent i;
                if(key==null) {
                    i = new Intent(SplashScreen.this, Login.class);
                }
                else{
                    i = new Intent(SplashScreen.this, Home.class);
                }
                SplashScreen.this.startActivity(i);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
