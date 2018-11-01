package com.iics.ust.tigercup;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
            dialog.setTitle(shop.name);

            //image
            ImageView img = dialog.findViewById(R.id.imageView);
            img.setImageResource(resourceId);



            dialog.show();
    }
}