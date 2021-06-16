package com.nandan.modernlibraryusingfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Semester_III_books extends AppCompatActivity {

    private RecyclerView contactRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester__i_books);
        contactRecView = findViewById(R.id.booksRecView);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Operating System Concepts", "Galvin",true,"https://images-na.ssl-images-amazon.com/images/I/51spVw9pGKL.jpg"));
        AvailableBooksAdapter adapter = new AvailableBooksAdapter(this);
        adapter.setContact(contacts);
        contactRecView.setAdapter(adapter);
        contactRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}