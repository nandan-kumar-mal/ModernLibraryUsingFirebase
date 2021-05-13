package com.nandan.modernlibraryusingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

     private Toolbar toolbar;
     private NavigationView naviView;
     private DrawerLayout drawer;
     private CardView cardTrans, cardAbout, cardAdd, cardBooklist;



    //FirebaseDatabase  database;
    //DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        naviView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        cardTrans = findViewById(R.id.cardTrans);
        cardBooklist = findViewById(R.id.cardBooklist);
        cardAdd = findViewById(R.id.cardAdd);
        cardAbout = findViewById(R.id.cardAbout);
        cardTrans.setOnClickListener(this);
        cardAbout.setOnClickListener(this);
        cardAdd.setOnClickListener(this);
        cardBooklist.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        naviView.setNavigationItemSelectedListener(this);




        //database= FirebaseDatabase.getInstance();
       // dbRef=database.getReference("Student");

        FirebaseDatabase.getInstance().getReference().child("Teachers").child("Com_Science").push().setValue("Dr. Mema");

       // dbRef.setValue("Nandan");


            

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
        super.onBackPressed();
    }
}

    @Override
    public void onClick(View v) {

        Intent i;
        switch(v.getId()){
            case R.id.cardTrans:
                i = new Intent(this, TransactionsActivity.class);
                startActivity(i);
                break;

            case R.id.cardBooklist:
                i = new Intent(this, Booklist.class);
                startActivity(i);
                break;

            case R.id.cardAdd:
                i = new Intent(this, BookAdd.class);
                startActivity(i);
                break;

            case R.id.cardAbout:
                i = new Intent(this, About_us.class);
                startActivity(i);
                break;

        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}