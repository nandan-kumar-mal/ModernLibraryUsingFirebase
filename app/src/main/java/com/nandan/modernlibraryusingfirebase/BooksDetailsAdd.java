package com.nandan.modernlibraryusingfirebase;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class BooksDetailsAdd extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText edtxtAuth, edtEd;
    private TextView txtBookName;
    private Spinner spin;
    private Button btnFin, btn_ChooseImg;
    private ImageView imgview;
    private Uri mImageUri;
    private ProgressBar mprogressbar;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    StorageReference storageReference;
    String cate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_details_add);

        edtxtAuth = findViewById(R.id.edtxtAuth);
        spin = findViewById(R.id.spin);
        edtEd = findViewById(R.id.edtTxtEd);
        mprogressbar = findViewById(R.id.progress_Bar);
        String[] Category = getResources().getStringArray(R.array.Category);
        ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_dropdown_item, Category);
        adapter.notifyDataSetChanged();
// Specify the layout to use when the list of choices appears

// Apply the adapter to the spinner
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cate = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        txtBookName = findViewById(R.id.txtBookName);
        btnFin = findViewById(R.id.btnFin);
        btn_ChooseImg = findViewById(R.id.btn_Choose);
        imgview = findViewById(R.id.iconimg);

        storageReference = FirebaseStorage.getInstance().getReference();

        btn_ChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        txtBookName.setText(getIntent().getStringExtra("title"));
        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               uploadtoFirebase();
            }
    });
    }
    private void uploadtoFirebase() {
        String txt_Author = edtxtAuth.getText().toString();
        String txt_Cat = cate;
        String txt_Edtion = edtEd.getText().toString();

        String txt_title = txtBookName.getText().toString();

        if ((TextUtils.isEmpty(txt_Author))||(TextUtils.isEmpty(txt_Cat))||(TextUtils.isEmpty(txt_Edtion))||mImageUri==null){
            Toast.makeText(BooksDetailsAdd.this,"Empty Credentials",Toast.LENGTH_LONG).show();
        }
        else {

            StorageReference fileref = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
            fileref.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mprogressbar.setProgress(0);
                        }
                    }, 500);
                    fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference("Books");
                            AddBookHelperClass helperClass = new AddBookHelperClass(txt_title, txt_Author, txt_Cat, txt_Edtion, uri.toString());
                            reference.child(txt_title).setValue(helperClass);
                            reference.child(txt_title).child("availability").setValue("Yes");

                            finish();
                            Toast.makeText(BooksDetailsAdd.this, "Uploaded Successfully!", Toast.LENGTH_SHORT).show();

                        }


                    });

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    mprogressbar.setProgress((int) progress);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull  Exception e) {
                    Toast.makeText(BooksDetailsAdd.this, "Uploading Failed!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData() != null){
            mImageUri = data.getData();
            imgview.setImageURI(mImageUri);
        }
    }
}