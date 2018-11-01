package com.iics.ust.tigercup;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Shops extends AppCompatActivity {
    private String[]Data = {
        "iChill Theater Cafe", "Cafe Kivhan Coffee", "Figaro Coffee Company", "Beyond Coffee Manila",
        "Amo Yamie Crib España", "Starbucks Coffee Dapitan Branch", "Café Churro", "Coffee Indulgence",
        "Cafe-UK", "Starbucks P. Noval", "Floti Cafe", "Seattle's Best", "Cafe-UK Co. - Mendiola",
        "Starbucks Coffee", "Cafe UK Dapitan", "Wit Avenue Cafe & Bar", "Bo's Coffee", "La Taza",
        "Amor Bakery", "Mamang Taho Coffee Shop", "Bon AppeTEA - España", "Starbucks Coffee",
        "Fritzo Cafe", "Hub Urban Coffee"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        LinearLayout layout = findViewById(R.id.shopContainer);

        for(int i = 0; i<Data.length; i++){
            Button shop = new Button(this);

            //Width and Height
            shop.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 250));

            //Text
            shop.setText("Button");

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
