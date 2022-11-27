package com.example.group_10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    private TextView tvName,  tvGradYear, tvCurrentMe;

    private FirebaseDatabase database;
    private DatabaseReference root;

    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

         tvName = findViewById(R.id.textView_name);
         tvGradYear = findViewById(R.id.textView_gradYear);
         tvCurrentMe = findViewById(R.id.textView_currentMe);

        userID = getIntent().getExtras().get("user_id").toString();

        database = FirebaseDatabase.getInstance();
        root = database.getReference().getRoot().child("users");

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(userID)) {
                    String name = snapshot.child(userID).child("name").getValue().toString();
                    String year = snapshot.child(userID).child("year").getValue().toString();
                    String currentMe = snapshot.child(userID).child("current").getValue().toString();
                    tvName.setText(name);
                    tvGradYear.setText(year);
                    tvCurrentMe.setText(currentMe);
                } else {
                    Toast.makeText(Profile.this, "User does not exist!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}
