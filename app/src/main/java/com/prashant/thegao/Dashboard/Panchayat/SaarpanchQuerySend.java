package com.prashant.thegao.Dashboard.Panchayat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prashant.thegao.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SaarpanchQuerySend extends AppCompatActivity {

    Button btnSendMsg,callBTN,wtspBTN;;
    EditText etMsg;
    ListView lvDiscussion;
    private static final int REQUEST_CALL = 1;

    private String fullname;
    ArrayList<String> listConversation = new ArrayList<String>();
    ArrayAdapter arrayAdpt;

    String UserName, user_msg_key;
    private DatabaseReference dbr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saarpanch_query_send);

        btnSendMsg = (Button) findViewById(R.id.btnSendMsg_sarpanch);
        etMsg = (EditText) findViewById(R.id.etMessage_sarpanch);
        lvDiscussion = (ListView) findViewById(R.id.lvConversation_sarpanch);

        callBTN = findViewById(R.id.SarpanchCallBtnView);
        wtspBTN = findViewById(R.id.SarpanchWhatsappBtnView);

        arrayAdpt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listConversation);
        lvDiscussion.setAdapter(arrayAdpt);
        UserName = "Kill";

        getSupportActionBar().setTitle("Sarpanch Fourm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wtspBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://wa.me/+918889818200?text=Hello%20...!%0ANamstey%20Sarpanch%20Ji%20%0A");
            }
        });

        callBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton();
            }
        });

        dbr = FirebaseDatabase.getInstance().getReference("Assoc User").child("Sarpanch").child("Palakhed").child("Admin").child("B-Queries");
        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //usey.setText(fullname);

                Map<String, Object> map = new HashMap<String, Object>();
                user_msg_key = dbr.push().getKey();
                dbr.updateChildren(map);
                DatabaseReference dbr2 = dbr.child(user_msg_key);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("msg", "Prashant Deshmkuh☑️️ : " + etMsg.getText().toString());
                dbr2.updateChildren(map2);
                etMsg.setText("");
            }
        });
        dbr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateConversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                updateConversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    private void CallButton() {

            if (ContextCompat.checkSelfPermission(SaarpanchQuerySend.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SaarpanchQuerySend.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" +"8889818200";
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }

    //WhatsApp Url Button
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


    public void updateConversation(DataSnapshot dataSnapshot){
        String msg, conversation;

        Iterator i = dataSnapshot.getChildren().iterator();
        while(i.hasNext()){
            msg = (String) ((DataSnapshot)i.next()).getValue();
            conversation = ""+ msg;
            arrayAdpt.insert(conversation, arrayAdpt.getCount());
            arrayAdpt.notifyDataSetChanged();
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
