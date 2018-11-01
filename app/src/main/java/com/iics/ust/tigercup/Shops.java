package com.iics.ust.tigercup;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.iics.ust.tigercup.logic.ShopConfig;
import com.iics.ust.tigercup.logic.ShopData;

public class Shops extends AppCompatActivity {
    ShopData[]shops = ShopConfig.getShops();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        LinearLayout layout = findViewById(R.id.shopContainer);

        for(int i = 0; i<shops.length; i++){
            //Button with Style
            Button shop = new Button(this, null, 0, R.style.ButtonStyle);

            //Width and Height
            shop.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 250));

            //Text
            shop.setText(shops[i].name);

            //Image
            shop.setBackgroundResource(R.drawable.sample_2);

            //Click Listener
            shop.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final Dialog dialog = new Dialog(Shops.this);
                    dialog.setContentView(R.layout.activity_shop_modal);
                    dialog.show();
                }
            });

            layout.addView(shop);
        }
    }
}
