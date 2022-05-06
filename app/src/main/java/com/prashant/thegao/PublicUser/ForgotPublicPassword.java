package com.prashant.thegao.PublicUser;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.prashant.thegao.R;

public class ForgotPublicPassword extends AppCompatActivity {

    EditText pwdresetEDTX;
    Button pwdresetBTN;
    FirebaseAuth authProfile;
    ProgressBar progressBar;
   private final static String TAG = "ForgotPublicPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_public_password);
       pwdresetEDTX=findViewById(R.id.passord_reset_EDTX);
       pwdresetBTN=findViewById(R.id.passord_reset_BTN);
        progressBar=findViewById(R.id.progressBar);


        pwdresetBTN.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String email = pwdresetEDTX.getText().toString();
               if (TextUtils.isEmpty(email)){
                   pwdresetEDTX.setError("Please Enter Your Email");
                   pwdresetEDTX.requestFocus();
               }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                   pwdresetEDTX.setError("Please Enter Valid Email");
                   pwdresetEDTX.requestFocus();
               } else {
                   progressBar.setVisibility(View.VISIBLE);
                   resetPassword(email);
               }
           }
       });

    }

    private void resetPassword(String email) {
        authProfile = FirebaseAuth.getInstance();
        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPublicPassword.this, "Please check your mail inbox for reset link", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ForgotPublicPassword.this, PublicLoginPage.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                            | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else{
                    try{
                       throw task.getException();
                    }  catch(FirebaseAuthInvalidUserException e){
                        pwdresetEDTX.setError("User Does Not Exists or is no longer valid please register again ");
                    }  catch(Exception e){
                        Log.e(TAG,e.getMessage());
                        Toast.makeText(ForgotPublicPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(ForgotPublicPassword.this, "Some Thing Went Wrong Or too Many attempts !!", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}