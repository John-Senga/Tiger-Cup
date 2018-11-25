package com.iics.ust.tigercup;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NextClickListener extends ShopClickListener {
    Dialog prevDialog = null;

    public NextClickListener(AppCompatActivity context, ShopData shop, Dialog dialog) {
        super(context, shop);
        this.prevDialog = dialog;
    }

    @Override
    public void onClick(View v){
        prevDialog.dismiss();
        showDialog();
    }
}
