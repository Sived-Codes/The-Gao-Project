package com.prashant.thegao.PublicUser;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prashant.thegao.R;


public class PublicSignupPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText fullname, newmobilenum, newemail, newpwd, newpwdc;

    ProgressBar progressBar;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_signup_page);



        final Spinner spinner = findViewById(R.id.spinnerGaoName);
        ArrayAdapter<CharSequence> adapter  = ArrayAdapter.createFromResource(this,R.array.GaoName, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        fullname = findViewById(R.id.NPfullname);
        newmobilenum = findViewById(R.id.NPmobile);
        newemail = findViewById(R.id.NPemail);
        newpwd = findViewById(R.id.NPpwd);
        newpwdc = findViewById(R.id.NPpwdc);
        progressBar = findViewById(R.id.progressBar);
        Button newregisterbtn2 = findViewById(R.id.NPregisterbtn);

        newregisterbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullnametxt = fullname.getText().toString();
                final String phoneTxt = newmobilenum.getText().toString();
                final String emailtxt = newemail.getText().toString();
                final String passwordTxt = newpwd.getText().toString();
                final String conPasswordTxt = newpwdc.getText().toString();
                final String spinnerTxt = spinner.getSelectedItem().toString();


                if (TextUtils.isEmpty(fullname.getText().toString())) {
                    fullname.setError("Enter Full Name");
                    fullname.requestFocus();
                } else if (TextUtils.isEmpty(newmobilenum.getText().toString())) {
                    newmobilenum.setError("Enter Mobile Number");
                    newmobilenum.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()) {
                    newemail.setError("Enter Valid E-Mail Address");
                    newemail.requestFocus();
                } else if (TextUtils.isEmpty(newpwd.getText().toString())) {
                    newpwd.setError("Enter Password");
                    newpwd.requestFocus();
                } else if (TextUtils.isEmpty(newpwdc.getText().toString())) {
                    newpwdc.setError("Confirm Password");
                    newpwdc.requestFocus();
                } else if (!passwordTxt.equals(conPasswordTxt)) {
                    newpwdc.setError("Password not matching Password");
                    newpwdc.requestFocus();

                    newpwd.clearComposingText();
                    newpwdc.clearComposingText();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(fullnametxt, phoneTxt, emailtxt,spinnerTxt, conPasswordTxt);

                }

            }
        });

    }

    private void registerUser (String fullnametxt, String phoneTxt, String emailtxt,String spinnerTxt, String conPasswordTxt) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(emailtxt, conPasswordTxt).addOnCompleteListener(PublicSignupPage.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(fullnametxt, emailtxt, phoneTxt, conPasswordTxt,spinnerTxt);

                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Public Users");

                    referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                firebaseUser.sendEmailVerification();

                                Toast.makeText(PublicSignupPage.this, "Public Registration is Successful", Toast.LENGTH_SHORT).show();
                                showAlertDialog();
                            } else{
                                Toast.makeText(PublicSignupPage.this, "Public Registration is failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                } else {
                    try {
                        throw task.getException();

                    } catch (FirebaseAuthUserCollisionException e) {
                        newemail.setError("Email is alredy Registerd");
                        newemail.requestFocus();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        newemail.setError("Email is invalid ");
                        newemail.requestFocus();

                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(PublicSignupPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    progressBar.setVisibility(View.GONE);


                    // Toast.makeText(EducationSignup.this, "Registration Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void showAlertDialog() {
        AlertDialog.Builder builder= new AlertDialog.Builder(PublicSignupPage.this);
        builder.setTitle("Email Verification");
        builder.setIcon(R.drawable.emailverifiedicon);
        builder.setMessage("Please check Your Inbox and Verify Email and login !! ").setCancelable(false);
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

