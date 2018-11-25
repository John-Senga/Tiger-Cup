package com.iics.ust.tigercup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference root;
    EditText fname, lname, email, password, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseDatabase.getInstance();
        root = db.getReference("users");

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
    }

    public void submit(View v){
        String fname = this.fname.getText().toString().trim();
        String lname = this.lname.getText().toString().trim();
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();
        String confirm = this.confirm.getText().toString().trim();

        if(fname.equals("")||lname.equals("")||email.equals("")||password.equals("")||confirm.equals("")){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirm)){
            Toast.makeText(this, "Password does not match the confirm password", Toast.LENGTH_SHORT).show();
        }
        else{
            UserData user = new UserData(fname, lname, email, password);
            String key = root.push().getKey();
            root.child(key).setValue(user);

            Intent i = null;
            i = new Intent(this, Home.class);
            startActivity(i);
        }
    }
}
