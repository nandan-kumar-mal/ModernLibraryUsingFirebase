package com.nandan.modernlibraryusingfirebase;


import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class BorrowABook extends AppCompatActivity {

    private TextView txttitle, txtauthor, txtcategory, txtedition;
    private AutoCompleteTextView acedtxtRollNo;
    ArrayList<String> studentsroll;
    ArrayAdapter<String> adapter;
    private DatabaseReference mref;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_a_book);


        txttitle = findViewById(R.id.Title);
        txtauthor = findViewById(R.id.Author);
        txtcategory = findViewById(R.id.Categ);
        txtedition = findViewById(R.id.Edition);


        
        acedtxtRollNo = findViewById(R.id.actv);
        studentsroll = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,studentsroll);
        acedtxtRollNo.setAdapter(adapter);

        showBookDetails();
        mref = FirebaseDatabase.getInstance().getReference("User");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot rollSnapshot: snapshot.getChildren()){
                    studentsroll.add(rollSnapshot.getValue().toString());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });




    }

    private void showBookDetails() {
        Intent intent = getIntent();
        String booktitle = intent.getStringExtra("title");
        String bookauthor = intent.getStringExtra("author");
        String bookedition = intent.getStringExtra("edition");
        String bookcategory = intent.getStringExtra("category");

        txttitle.setText(booktitle);
        txtauthor.setText(bookauthor);
        txtedition.setText(bookedition);
        txtcategory.setText(bookcategory);

    }


}