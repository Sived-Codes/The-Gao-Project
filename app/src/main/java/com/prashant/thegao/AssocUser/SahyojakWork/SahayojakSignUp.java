package com.prashant.thegao.AssocUser.SahyojakWork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prashant.thegao.R;

public class SahayojakSignUp extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://the-gao-1-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahayojak_sign_up);

        final EditText fullname = findViewById(R.id.ANfullname_sahyojak);
        final EditText phone = findViewById(R.id.ANmobile_sahyojak);
        final EditText userid = findViewById(R.id.ANUserId_sahyojak);
        final EditText password = findViewById(R.id.ANpwd_sahyojak);
        final EditText conPassword = findViewById(R.id.ANpwdc_sahyojak);
        final Button registerbtn2 = findViewById(R.id.ANregisterbtn_sahyojak);

        getSupportActionBar().setTitle("Sahyojak SignUp");

        registerbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullnametxt = fullname.getText().toString();
                final String phoneTxt = phone.getText().toString();
                final String userIdTxt = userid.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String conPasswordTxt = conPassword.getText().toString();

                if (fullnametxt.isEmpty()) {
                    fullname.setError("Enter Full Name");
                    fullname.requestFocus();

                } else if (phoneTxt.isEmpty()) {
                    phone.setError("Enter Mobile Number");
                    phone.requestFocus();

                } else if (userIdTxt.isEmpty()) {
                    userid.setError("Enter User id");
                    userid.requestFocus();

                } else if (passwordTxt.isEmpty()) {
                    password.setError("Enter Password");
                    password.requestFocus();

                } else if (conPasswordTxt.isEmpty()) {
                    conPassword.setError("Confirm Password");
                    conPassword.requestFocus();

                } else if (!passwordTxt.equals(conPasswordTxt)) {
                    Toast.makeText(SahayojakSignUp.this, "Password are not matching", Toast.LENGTH_SHORT).show();

                } else {
                    databaseReference.child("Assoc User").child("Sahyojak").child("Palakhed").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //number kahi pahele se registerd to nai hai check karega
                            if (snapshot.hasChild(userIdTxt)) {
                                Toast.makeText(SahayojakSignUp.this, "This User ID is Already Registered", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child("Assoc User").child("Sahyojak").child("Palakhed").child(userIdTxt).child("Full name").setValue(fullnametxt);
                                databaseReference.child("Assoc User").child("Sahyojak").child("Palakhed").child(userIdTxt).child("User ID").setValue(userIdTxt);
                                databaseReference.child("Assoc User").child("Sahyojak").child("Palakhed").child(userIdTxt).child("Phone").setValue(phoneTxt);
                                databaseReference.child("Assoc User").child("Sahyojak").child("Palakhed").child(userIdTxt).child("Password").setValue(conPasswordTxt);
                                Toast.makeText(SahayojakSignUp.this, "User Successfully Registered !!Thanks ", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
    }
}




