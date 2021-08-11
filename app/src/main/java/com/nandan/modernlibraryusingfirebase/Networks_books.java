package com.nandan.modernlibraryusingfirebase;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Networks_books extends AppCompatActivity {

    private RecyclerView contactRecView;
    private DatabaseReference database;
    ArrayList<Contact> contacts;
    AvailableBooksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester__i_books);
        contactRecView = findViewById(R.id.booksRecView);
        contactRecView.setHasFixedSize(true);

        contacts = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Books");
        Query query = FirebaseDatabase.getInstance().getReference("Books")
                .orderByChild("category").equalTo("Semester I");
       query.addListenerForSingleValueEvent(valueEventListener);
  //      database.addListenerForSingleValueEvent(valueEventListener);

        adapter = new AvailableBooksAdapter(this,contacts);

        contactRecView.setAdapter(adapter);
        contactRecView.setLayoutManager(new LinearLayoutManager(this));
    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

            if(snapshot.exists()){
                contacts.clear();
                for(DataSnapshot data: snapshot.getChildren()){

                    Contact contact = data.getValue(Contact.class);
                    contacts.add(contact);

                    }
                adapter.notifyDataSetChanged();
                }


            }


        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {
            Toast.makeText(Networks_books.this, "Cannot Load!", Toast.LENGTH_SHORT).show();

        }
    };
}