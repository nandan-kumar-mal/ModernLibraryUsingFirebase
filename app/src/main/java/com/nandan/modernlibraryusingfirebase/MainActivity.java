package com.nandan.modernlibraryusingfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    //FirebaseDatabase  database;
    //DatabaseReference dbRef;
  private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //database= FirebaseDatabase.getInstance();
       // dbRef=database.getReference("Student");

        FirebaseDatabase.getInstance().getReference().child("Teachers").child("Com_Science").push().setValue("Dr. Mema");

       // dbRef.setValue("Nandan");


            

    }
}