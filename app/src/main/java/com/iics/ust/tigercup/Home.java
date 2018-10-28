package com.iics.ust.tigercup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void goToShops(View v){
        Intent i = null;

        if(v.getId() == R.id.cafe){
            Log.d("Log","The user has selected cafe");
        }
        if(v.getId() == R.id.study_hub){
            Log.d("Log","The user has selected study hub");
        }
        if(v.getId() == R.id.leisure){
            Log.d("Log","The user has selected leisure");
        }
        if(v.getId() == R.id.relaxation){
            Log.d("Log","The user has selected relaxation");
        }

        i = new Intent(this, Shops.class);
        startActivity(i);

    }
}
