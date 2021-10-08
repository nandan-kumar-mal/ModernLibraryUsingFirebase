package com.nandan.modernlibraryusingfirebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private Button goRegister;
    private Button loginAsAdmin;
    private FirebaseAuth fauth;
    private ImageView loginBackButton;
    private CheckBox rememberMe;


    FirebaseDatabase rootNode;
    DatabaseReference user_node ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.iEmail);
        password = findViewById(R.id.iPassword);
        login = findViewById(R.id.LoginBtn);
        fauth = FirebaseAuth.getInstance();
        loginBackButton = findViewById(R.id.login_backButton);
        goRegister = findViewById(R.id.GoToRegister);
        rememberMe = findViewById(R.id.rememberMe);
        loginAsAdmin = findViewById(R.id.loginAsAdmin);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                loginUser(txt_email, txt_password);

            }
        });
        loginBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, activity_welcome.class));
                finish();
            }
        });
        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
                finish();
            }
        });
        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("rememberMe", "true");
                    editor.apply();
                    Toast.makeText(Login.this, "Checked", Toast.LENGTH_SHORT).show();

                } else if (!buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("rememberMe", "false");
                    editor.apply();
                    Toast.makeText(Login.this, "unChecked", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        loginAsAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if(buttonView.isChecked()) {
//                    Toast.makeText(Login.this, "Checked", Toast.LENGTH_SHORT).show();
//                    user_node.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                            email_fromdb=snapshot.child("email").getValue(String.class);
//                            password_fromdb=snapshot.child("password").getValue(String.class);
//                            if(email_fromdb.equals(email_input) && password_fromdb.equals(password_input))
//                            {
//                               admin= true;
//
//                            }
//                            else
//                            {
//                                admin=false;
//                            }
//                        }
//                        @Override
//                        public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//                        }
//                    });
//
//                }
//
//            }
//        });

        loginAsAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_input = email.getText().toString();
                String password_input = password.getText().toString();


                rootNode = FirebaseDatabase.getInstance();
                user_node = rootNode.getReference("User").child("admin@");
                user_node.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String email_fromdb = snapshot.child("email").getValue(String.class);
                            String password_fromdb = snapshot.child("password").getValue(String.class);
                            if (email_fromdb.equals(email_input) && password_fromdb.equals(password_input)) {
                                startActivity(new Intent(Login.this, MainActivity.class));
                                Toast.makeText(Login.this, "Admin Login Successful!", Toast.LENGTH_SHORT).show();
                            } else {
                                password.setError("password Wrong");
                            }
                        } else {
                            email.setError("There is no such admin");
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    }
                });

            }
        });
    }


    private void loginUser(String email, String password) {

            fauth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    Toast.makeText(Login.this, "User Login Successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,UserActivity.class));

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

    }






    @Override
    public void onBackPressed() {
        startActivity(new Intent(Login.this,activity_welcome.class));
        finish();
    }
}