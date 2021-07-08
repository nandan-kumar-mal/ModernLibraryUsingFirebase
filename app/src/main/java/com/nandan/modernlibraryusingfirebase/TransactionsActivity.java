
package com.nandan.modernlibraryusingfirebase;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class TransactionsActivity extends AppCompatActivity {
    CodeScanner codeScan;
    CodeScannerView scanView;
    TextView resultData;
    Button btnBor, btnRetn;
    ImageView imgClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        scanView = findViewById(R.id.scannerView);
        codeScan = new CodeScanner(this, scanView);
        resultData = findViewById(R.id.txtResult);
        btnBor = findViewById(R.id.btnBorrow);
        btnRetn = findViewById(R.id.btnReturn);
        imgClose = findViewById(R.id.imgclose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultData.setText("Title");

            }
        });

        btnBor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = resultData.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Books");

                Query checkBook = reference.orderByChild("title").equalTo(title);
                checkBook.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.exists()){
                            String author = snapshot.child(title).child("author").getValue(String.class);
                            String category = snapshot.child(title).child("category").getValue(String.class);
                            String edition = snapshot.child(title).child("edition").getValue(String.class);

                            Intent intent = new Intent(getApplicationContext(), BorrowABook.class);
                            intent.putExtra("title", title);
                            intent.putExtra("author", author);
                            intent.putExtra("edition", edition);
                            intent.putExtra("category", category);
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(TransactionsActivity.this, "Book is not available", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(TransactionsActivity.this, "Book is not available", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });

        btnRetn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransactionsActivity.this, ReturnABook.class));
            }
        });

        codeScan.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultData.setText(result.getText());
                    }
                });
            }
        });
        scanView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScan.startPreview();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestForCamera();

    }

    public void requestForCamera() {
        Dexter.withContext(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                codeScan.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(TransactionsActivity.this, "Camera Permission is Required.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken Token) {
                Token.continuePermissionRequest();
            }

        }).check();
    }

}



