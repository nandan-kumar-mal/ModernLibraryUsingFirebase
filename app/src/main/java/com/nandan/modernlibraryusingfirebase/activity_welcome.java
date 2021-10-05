package com.nandan.modernlibraryusingfirebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String rmChecked=preferences.getString("rememberMe"," ");
        if (rmChecked.equals("true")){
            startActivity(new Intent(activity_welcome.this,MainActivity.class));
        }else if (rmChecked.equals("false")){
            Toast.makeText(this, "Please login!", Toast.LENGTH_SHORT).show();
        }

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

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}