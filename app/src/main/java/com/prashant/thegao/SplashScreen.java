package com.prashant.thegao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    Handler h= new Handler();
    FirebaseAuth authProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        authProfile = FirebaseAuth.getInstance();

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        h.postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, SelectUser.class);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        },4000);
    }

}