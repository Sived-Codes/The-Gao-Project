package com.prashant.thegao.AssocUser;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.prashant.thegao.AssocUser.SachivWork.SachivLogin;
import com.prashant.thegao.AssocUser.SahyojakWork.SahayojakLogin;
import com.prashant.thegao.AssocUser.SarpanchWork.SarpanchLogin;
import com.prashant.thegao.R;

import java.util.Objects;

public class UserTypes extends AppCompatActivity {

    Button sarpanchBTN,sachivBTN,sahyojakBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_types);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Select Assoc Type");
        sarpanchBTN =findViewById(R.id.sarpanchID_BTN);
        sachivBTN =findViewById(R.id.sachivID_BTN);
        sahyojakBTN =findViewById(R.id.sahyojakID_BTN);

        sarpanchBTN.setOnClickListener(view -> {
          Intent intent = new Intent(UserTypes.this, SarpanchLogin.class);
            startActivity(intent);
        });

        sachivBTN.setOnClickListener(view -> {
            Intent intent = new Intent(UserTypes.this, SachivLogin.class);
            startActivity(intent);
        });

        sahyojakBTN.setOnClickListener(view -> {
            Intent intent = new Intent(UserTypes.this, SahayojakLogin.class);
            startActivity(intent);
        });

    }
}