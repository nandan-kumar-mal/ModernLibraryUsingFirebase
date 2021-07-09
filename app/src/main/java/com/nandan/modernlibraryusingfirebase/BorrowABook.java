package com.nandan.modernlibraryusingfirebase;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BorrowABook extends AppCompatActivity {

    private TextView txttitle, txtauthor, txtcategory, txtedition,txt_name,txt_roll,txt_year;
    private AutoCompleteTextView acedtxtRollNo;
    ArrayList<String> studentsroll;
    ArrayAdapter<String> adapter;
    private DatabaseReference mref, nref;
    private Button btnFin;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_a_book);


        txttitle = findViewById(R.id.Title);
        txtauthor = findViewById(R.id.Author);
        txtcategory = findViewById(R.id.Categ);
        txtedition = findViewById(R.id.Edition);
        txt_name=findViewById(R.id.Name);
        txt_roll=findViewById(R.id.RollNo);
        txt_year=findViewById(R.id.Year);
        btnFin=findViewById(R.id.btn_Finish);



        
        acedtxtRollNo = findViewById(R.id.actv);
        studentsroll = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,studentsroll);
        acedtxtRollNo.setAdapter(adapter);

        mref = FirebaseDatabase.getInstance().getReference("User");
        nref = FirebaseDatabase.getInstance().getReference("Books");

        acedtxtRollNo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                txt_roll.setText(selectedItem);

                mref.child(selectedItem).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        txt_name.setText(snapshot.child("fullName").getValue().toString());
                        txt_year.setText(snapshot.child("studentClass").getValue().toString());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

 //                       addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String Usr_key = snapshot.getKey();
//                        txt_name.setText(snapshot.child(Usr_key).child("name").getValue().toString());
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });


            }

        });

        showBookDetails();

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot rollSnapshot: snapshot.getChildren()){

                    studentsroll.add(rollSnapshot.child("rollNo").getValue().toString());
                    adapter.notifyDataSetChanged();



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = txttitle.getText().toString();
                String roll = txt_roll.getText().toString();

                nref.child(title).child("availability").setValue("No");
                mref.child(roll).child("BorrowedBooks").setValue(title);
                Toast.makeText(BorrowABook.this, "Transaction Successfully!", Toast.LENGTH_SHORT).show();
                finish();



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