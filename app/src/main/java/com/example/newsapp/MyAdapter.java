package com.example.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdapter extends FirebaseRecyclerAdapter<User,MyAdapter.MyViewHolder> {

    public MyAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull User model) {

        holder.txtTitle.setText(model.getTitle());
        Glide.with(holder.txtTitle.getContext()).load(model.getPfurl()).into(holder.imgDP);
        holder.txtVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(model.getLink()));
                holder.imgDP.getContext().startActivity(intent);


            }

        });

        holder.imgDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(model.getLink()));
                holder.imgDP.getContext().startActivity(intent);


            }
        });



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgDP;
        TextView txtTitle,txtVisit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDP=itemView.findViewById(R.id.imgDP);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtVisit=itemView.findViewById(R.id.txtvisit);

        }

    }

}
