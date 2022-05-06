package com.prashant.thegao.Dashboard;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.prashant.thegao.R;
import com.squareup.picasso.Picasso;

public class UploadProfilePic extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageView imageViewUploadPic;
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private StorageReference storageReference;
    private final int  PICK_IMAGE_REQUEST = 1;
    private Uri uriImage;



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_pic);

        authProfile = FirebaseAuth.getInstance();

        Button buttonuploadPicChoose = findViewById(R.id.upload_pic_choose_button);
        Button buttonuploadPic = findViewById(R.id.upload_pic_button);

        imageViewUploadPic = findViewById(R.id.imageView_profile_dp);

        progressBar = findViewById(R.id.progressBar);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();

        storageReference = FirebaseStorage.getInstance().getReference("Profile Pics");
        Uri uri = firebaseUser.getPhotoUrl();
        Picasso.get().load(uri).into(imageViewUploadPic);

        buttonuploadPicChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();

            }
        });

        buttonuploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadPic();
                progressBar.setVisibility(View.VISIBLE);


            }
        });


    }

    private void UploadPic() {
        if(uriImage !=null){
            StorageReference fileReference = storageReference.child(authProfile.getCurrentUser().getUid() + "."
            +getFileExtension(uriImage));
            fileReference.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUri = uri;
                            firebaseUser = authProfile.getCurrentUser();
                            UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder().setPhotoUri(downloadUri).build();
                            firebaseUser.updateProfile(profileUpdate);
                        }
                    });
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(UploadProfilePic.this, "Profile Pic Upload Successful", Toast.LENGTH_SHORT).show();

                   // Intent intent = new Intent(UploadProfilePic.this,Dashboard.class);

                    AlertDialog.Builder builder= new AlertDialog.Builder(UploadProfilePic.this);
                    builder.setTitle("Upload Successful");
                    builder.setIcon(R.drawable.wink);
                    builder.setMessage("Your profile picture was uploaded ✅").setCancelable(false)
                            .setPositiveButton("Go Back to Dashboard", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(UploadProfilePic.this,Dashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UploadProfilePic.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please choose Any Pic", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriImage = data.getData();
            imageViewUploadPic.setImageURI(uriImage);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
