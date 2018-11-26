package com.iics.ust.tigercup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Reviews extends AppCompatActivity {
    FirebaseDatabase db;
    EditText reviewField;
    String shopId, key, review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        db = FirebaseDatabase.getInstance();

        Bundle bundle = getIntent().getExtras();
        shopId = bundle.getString("shopId");

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        key = pref.getString("key", null);

        reviewField = findViewById(R.id.reviewField);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getReviews();
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

    public void submit(View v){
        review =  reviewField.getText().toString().trim();
        if(!review.equals("")){
            DatabaseReference ref = db.getReference("users");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String fname = dataSnapshot.child(key).child("fname").getValue().toString();
                    String lname = dataSnapshot.child(key).child("lname").getValue().toString();
                    addReview(fname, lname, review);
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });
        }
    }

    public void addReview(String fname, String lname, String review){
        ReviewData reviewData = new ReviewData(fname, lname, review);
        DatabaseReference ref = db.getReference("shops");
        String key = ref.child(shopId).child("Reviews").push().getKey();
        ref.child(shopId).child("Reviews").child(key).setValue(reviewData);
        reviewField.setText("");
        Toast.makeText(this, "Your review has been successfully published", Toast.LENGTH_SHORT).show();
    }

    public void getReviews(){
        DatabaseReference ref = db.getReference("shops");
        ref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ReviewData> reviews = new ArrayList<>();
                for (DataSnapshot snapshot: dataSnapshot.child(shopId).child("Reviews").getChildren()) {
                    String fname = snapshot.child("fname").getValue().toString();
                    String lname = snapshot.child("lname").getValue().toString();
                    String review = snapshot.child("review").getValue().toString();
                    ReviewData reviewData = new ReviewData(fname,lname,review);
                    reviews.add(reviewData);
                }
                renderReviews(reviews);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void renderReviews(List<ReviewData>reviews){
        LinearLayout layout = findViewById(R.id.reviewsContainer);
        layout.removeAllViews();
        if(reviews.size()==0){
            TextView text = new TextView(this);
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            text.setText("No Reviews Available");
            text.setGravity(Gravity.CENTER);
            layout.addView(text);
        }
        else {
            for (ReviewData review : reviews) {
                LinearLayout card = new LinearLayout(this);
                card.setBackground(this.getDrawable(R.drawable.card));
                card.setOrientation(LinearLayout.VERTICAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, 20);

                TextView nameText = new TextView(this);
                nameText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                nameText.setText(review.fname+" "+review.lname);
                nameText.setTypeface(null, Typeface.BOLD);
                card.addView(nameText);

                TextView reviewText = new TextView(this);
                reviewText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                reviewText.setText(review.review);
                card.addView(reviewText);

                layout.addView(card, layoutParams);
            }
        }
    }
}
