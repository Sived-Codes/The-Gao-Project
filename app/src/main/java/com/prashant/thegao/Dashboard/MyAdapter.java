package com.prashant.thegao.Dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prashant.thegao.AssocUser.SarpanchWork.Upload;
import com.prashant.thegao.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Upload> mList;
    private final Context context;

    public MyAdapter (Context context, ArrayList<Upload> mList){

        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(mList.get(position).getmImageUri()).into(holder.imageView);


    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = imageView.findViewById(R.id.m_image);
        }
    }

}
