package com.nandan.modernlibraryusingfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_welcome extends AppCompatActivity {

    private Button register;
    private Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

       register = findViewById(R.id.RegisterBtn);
       login = findViewById(R.id.LoginBtn);

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(activity_welcome.this,Register.class));
               finish();
           }
       });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(activity_welcome.this,Login.class));
               finish();
           }
       });
    }
}