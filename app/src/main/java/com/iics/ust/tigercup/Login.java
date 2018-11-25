package com.iics.ust.tigercup;

import android.content.Intent;
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

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String userEmail = snapshot.child("email").getValue().toString();
                    String userPassword = snapshot.child("password").getValue().toString();
                    if(loginEmail.equals(userEmail) && loginPassword.equals(userPassword)){
                        login(snapshot.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void login(String key){
        Intent i = null;
        i = new Intent(this, Home.class);
        startActivity(i);
    }

    public void goToRegister(View v){
        Intent i = null;
        i = new Intent(this, Register.class);
        startActivity(i);
    }
}
