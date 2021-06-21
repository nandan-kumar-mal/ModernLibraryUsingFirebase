package com.nandan.modernlibraryusingfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BorrowABook extends AppCompatActivity {

    private TextView txttitle, txtauthor, txtcategory, txtedition;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_a_book);

        txttitle = findViewById(R.id.txt_title);
        txtauthor = findViewById(R.id.txt_Author);
        txtcategory = findViewById(R.id.txt_Cat);
        txtedition = findViewById(R.id.txt_Edition);

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