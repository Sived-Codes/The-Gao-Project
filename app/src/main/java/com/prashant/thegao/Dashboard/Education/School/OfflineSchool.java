package com.prashant.thegao.Dashboard.Education.School;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.prashant.thegao.R;

public class OfflineSchool extends AppCompatActivity {
    Button hindi,English;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_school);
        getSupportActionBar().setTitle("Select Medium");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hindi = findViewById(R.id.hindiBTN);
        English = findViewById(R.id.englishBTN);

        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OfflineSchool.this, OfflineHindiSchool.class);
                startActivity(intent);
            }
        });

    }
}