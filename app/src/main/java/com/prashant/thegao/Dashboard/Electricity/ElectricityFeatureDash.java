package com.prashant.thegao.Dashboard.Electricity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.prashant.thegao.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ElectricityFeatureDash extends AppCompatActivity {


    ImageSlider imageSlider,imageSlider2;

    Button electricity_cmplnt_BTN,electricity_dept_BTN,electricity_paybill_BTN,electricity_checkbill_BTN  ;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_feature_dash);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Electricity Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        electricity_cmplnt_BTN = findViewById(R.id.Electric_complaint_BTN);
        electricity_dept_BTN = findViewById(R.id.Electric_dpt_office_BTN);
        electricity_paybill_BTN = findViewById(R.id.Electric_dpt_paybill_BTN);
        electricity_checkbill_BTN = findViewById(R.id.Electric_dpt_checkbill_BTN);


        imageSlider = findViewById(R.id.slider_electric1);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/the-gao-1.appspot.com/o/Gram%20Panchayat%20Photos%2FElectricity%20Photo%20Slider%2Fele3.jpg?alt=media&token=fe942018-0656-48d5-be64-8edbb7b08e69"));
        slideModels.add(new SlideModel("https://www.gegridsolutions.com/services/gridautomation/images/banner1.jpg"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/the-gao-1.appspot.com/o/Gram%20Panchayat%20Photos%2FElectricity%20Photo%20Slider%2Felec2.jpg?alt=media&token=be238e99-bfda-41e5-bc7a-f7cae1b59216"));
        slideModels.add(new SlideModel("https://www.electrical4u.com/wp-content/uploads/Electrical-Safety-Precautions.png"));

        imageSlider.setImageList(slideModels,true);


        imageSlider2 = findViewById(R.id.slider_electric2);
        List<SlideModel> slideModels1 = new ArrayList<>();
        slideModels1.add(new SlideModel("https://images.twinkl.co.uk/tw1n/image/private/t_630_eco/image_repo/08/19/t-t-2286-staying-safe-with-electricity-display-posters-_ver_1.webp"));
        slideModels1.add(new SlideModel("https://cdn3.vectorstock.com/i/1000x1000/67/02/electrical-safety-simple-art-poster-vector-20236702.jpg"));
        slideModels1.add(new SlideModel("https://cf.ltkcdn.net/safety/images/std/162278-425x329-use-electronics-safely.gif"));
        slideModels1.add(new SlideModel("https://5.imimg.com/data5/CF/FW/MQ/SELLER-75017778/covid-19-safety-posters-1000x1000.jpg"));

        imageSlider2.setImageList(slideModels1,true);

        electricity_cmplnt_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElectricityFeatureDash.this, ElectricityComplaint.class);
                startActivity(intent);
            }
        });

        electricity_dept_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(ElectricityFeatureDash.this, ElectricityDepartment.class);
                startActivity(intent);
            }
        });

        electricity_paybill_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElectricityFeatureDash.this, ElectricityPayBill.class);
                startActivity(intent);
            }
        });

        electricity_checkbill_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElectricityFeatureDash.this, ElectricityCheckBill.class);
                startActivity(intent);
            }
        });
    }
}