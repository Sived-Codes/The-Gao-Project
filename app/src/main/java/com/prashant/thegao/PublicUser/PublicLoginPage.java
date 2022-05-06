package com.prashant.thegao.PublicUser;
import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.prashant.thegao.Dashboard.Dashboard;
import com.prashant.thegao.R;

public class PublicLoginPage extends AppCompatActivity {

    EditText email,newpwdc;
    Button loginBtn, registerbtn1;
    FirebaseAuth authProfile;
    ProgressBar progressBar;
    TextView forgetpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_login_page);

        email = findViewById(R.id.Pemail);
        newpwdc = findViewById(R.id.Ppassword);
        loginBtn = findViewById(R.id.PloginBTN);
        registerbtn1 = findViewById(R.id.PregisterBtn);
        progressBar = findViewById(R.id.progressBar);
        authProfile = FirebaseAuth.getInstance();
        forgetpwd = findViewById(R.id.PforgotPwd);


        forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PublicLoginPage.this, ForgotPublicPassword.class);
                startActivity(intent);

            }
        });
        registerbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PublicLoginPage.this, PublicSignupPage.class);
                startActivity(intent);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailTxt = email.getText().toString();
                final String passwordTxt = newpwdc.getText().toString();

                if (TextUtils.isEmpty(email.getText().toString())) {
                    email.setError("Enter Email Adrress");
                    email.requestFocus();

                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()) {
                    email.setError("Enter Valid E-Mail Address");
                    email.requestFocus();

                } else if (TextUtils.isEmpty(newpwdc.getText().toString())) {
                    newpwdc.setError("Enter Password");
                    newpwdc.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    loginUser(emailTxt,passwordTxt);
                }
            }
        });


    }

    private void loginUser(String emailTxt, String passwordTxt) {
        authProfile.signInWithEmailAndPassword(emailTxt, passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = authProfile.getCurrentUser();
                    if(firebaseUser.isEmailVerified()){
                        Toast.makeText(PublicLoginPage.this, "User Logged IN ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PublicLoginPage.this, Dashboard.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        firebaseUser.sendEmailVerification();
                        authProfile.getInstance().signOut();
                        showAlertDialog();
                    }


                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e){
                        email.setError("Email is Not Registered");
                        email.requestFocus();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        email.setError("Please check Email and Password");
                        email.requestFocus();
                    }catch (Exception e){

                        Log.e(TAG,e.getMessage());
                        Toast.makeText(PublicLoginPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder= new AlertDialog.Builder(PublicLoginPage.this);
        builder.setTitle("Email Verification");
        builder.setIcon(R.drawable.emailverifiedicon);
        builder.setMessage("Please check Your Inbox and Verify Email ").setCancelable(false);
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


}

