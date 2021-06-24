package com.nandan.modernlibraryusingfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BorrowABook extends AppCompatActivity {

    private TextView txttitle, txtauthor, txtcategory, txtedition;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_a_book);

        txttitle = findViewById(R.id.Title);
        txtauthor = findViewById(R.id.Author);
        txtcategory = findViewById(R.id.Categ);
        txtedition = findViewById(R.id.Edition);

        showBookDetails();

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