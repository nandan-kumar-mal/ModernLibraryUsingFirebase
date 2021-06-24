package com.nandan.modernlibraryusingfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button register;
    private FirebaseAuth fauth;

    AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.iEmail);
        password = findViewById(R.id.iPassword);
        register = findViewById(R.id.RegBtn);
        fauth = FirebaseAuth.getInstance();
        autoCompleteTextView = findViewById(R.id.actView);

        String[] studentClass = getResources().getStringArray(R.array.Class);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.signup_dropdown_item, studentClass);
        autoCompleteTextView.setAdapter(arrayAdapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if ((TextUtils.isEmpty(txt_email))||(TextUtils.isEmpty(txt_password))){
                    Toast.makeText(Register.this,"Empty Credentialls",Toast.LENGTH_LONG).show();
                }else if (txt_password.length()<6){
                    Toast.makeText(Register.this, "Password too short!", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(txt_email,txt_password);
                }

            }
        });
    }

    private void registerUser(String email, String password) {

        fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()){
                  Toast.makeText(Register.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                  startActivity(new Intent(Register.this, activity_welcome.class));
                  finish();
              }else{
                    Toast.makeText(Register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}