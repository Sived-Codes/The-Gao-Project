package com.prashant.thegao.Dashboard.Electricity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.prashant.thegao.R;

import java.util.ArrayList;
import java.util.List;

public class ElectricityDepartment extends AppCompatActivity {

    ImageSlider imageSlider;
    Button callbtn, mailbtn, locationbtn;
    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_department);

        getSupportActionBar().setTitle("Electricity Department");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageSlider = findViewById(R.id.electricityheadofficeSlider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://cdn.s3waas.gov.in/s37ef605fc8dba5425d6965fbd4c8fbe1f/uploads/2019/10/2019102386.jpg"));
        slideModels.add(new SlideModel("https://cdn.s3waas.gov.in/s37ef605fc8dba5425d6965fbd4c8fbe1f/uploads/2020/06/2020062916.jpg"));
        slideModels.add(new SlideModel("https://mapio.net/images-p/122621451.jpg"));
        slideModels.add(new SlideModel("https://cdn.s3waas.gov.in/s37ef605fc8dba5425d6965fbd4c8fbe1f/uploads/2018/05/2018050541-1.jpg"));
        slideModels.add(new SlideModel("https://1.bp.blogspot.com/-pXukP_kkOyM/X-ylPBz7ygI/AAAAAAAAEA8/OFQGzRvo-GQfrm2Ab_mxrj4w-Ld3_2meQCLcBGAsYHQ/s784/nagar_palika_nigam-cmyk_6118497_835x547-m.jpg"));
        imageSlider.setImageList(slideModels,true);


        callbtn = findViewById(R.id.ele_dept_callBtn);
        locationbtn = findViewById(R.id.ele_dept_locationBtn);
        mailbtn = findViewById(R.id.ele_dept_mailBtn);

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton();
            }
        });

        locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String theurl = "https://www.google.com/maps/dir//3W6G%2B547+Mpeb+Office,+Sinchai+Colony,+Mohan+Nagar,+Chhindwara,+Madhya+Pradesh+480001/@22.0604072,78.8902735,13z/data=!4m8!4m7!1m0!1m5!1m1!1s0x3bd565fc57629463:0xcd566a358690af1b!2m2!1d78.9252744!2d22.0603651";
                Uri urlstr = Uri.parse(theurl);
                Intent urlintent = new Intent();
                urlintent.setData(urlstr);
                urlintent.setAction(Intent.ACTION_VIEW);
                startActivity(urlintent);
            }
        });

        mailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "MPEastDiscom@mpez.co.in"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                startActivity(intent);
            }
        });

    }

    private void CallButton() {

        if (ContextCompat.checkSelfPermission(ElectricityDepartment.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ElectricityDepartment.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" +" 07162222876";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CallButton();
            } else {
                Toast.makeText(this, "Permission nahi diye aap", Toast.LENGTH_SHORT).show();
            }
        }
    }
}