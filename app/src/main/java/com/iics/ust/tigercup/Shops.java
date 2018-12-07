package com.iics.ust.tigercup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Shops extends AppCompatActivity {
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        getShops();

        Bundle bundle = getIntent().getExtras();
        category = bundle.getString("category");

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
                SharedPreferences pref = getApplicationContext().getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor writer = pref.edit();
                writer.remove("key");
                writer.commit();

                Intent i = new Intent(this, Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getShops(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("shops");

        ref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ShopData> shops = new ArrayList<>();
                for (DataSnapshot shopSnapshot: dataSnapshot.getChildren()) {
                    String Category = shopSnapshot.child("Category").getValue().toString();
                    if(Category.equalsIgnoreCase(category)) {
                        String Id = shopSnapshot.getKey();
                        String Name = shopSnapshot.child("Name").getValue().toString();
                        String Image = shopSnapshot.child("Image").getValue().toString();
                        String Latitude = shopSnapshot.child("Latitude").getValue().toString();
                        String Longitude = shopSnapshot.child("Longitude").getValue().toString();
                        ShopData data = new ShopData(Id, Name, Category, Image, Latitude, Longitude);
                        shops.add(data);
                    }
                }

                for(int i = 0; i<shops.size(); i++){
                    int next = (i+1)%shops.size();
                    shops.get(i).setNext(shops.get(next));
                }
                generateShopButtons(shops);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Log", "Failed to read value.", error.toException());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void generateShopButtons(List<ShopData>shops){
        LinearLayout layout = findViewById(R.id.shopContainer);
        for(ShopData shop: shops){
            //Button with Style
            Button btn = new Button(this, null, 0, R.style.ButtonStyle);

            //Width and Height
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 250));

            //Text
            btn.setText(shop.Name);

            //Image
            int resourceId = getResources().getIdentifier(shop.Image, "drawable", getPackageName());
            btn.setBackgroundResource(resourceId);

            //Click Listener
            btn.setOnClickListener(new ShopClickListener(this, shop));

            //Add button to layout
            layout.addView(btn);
        }
    }

}
