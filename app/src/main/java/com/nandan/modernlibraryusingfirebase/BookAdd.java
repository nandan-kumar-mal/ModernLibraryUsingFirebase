package com.nandan.modernlibraryusingfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileOutputStream;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class BookAdd extends AppCompatActivity {

    // variables for imageview, edittext,
    // button, bitmap and qrencoder.
    private ImageView qrCodeIV;
    private EditText dataEdt;
    private Button generateQrBtn, addBookBtn, btnSave;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);

        qrCodeIV = findViewById(R.id.idIVQrcode);
        dataEdt = findViewById(R.id.idEdt);
        generateQrBtn = findViewById(R.id.idBtnGenerateQR);
        addBookBtn = findViewById(R.id.idBtnAddBook);
        btnSave = findViewById(R.id.btnSave);
        ActivityCompat.requestPermissions(BookAdd.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(BookAdd.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        generateQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(dataEdt.getText().toString())){
                    Toast.makeText(BookAdd.this, "Enter some text to generate QR Code", Toast.LENGTH_SHORT).show();
                }
                else{
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();

                    Point point = new Point();
                    display.getSize(point);

                    int width = point.x;
                    int height = point.y;

                    int dimen = width < height ? width : height;
                    dimen  = dimen * 3/4;

                    qrgEncoder = new QRGEncoder(dataEdt.getText().toString(),null, QRGContents.Type.TEXT, dimen);
                    try{
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrCodeIV.setImageBitmap(bitmap);

                    } catch (WriterException e) {
                        Log.e("Tag", e.toString());
                    }


                }
            }
        });
        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookAdd.this, BooksDetailsAdd.class);
                intent.putExtra("title", dataEdt.getText().toString());

                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savetoGallery();

            }
        });
    }

    private void savetoGallery() {

        BitmapDrawable bitmapDrawable = (BitmapDrawable) qrCodeIV.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        FileOutputStream outputStream = null;
        File file = Environment.getExternalStorageDirectory();
        File dir = new File(file.getAbsolutePath() + "/QR images");
        dir.mkdir();

        String filename = String.format("%d.png",System.currentTimeMillis());
        File outfile = new File(dir, filename);
        try{
           outputStream = new FileOutputStream(outfile);

        }catch (Exception e){
            e.printStackTrace();

        }

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        try {
            outputStream.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();

        }

    }


}