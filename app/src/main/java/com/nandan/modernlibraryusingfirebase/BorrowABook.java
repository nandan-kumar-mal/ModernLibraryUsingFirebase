package com.nandan.modernlibraryusingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class BorrowABook extends AppCompatActivity {

    private TextView txttitle, txtauthor, txtcategory, txtedition;
    private AutoCompleteTextView acedtxtRollNo;
    private List<String> studentsroll;
    private DatabaseReference mref;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_a_book);

        txttitle = findViewById(R.id.txt_title);
        txtauthor = findViewById(R.id.txt_Author);
        txtcategory = findViewById(R.id.txt_Cat);
        txtedition = findViewById(R.id.txt_Edition);
        acedtxtRollNo = findViewById(R.id.actv);
        studentsroll = new ArrayList<String>();
        showBookDetails();
        mref = FirebaseDatabase.getInstance().getReference();
        mref.child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot rollSnapshot: snapshot.getChildren()){
                    String roll_no = rollSnapshot.child("Roll No").getValue(String.class);
                    studentsroll.add(roll_no);
                    ArrayAdapter<String> studentsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, studentsroll);
                    acedtxtRollNo.setAdapter(studentsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BorrowABook.this, "Unsuccessful Data Loading", Toast.LENGTH_SHORT).show();

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