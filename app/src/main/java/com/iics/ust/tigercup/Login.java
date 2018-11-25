package com.iics.ust.tigercup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference root;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = FirebaseDatabase.getInstance();
        root = db.getReference("users");

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void verify(View v){
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String loginEmail = email.getText().toString().trim();
                String loginPassword = password.getText().toString().trim();
                Boolean incorrect = true;

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String userEmail = snapshot.child("email").getValue().toString();
                    String userPassword = snapshot.child("password").getValue().toString();
                    if(loginEmail.equals(userEmail) && loginPassword.equals(userPassword)){
                        login(snapshot.getKey());
                        incorrect = false;
                        break;
                    }
                }

                if(incorrect)
                    loginError();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void login(String key){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor writer = pref.edit();
        writer.putString("key", key);
        writer.commit();

        Intent i = new Intent(this, Home.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    private void loginError(){
        Toast.makeText(this,"Incorrect email or password", Toast.LENGTH_SHORT).show();
    }

    public void goToRegister(View v){
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }
}
