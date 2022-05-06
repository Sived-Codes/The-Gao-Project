package com.prashant.thegao.AssocUser.SarpanchWork;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

public class SarpanchQueryRecevie extends AppCompatActivity {

    Button btnSendMsg;
    EditText etMsg;
    ListView lvDiscussion;

    ArrayList<String> listConversation = new ArrayList<String>();
    ArrayAdapter arrayAdpt;

    String UserName, user_msg_key;
    private DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarpanch_query_recevie);

        btnSendMsg = (Button) findViewById(R.id.btnSendMsg2);
        etMsg = (EditText) findViewById(R.id.etMessage2);

        lvDiscussion = (ListView) findViewById(R.id.lvConversation2);

        arrayAdpt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listConversation);
        lvDiscussion.setAdapter(arrayAdpt);
        UserName = "Kill";

        dbr = FirebaseDatabase.getInstance().getReference("Assoc User").child("Sarpanch").child("Palakhed").child("Admin").child("B-Queries");

        btnSendMsg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                user_msg_key = dbr.push().getKey();
                dbr.updateChildren(map);
                DatabaseReference dbr2 = dbr.child(user_msg_key);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("msg","Gram Sarpanch✅ : " + etMsg.getText().toString());
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
}
