package com.iics.ust.tigercup;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Shops extends AppCompatActivity {

    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        button = findViewById(R.id.shop_1);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final Dialog dialog = new Dialog(Shops.this);
                dialog.setContentView(R.layout.activity_shop_modal);
                dialog.show();
            }
        });
    }
}
