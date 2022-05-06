package com.prashant.thegao.Dashboard.Education.School;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.prashant.thegao.R;

public class SchoolDashboard extends AppCompatActivity {

    String[] schools ={"Adarsh Gramodya Public School","Sarswati Shisu Mandir School","Lakshya Public School","Gov. School Palakhed",};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterSchools;
    Button loginBTN,offlineBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_dashboard);


        loginBTN = findViewById(R.id.School_loginBTN);
        offlineBTN = findViewById(R.id.School_oflineBTN);

        getSupportActionBar().setTitle("School Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        autoCompleteTextView=findViewById(R.id.schoolList);
        adapterSchools=new ArrayAdapter<String>(this,R.layout.school_list,schools);
        autoCompleteTextView.setAdapter(adapterSchools);



        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String schools = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"schools :" +schools,Toast.LENGTH_LONG);
            }
        });

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(SchoolDashboard.this);
                builder.setTitle("Online School");
                builder.setIcon(R.drawable.wrkinprgrss);
                builder.setMessage("This feature is coming soon OR You can go with Offline option.").setCancelable(false)

                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        offlineBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SchoolDashboard.this, OfflineSchool.class);
                startActivity(intent);
            }
        });

    }
}