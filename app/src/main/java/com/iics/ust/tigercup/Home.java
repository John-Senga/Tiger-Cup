package com.iics.ust.tigercup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                Intent i = null;
                i = new Intent(this, Login.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

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
