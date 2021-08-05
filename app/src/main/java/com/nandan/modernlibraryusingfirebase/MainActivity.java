package com.nandan.modernlibraryusingfirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

     private Toolbar toolbar;
     private NavigationView naviView;
     private DrawerLayout drawer;
     private CardView cardTrans, cardAbout, cardAdd, cardBooklist;
    private ImageView menuIcon;


    //FirebaseDatabase  database;
    //DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuIcon = findViewById(R.id.navicon);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        naviView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        cardTrans = findViewById(R.id.cardTrans);
        cardBooklist = findViewById(R.id.cardBooklist);
        cardAdd = findViewById(R.id.cardAdd);
        cardAbout = findViewById(R.id.cardAbout);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        cardTrans.setOnClickListener(this);
        cardAbout.setOnClickListener(this);
        cardAdd.setOnClickListener(this);
        cardBooklist.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        naviView.setNavigationItemSelectedListener(this);
        toggle.setToolbarNavigationClickListener(this);






            

    }
    

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton("no", null)
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.onBackPressed();
                        }
                    } ).create().show();

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(this,MainActivity.class));
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ProfileFragment()).commit();
                break;
            case R.id.nav_noti:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new NotificationsFragment()).commit();
                break;
            case R.id.nav_logout:
                AlertDialog.Builder logout_alert = new AlertDialog.Builder(this);
                logout_alert.setMessage("Are you sure you want to Logout?")
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logout();

                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create().show();

                break;
            case R.id.nav_share:
                Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rep:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ReportFragment()).commit();
                break;


        }
        return true;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,activity_welcome.class));
        finish();
    }
}