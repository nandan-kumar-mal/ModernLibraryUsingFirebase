package com.nandan.modernlibraryusingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BooksDetailsAdd extends AppCompatActivity {

    private EditText edtxtAuth, edtxtCat, edtEd;
    private TextView txtBookName;
    private Button btnFin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_details_add);

        edtxtAuth = findViewById(R.id.edtxtAuth);
        edtxtCat = findViewById(R.id.edtTxtCat);
        edtEd = findViewById(R.id.edtTxtEd);

        txtBookName = findViewById(R.id.txtBookName);
        btnFin = findViewById(R.id.btnFin);

        txtBookName.setText(getIntent().getStringExtra("title"));
        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_Author = edtxtAuth.getText().toString();
                String txt_Cat = edtxtCat.getText().toString();
                String txt_Edtion = edtEd.getText().toString();


                if ((TextUtils.isEmpty(txt_Author))||(TextUtils.isEmpty(txt_Cat))||(TextUtils.isEmpty(txt_Edtion))){
                    Toast.makeText(BooksDetailsAdd.this,"Empty Credentialls",Toast.LENGTH_LONG).show();

                }

                else{
                    rootNode  = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Books");

                    String txt_title = txtBookName.getText().toString();
                    AddBookHelperClass helperClass = new AddBookHelperClass(txt_title, txt_Author, txt_Cat, txt_Edtion);
                    reference.child(txt_title).setValue(helperClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(BooksDetailsAdd.this, "Successfully Added in the Database", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(BooksDetailsAdd.this,MainActivity.class));
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(BooksDetailsAdd.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });



                }
            }
        });


    }
} 