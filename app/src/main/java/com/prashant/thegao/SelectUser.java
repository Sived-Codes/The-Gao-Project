package com.prashant.thegao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.prashant.thegao.AssocUser.UserTypes;
import com.prashant.thegao.Dashboard.Dashboard;
import com.prashant.thegao.PublicUser.PublicLoginPage;

public class SelectUser extends AppCompatActivity {

    Button PublicBTN,AssocBTN;
    FirebaseAuth authProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        PublicBTN = findViewById(R.id.publicBTN);
        AssocBTN = findViewById(R.id.assocBTN);
        authProfile = FirebaseAuth.getInstance();




        PublicBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectUser.this, PublicLoginPage.class);
                startActivity(intent);
            }
        });

        AssocBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectUser.this, UserTypes.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(SelectUser.this);
        builder.setTitle("Exit");
        builder.setIcon(R.drawable.shocked);
        builder.setMessage("sahi me exit karna hai kya..?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    //Login check
    @Override
    protected void onStart() {
        super.onStart();
        if(authProfile.getCurrentUser() !=null){
            Toast.makeText(this, "Alreday Logged In", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SelectUser.this, Dashboard.class));
            finish();
        }
        else {
            Toast.makeText(this, "You can Login now", Toast.LENGTH_SHORT).show();

        }
    }


}