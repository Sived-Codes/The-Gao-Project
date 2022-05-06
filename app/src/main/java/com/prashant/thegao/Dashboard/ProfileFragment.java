package com.prashant.thegao.Dashboard;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prashant.thegao.PublicUser.ReadWriteUserDetails;
import com.prashant.thegao.R;
import com.squareup.picasso.Picasso;



public class ProfileFragment extends Fragment {

    private TextView TVwelcome,Tvfullname,TVemail,TVphone,Tvvillage;
    private ProgressBar progressBar;
    public String fullname,email,phone,village;
    ImageView imageView;
    FirebaseAuth authProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        imageView=view.findViewById(R.id.imageView_ProfileID);
        TVwelcome= view.findViewById(R.id.textViewWLCM);
        Tvfullname= view.findViewById(R.id.textViewFullName);
        TVemail= view.findViewById(R.id.textView_show_email);
        TVphone= view.findViewById(R.id.textView_show_phone);
        Tvvillage= view.findViewById(R.id.textView_show_village);
        progressBar= view.findViewById(R.id.progressBar);
        authProfile=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();
        getActivity().setTitle("Profile");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UploadProfilePic.class);
                startActivity(intent);
            }
        });

        if(firebaseUser== null){
            Toast.makeText(getContext(), "Something Wrong User Detail not Available", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);

        } return view;
    }

    private void checkIfEmailVerified(FirebaseUser firebaseUser) {
        if(!firebaseUser.isEmailVerified()){
            showAlertDialog();
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Email Verified nahi hai ");
        builder.setMessage("Please verified Your email address");
        builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

     private void showUserProfile(FirebaseUser firebaseUser) {
         String userID = firebaseUser.getUid();
         DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Public Users");
         referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
             @SuppressLint("SetTextI18n")
             @Override
             public void onDataChange( DataSnapshot snapshot) {
                 ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                 if (readUserDetails != null) {
                     fullname = readUserDetails.A_FullName;
                     email = firebaseUser.getEmail();
                     phone = readUserDetails.C_Mobile;
                     village = readUserDetails.E_Village;

                     TVwelcome.setText("Welcome, " + fullname + "!");
                     Tvfullname.setText(fullname);
                     TVemail.setText(email);
                     TVphone.setText(phone);
                     Tvvillage.setText(village);

                     Uri uri = firebaseUser.getPhotoUrl();

                     Picasso.get().load(uri).into(imageView);

                 } else {
                     Toast.makeText(getContext(), "Something Went Wrong !!!", Toast.LENGTH_SHORT).show();
                 }

                 progressBar.setVisibility(View.GONE);

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Toast.makeText(getContext(), "Something Wrong User !!!", Toast.LENGTH_SHORT).show();
                 progressBar.setVisibility(View.GONE);

             }
         });
     }


}