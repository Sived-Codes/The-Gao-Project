package com.prashant.thegao.Dashboard.Education;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.prashant.thegao.Dashboard.Education.School.SchoolDashboard;
import com.prashant.thegao.R;

import java.util.ArrayList;
import java.util.List;

public class EducationFeatureDash extends AppCompatActivity {

    ImageSlider imageSlider;

    CardView schoolBTN,collegeBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_feature_dash);
        getSupportActionBar().setTitle("Education Dashbaord");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        schoolBTN = findViewById(R.id.schoolBTN);
        collegeBTN = findViewById(R.id.collegeBTN);

        schoolBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EducationFeatureDash.this, SchoolDashboard.class);
                startActivity(intent);
            }
        });

        imageSlider = findViewById(R.id.educationSlider);
        List<SlideModel> EduslideModels = new ArrayList<>();
        EduslideModels.add(new SlideModel("https://img.freepik.com/free-vector/education-horizontal-typography-banner-set-with-learning-knowledge-symbols-flat-illustration_1284-29493.jpg?w=2000"));
        EduslideModels.add(new SlideModel("https://www.ecoleglobale.com/blog/wp-content/uploads/2021/10/IMPORTANCE-OF-VALUE-EDUCATION.jpg"));
        EduslideModels.add(new SlideModel("https://www.unicef.org/india/sites/unicef.org.india/files/styles/hero_desktop/public/UNI355729.jpg?itok=HlY4Hq4A"));
        EduslideModels.add(new SlideModel("https://leverageedublog.s3.ap-south-1.amazonaws.com/blog/wp-content/uploads/2019/09/23190755/Importance-of-Value-Education.jpg"));
        EduslideModels.add(new SlideModel("https://s3.ap-south-1.amazonaws.com/atg-storage-s3/assets/Frontend/images/article_image/article-03050000002019864182494.jpg"));
        EduslideModels.add(new SlideModel("https://www.unicef.org/india/sites/unicef.org.india/files/styles/hero_desktop/public/UNI355729.jpg?itok=HlY4Hq4A"));
        EduslideModels.add(new SlideModel("https://cdn.britannica.com/36/179536-138-846A71D2/India-efforts-film-education-system-2009.jpg?w=800&h=450&c=crop"));
        imageSlider.setImageList(EduslideModels,true);






    }
}