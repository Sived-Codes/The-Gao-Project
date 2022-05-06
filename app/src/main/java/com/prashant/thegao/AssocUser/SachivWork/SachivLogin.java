package com.prashant.thegao.AssocUser.SachivWork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prashant.thegao.AssocUser.SarpanchWork.SarpanchDashboard;
import com.prashant.thegao.AssocUser.SarpanchWork.SarpanchLogin;
import com.prashant.thegao.R;

public class SachivLogin extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://the-gao-1-default-rtdb.firebaseio.com/");
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    EditText userid,password;
    Button loginbtn, registerbtn1;
    FirebaseAuth authProfile;
    ProgressBar progressBar;
    TextView forgetpwd;
    private static final String TAG ="SachivLoginPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sachivlogin);


        preferences = getSharedPreferences("MyPreference",MODE_PRIVATE);
        editor = preferences.edit();


        userid = findViewById(R.id.AuserId_sachiv);
        password = findViewById(R.id.Apassword_sachiv);
        loginbtn = findViewById(R.id.AloginBTN_sachiv);
        registerbtn1 = findViewById(R.id.AregisterBtn_sachiv);
        progressBar =  findViewById(R.id.progressBar);

        getSupportActionBar().setTitle("Sachiv Admin Panel ");

        registerbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SachivLogin.this, SachivSignUp.class);
                startActivity(intent);

            }
        });

        if (preferences.contains("saved_id")){
            Intent intent = new Intent(SachivLogin.this, SachivDashboard.class);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userIdTxt = userid.getText().toString();
                final String passwordTxt = password.getText().toString();
                editor.putString("saved_id",userIdTxt);
                editor.putString("saved_pwd",passwordTxt);
                editor.commit();

                if (TextUtils.isEmpty(userid.getText().toString())) {
                    userid.setError("Enter User ID Provided by Software Developer ");
                    userid.requestFocus();

                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Enter Password");
                    password.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    databaseReference.child("Assoc User").child("Sachiv").child("Palakhed").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(userIdTxt)){
                                //check pwd match
                                final String getPassword;
                                getPassword = snapshot.child(userIdTxt).child("Password").getValue(String.class);

                                if(getPassword.equals(passwordTxt)){
                                    Toast.makeText(SachivLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SachivLogin.this, SachivDashboard.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(SachivLogin.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(SachivLogin.this, "Wrong User-ID", Toast.LENGTH_SHORT).show();
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
