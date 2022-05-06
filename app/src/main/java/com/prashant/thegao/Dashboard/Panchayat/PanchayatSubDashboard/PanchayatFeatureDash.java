package com.prashant.thegao.Dashboard.Panchayat.PanchayatSubDashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.prashant.thegao.Dashboard.Panchayat.PanchayatConstructionWork;
import com.prashant.thegao.Dashboard.Panchayat.PanchayatLenDen;
import com.prashant.thegao.Dashboard.Panchayat.PanchayatRoadWork;
import com.prashant.thegao.Dashboard.Panchayat.SaarpanchQuerySend;
import com.prashant.thegao.Dashboard.Panchayat.SachivQuerySend;
import com.prashant.thegao.Dashboard.Panchayat.SahayojakQuerySend;
import com.prashant.thegao.R;

import java.util.ArrayList;
import java.util.List;

public class PanchayatFeatureDash extends AppCompatActivity {

    CardView sarpanchQ_btn,sachivQ_btn,sahyojakQ_btn ,roadworkQ_btn,lendenQ_btn,constructionQ_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarpanch_feature_dash);

        getSupportActionBar().setTitle("Panchayat Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/the-gao-1.appspot.com/o/Gram%20Panchayat%20Photos%2FGramPanchayat_Dashboard_Image%203.jpg?alt=media&token=f1b5eaf3-7347-42fd-92ed-978c2f46fc62"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/the-gao-1.appspot.com/o/Gram%20Panchayat%20Photos%2FGramPanchayat_Dashboard_Image%20(2).jpg?alt=media&token=3c06c56c-80cb-42c2-9184-ab0381d48bc5"));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/the-gao-1.appspot.com/o/Gram%20Panchayat%20Photos%2FGramPanchayat_Dashboard_Image.jpg?alt=media&token=3f3fba5b-9edd-43ec-b424-33e6b2880ed8","ग्राम पंचायत भवन पालाखेड़"));

        imageSlider.setImageList(slideModels,true);

        sarpanchQ_btn = findViewById(R.id.sarpanch_query_btn);
        sachivQ_btn = findViewById(R.id.sachiv_query_btn);
        sahyojakQ_btn = findViewById(R.id.sahyojak_query_btn);
        roadworkQ_btn = findViewById(R.id.roadwork_btn);
        lendenQ_btn = findViewById(R.id.panchayat_lenden_btn);
        constructionQ_btn = findViewById(R.id.construction_btn);

        sarpanchQ_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanchayatFeatureDash.this, SaarpanchQuerySend.class);
                startActivity(intent);
            }
        });

        sachivQ_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanchayatFeatureDash.this, SachivQuerySend.class);
                startActivity(intent);
            }
        });

        sahyojakQ_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanchayatFeatureDash.this, SahayojakQuerySend.class);
                startActivity(intent);
            }
        });

        roadworkQ_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanchayatFeatureDash.this, PanchayatRoadWork.class);
                startActivity(intent);
            }
        });

        lendenQ_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanchayatFeatureDash.this, PanchayatLenDen.class);
                startActivity(intent);
            }
        });

        constructionQ_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanchayatFeatureDash.this, PanchayatConstructionWork.class);
                startActivity(intent);
            }
        });




    }
}