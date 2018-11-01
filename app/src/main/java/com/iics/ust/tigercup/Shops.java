package com.iics.ust.tigercup;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Shops extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        generateShopButtons();
    }

    private void generateShopButtons(){
        List<ShopData>shops = getShops();

        LinearLayout layout = findViewById(R.id.shopContainer);
        for(ShopData shop: shops){
            //Button with Style
            Button btn = new Button(this, null, 0, R.style.ButtonStyle);

            //Width and Height
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 250));

            //Text
            btn.setText(shop.name);

            //Image
            int resourceId = getResources().getIdentifier(shop.img, "drawable", getPackageName());
            btn.setBackgroundResource(resourceId);

            //Click Listener
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final Dialog dialog = new Dialog(Shops.this);
                    dialog.setContentView(R.layout.activity_shop_modal);
                    dialog.show();
                }
            });

            layout.addView(btn);
        }
    }

    private List<ShopData> getShops(){
        try {
            BufferedReader x =  new BufferedReader(new InputStreamReader(getAssets().open("shops.csv")));
            List<ShopData> shops = new ArrayList<>();
            String line;
            x.readLine();
            while ((line = x.readLine()) != null) {
                String[]val = line.split(",");
                ShopData data = new ShopData(val[0], val[1], val[2], val[3], val[4]);
                shops.add(data);
            }
            x.close();
            return shops;
        } catch (Exception e) {
            Log.d("Log", e + "");
        }
        return null;
    }

}
