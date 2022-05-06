package com.prashant.thegao.Dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prashant.thegao.AssocUser.SarpanchWork.Upload;
import com.prashant.thegao.R;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {

    private ArrayList<Upload> list;
    private MyAdapter adapter;
    private final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Assoc User").child("Sarpanch").child("Palakhed").child("Admin").child("A-Notices");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_notification, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        list = new ArrayList<>();
        adapter = new MyAdapter(getContext(), list);



        getActivity().setTitle("Notification");





        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Upload upload = dataSnapshot.getValue(Upload.class);
                    list.add(upload);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}

