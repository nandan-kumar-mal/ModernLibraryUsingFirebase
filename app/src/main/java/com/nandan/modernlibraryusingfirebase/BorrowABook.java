package com.nandan.modernlibraryusingfirebase;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private TextView txttitle, txtauthor, txtcategory, txtedition,txt_name,txt_roll,txt_year,Copy,nCopy;
    private AutoCompleteTextView acedtxtRollNo;
    ArrayList<String> studentsroll;
    ArrayAdapter<String> adapter;
    private DatabaseReference mref, nref,bref,cref,href;
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
        Copy= findViewById(R.id.Copies);
        nCopy= findViewById(R.id.nCopies);



        
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


            }

        });

        showBookDetails();

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot rollSnapshot: snapshot.getChildren()){

                    try {
                        studentsroll.add(rollSnapshot.child("rollNo").getValue().toString());
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }


                    adapter.notifyDataSetChanged();



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        btnFin.setOnClickListener(new View.OnClickListener() {
            Integer nCopies;

            @Override
            public void onClick(View v) {
                String title = txttitle.getText().toString();
                String roll = txt_roll.getText().toString();
                bref = FirebaseDatabase.getInstance().getReference("User").child(roll).child("BorrowedBooks");
                bref.setValue(title);

               cref=FirebaseDatabase.getInstance().getReference("Books").child(title);


               // Query query = mref.child(roll).child("BorrowedBooks");
//                bref.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//                        for (DataSnapshot data:snapshot.getChildren())
//                        {
//                            DataSnapshot  Borrow_1 = data.child("Borrow_1");
//                            String Borrow1 = Borrow_1.getValue(String.class);
//
//                            if(Borrow1 == null)
//                            {
//                                 data.child("Borrow_1").getRef().setValue(title);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//                    }
//                });

                 String rCopy = nCopy.getText().toString();
                 int iCopy = Integer.parseInt(rCopy);
                 iCopy--;
                cref.child("copies").setValue(String.valueOf(iCopy));

                if(iCopy<2)
                {
                    nref.child(title).child("availability").setValue("No");
                }

                else
                {
                    nref.child(title).child("availability").setValue("Yes");
                }






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
        String bookcopy = intent.getStringExtra("copies");



        txttitle.setText(booktitle);
        txtauthor.setText(bookauthor);
        txtedition.setText(bookedition);
        txtcategory.setText(bookcategory);
        nCopy.setText(bookcopy);

    }


}