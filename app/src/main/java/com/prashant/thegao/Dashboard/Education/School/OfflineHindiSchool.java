package com.prashant.thegao.Dashboard.Education.School;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.prashant.thegao.R;

public class OfflineHindiSchool extends AppCompatActivity {

    CardView Class1stBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_hindi_school);

        getSupportActionBar().setTitle("Select Class");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Class1stBTN=findViewById(R.id.class1st);
        Class1stBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OfflineHindiSchool.this, "Button Pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}