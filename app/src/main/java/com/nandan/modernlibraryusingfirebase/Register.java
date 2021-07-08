package com.nandan.modernlibraryusingfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private TextInputLayout fullName;
    private TextInputLayout rollNo;
    private TextInputLayout email;
    private TextInputLayout password;
    private Button register;
    private FirebaseAuth fauth;
    FirebaseDatabase rootNode;
    DatabaseReference user_node ;



    private AutoCompleteTextView autoCompleteTextView;

    public Register() { }
    String txt_fullName,txt_rollNo,txt_Class,txt_email,txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.reg_fullName);
        rollNo = findViewById(R.id.reg_rollNo);
        email = findViewById(R.id.reg_Email);
        password = findViewById(R.id.reg_Password);
        register = findViewById(R.id.RegBtn);
        fauth = FirebaseAuth.getInstance();
        autoCompleteTextView = findViewById(R.id.actView);

        String[] studentClass = getResources().getStringArray(R.array.Class);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.signup_dropdown_item, studentClass);
        autoCompleteTextView.setAdapter(arrayAdapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(!ValidateFullName()|!ValidateRollNo()|!ValidateEmail()|!ValidatePassword()){
                    return;
                }*/

                 txt_fullName = fullName.getEditText().getText().toString();
                 txt_rollNo = rollNo.getEditText().getText().toString();
                 txt_Class = autoCompleteTextView.getText().toString();
                 txt_email = email.getEditText().getText().toString();
                 txt_password = password.getEditText().getText().toString();


                if ((TextUtils.isEmpty(txt_email)) || (TextUtils.isEmpty(txt_password))) {
                    Toast.makeText(Register.this, "Empty Credentialls", Toast.LENGTH_LONG).show();
                } else if (txt_password.length() < 6) {
                    Toast.makeText(Register.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txt_email, txt_password);
                    storeUserData();
                }

            }
        });
    }

    public void storeUserData() {

         rootNode=FirebaseDatabase.getInstance();
         user_node=rootNode.getReference().child("User");

         UserHelperClass addNewUser = new UserHelperClass(txt_fullName,txt_rollNo,txt_Class,txt_email,txt_password);

         user_node.setValue(addNewUser);



    }

    private void registerUser(String email, String password) {

        fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, activity_welcome.class));
                    finish();
                } else {
                    Toast.makeText(Register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean ValidateFullName() {
        String val = fullName.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            fullName.setError("Field cannot be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;

        }
    }

    private boolean ValidateRollNo() {
        String val = rollNo.getEditText().getText().toString().trim();
        //String checkRollNo = ;
        if (val.isEmpty()) {
            rollNo.setError("Field cannot be empty");
            return false;
        }
        /*else if (!val.matches(checkRollNo)){
            rollNo.setError("This field should contain only number 0-9");
            return false;
        }*/
        else {
            rollNo.setError(null);
            rollNo.setErrorEnabled(false);
            return true;

        }
    }

    private boolean ValidateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;

        }
    }

    /*private boolean ValidatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                ".{6,}" +               //at least 6 characters
                "$";
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Password should contain");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;

        }
    }*/


}