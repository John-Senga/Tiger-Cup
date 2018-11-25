package com.iics.ust.tigercup;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ShopClickListener implements View.OnClickListener {
    ShopData shop;
    AppCompatActivity context;
    int resourceId;

    public ShopClickListener(AppCompatActivity context, ShopData shop, int resourceId) {
        this.shop = shop;
        this.context = context;
        this.resourceId = resourceId;
    }

    @Override
    public void onClick(View v){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_shop_modal);

        //Modal title
        dialog.setTitle(shop.Name);

        //image
        ImageView img = dialog.findViewById(R.id.imageView);
        img.setImageResource(resourceId);

        Button mapBtn = dialog.findViewById(R.id.map);
        mapBtn.setOnClickListener(new View.OnClickListener()
           {
               @Override
               public void onClick(View v) {
                   Intent i = new Intent(Intent.ACTION_VIEW);
                   i.setData(Uri.parse("http://maps.google.com/maps?saddr=14.609920, 120.992033&daddr="+shop.Latitude+","+shop.Longitude));
                   Intent chooser = Intent.createChooser(i,"Please select a map application");
                   context.startActivity(chooser);
               }
           }
        );

        dialog.show();
    }
}