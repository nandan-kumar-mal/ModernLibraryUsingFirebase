package com.nandan.modernlibraryusingfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Booklist extends AppCompatActivity {

    RecyclerView recv;
    BookListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);
        recv = findViewById(R.id.revView);
        recv.setLayoutManager(new LinearLayoutManager(this));
         adapter = new BookListAdapter(dataqueue());
         recv.setAdapter(adapter);
    }

    public ArrayList<Model> dataqueue()
        {
        ArrayList<Model> holder = new ArrayList<>();
        Model obj1 = new Model();
        obj1.setTitle("Semester I");
        obj1.setDesc("All the books needed in this semester");
        obj1.setImgname(R.drawable.book138);
        holder.add(obj1);


        Model obj2 = new Model();
        obj2.setTitle("Semester II");
        obj2.setDesc("All the books needed in this semester");
        obj2.setImgname(R.drawable.sem2);
        holder.add(obj2);

        Model obj3 = new Model();
        obj3.setTitle("Semester III");
        obj3.setDesc("All the books needed in this semester");
        obj3.setImgname(R.drawable.book138);
        holder.add(obj3);

        Model obj4 = new Model();
        obj4.setTitle("Semester IV");
        obj4.setDesc("All the books needed in this semester");
        obj4.setImgname(R.drawable.book138);
        holder.add(obj4);

        Model obj5 = new Model();
        obj5.setTitle("Semester V");
        obj5.setDesc("All the books needed in this semester");
        obj5.setImgname(R.drawable.book138);
        holder.add(obj5);

        Model obj6 = new Model();
        obj6.setTitle("Semester VI");
        obj6.setDesc("All the books needed in this semester");
        obj6.setImgname(R.drawable.book138);
        holder.add(obj6);

        Model obj7 = new Model();
        obj7.setTitle("Miscellaneous");
        obj7.setDesc("Miscellaneous books.");
        obj7.setImgname(R.drawable.misc);
        holder.add(obj7);

        return holder;



    }
}