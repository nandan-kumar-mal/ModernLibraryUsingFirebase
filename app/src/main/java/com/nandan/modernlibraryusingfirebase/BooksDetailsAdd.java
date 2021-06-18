package com.nandan.modernlibraryusingfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BooksDetailsAdd extends AppCompatActivity {

    private EditText edtxtAuth, edtxtCat;
    private TextView txtBookName;
    private Button btnFin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_details_add);

        edtxtAuth = findViewById(R.id.edtxtAuth);
        edtxtCat = findViewById(R.id.edtTxtCat);

        txtBookName = findViewById(R.id.txtBookName);
        btnFin = findViewById(R.id.btnFin);

        txtBookName.setText(getIntent().getStringExtra("title"));
        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_Author = edtxtAuth.getText().toString();
                String txt_Cat = edtxtCat.getText().toString();

                if ((TextUtils.isEmpty(txt_Author))||(TextUtils.isEmpty(txt_Cat))){
                    Toast.makeText(BooksDetailsAdd.this,"Empty Credentialls",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
} 