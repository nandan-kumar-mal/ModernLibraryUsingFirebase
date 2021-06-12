package com.nandan.modernlibraryusingfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Semester_I_books extends AppCompatActivity {

    private RecyclerView contactRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester__i_books);
        contactRecView = findViewById(R.id.booksRecView);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Programming in ANSI C", "E.Balagurusamy",true,"https://images-na.ssl-images-amazon.com/images/I/61jRrvKXV0L.jpg"));
        contacts.add(new Contact("Discrete Mathematics ", "Seymour Lipschulz and Marc lars Lipson",true,"https://images-na.ssl-images-amazon.com/images/I/71kohGylsyL.jpg"));
        contacts.add(new Contact(":et us C", "Yashavant Kanetkar",true,"https://images-na.ssl-images-amazon.com/images/I/51H52tr3KHL.jpg"));
        contacts.add(new Contact("Programming with Java", "E.Balagurusamy",true,"https://www.textbooks.solutions/wp-content/archivos/2019/09/programming-with-java-e-balagurusamy-3e.jpg"));
        contacts.add(new Contact("Data Communications and Networking", "Behrouz A. Forouzan",true,"https://images-na.ssl-images-amazon.com/images/I/41LBD-XtzYL.jpg"));
        contacts.add(new Contact("Object Oriented Programming with C++", "E.Balagurusamy",true,"https://images-na.ssl-images-amazon.com/images/I/41F8dUDF2CL._SX258_BO1,204,203,200_.jpg"));
        contacts.add(new Contact("Object Oriented Programming with C++", "E.Balagurusamy",true,"https://images-na.ssl-images-amazon.com/images/I/41LBD-XtzYL.jpg"));
        contacts.add(new Contact("Object Oriented Programming with C++", "E.Balagurusamy",true,"https://images-na.ssl-images-amazon.com/images/I/41LBD-XtzYL.jpg"));

        AvailableBooksAdapter adapter = new AvailableBooksAdapter();
        adapter.setContact(contacts);
        contactRecView.setAdapter(adapter);
        contactRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}