package com.prashant.thegao.AssocUser.SarpanchWork;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.prashant.thegao.R;
import com.prashant.thegao.SelectUser;


public class SarpanchDashboard extends AppCompatActivity {

    Button updateNoticeBtn,logoutBtn, receiveQueryBtn;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarpanch_dashboard);

        preferences = getSharedPreferences("MyPreference",MODE_PRIVATE);
        editor = preferences.edit();

        updateNoticeBtn = findViewById(R.id.sapanch_updateNotice_BTN);
        logoutBtn = findViewById(R.id.sarpanch_login_BTN);
        receiveQueryBtn = findViewById(R.id.sarpanch_query_receive_btn);



        getSupportActionBar().setTitle("Sarpanch Dashboard");


        updateNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SarpanchDashboard.this, SarpanchUpdateNotice.class);
                startActivity(intent);
            }
        });

        receiveQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SarpanchDashboard.this, SarpanchQueryRecevie.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(SarpanchDashboard.this, SelectUser.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(SarpanchDashboard.this);
        builder.setTitle(" Exit!!");
        builder.setIcon(R.drawable.angry);
        builder.setMessage("Do you wanna Exit ??'")
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
}