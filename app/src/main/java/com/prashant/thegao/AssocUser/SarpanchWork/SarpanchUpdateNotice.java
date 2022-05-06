package com.prashant.thegao.AssocUser.SarpanchWork;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.prashant.thegao.R;
import com.squareup.picasso.Picasso;

public class SarpanchUpdateNotice extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView mImageViewChoose;
    private Button mButtonupload;
    private TextView mEdittext;
    private ProgressBar progressBar;
    private Uri mImageuri;
    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarpanch_update_notice);
        mImageViewChoose = findViewById(R.id.SarpanchUploadNoticImageID);
        mButtonupload = findViewById(R.id.SarpanchSendNoticeBTN);
        mEdittext = findViewById(R.id.SarpanchUploadNoticeEditText);
        progressBar = findViewById(R.id.progressBar);

        getSupportActionBar().setTitle("Upload Notice ");

        mStorageReference= FirebaseStorage.getInstance().getReference("Assoc User").child("Sarpanch").child("Palakhed").child("Admin").child("A-Notices");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Assoc User").child("Sarpanch").child("Palakhed").child("Admin").child("A-Notices");

        mImageViewChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenImageChooser();
            }
        });

        mButtonupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadFile();
                progressBar.setVisibility(View.VISIBLE);
            }
        });


    }


    private void OpenImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageuri = data.getData();
            Picasso.get().load(mImageuri).into(mImageViewChoose);
        }
    }
    private  String getFileExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void UploadFile() {
        if(mImageuri != null){

            StorageReference filereference = mStorageReference.child(System.currentTimeMillis()+"."+getFileExt(mImageuri));

            filereference.putFile(mImageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(SarpanchUpdateNotice.this, "Notice Successfully uploaded", Toast.LENGTH_SHORT).show();  AlertDialog.Builder builder= new AlertDialog.Builder(SarpanchUpdateNotice.this);
                    builder.setTitle("Done");
                    builder.setIcon(R.drawable.check);
                    builder.setMessage("Notice Successfully uploaded ✅").setCancelable(false)
                            .setPositiveButton("Go Back to Dashboard", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(SarpanchUpdateNotice.this, SarpanchDashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


                    Upload upload = new Upload(mEdittext.getText().toString().trim(),taskSnapshot.getUploadSessionUri().toString());
                    String uploadId = mDatabaseReference.push().getKey();
                    mDatabaseReference.child(uploadId).setValue(upload);
                    progressBar.setVisibility(View.GONE);

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SarpanchUpdateNotice.this, e .getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    });
        }else{
            Toast.makeText(this, "No image Selected", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);

        }
    }


}