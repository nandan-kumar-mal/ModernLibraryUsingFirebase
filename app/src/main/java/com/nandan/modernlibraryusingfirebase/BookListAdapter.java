package com.nandan.modernlibraryusingfirebase;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    ArrayList<Model> data;
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgView;
        private TextView txtView , txtView2;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgView);
            txtView = itemView.findViewById(R.id.txtView);
            txtView2 = itemView.findViewById(R.id.txtView2);
            parent = itemView.findViewById(R.id.parent);


        }
    }

    public BookListAdapter(ArrayList<Model> data, c) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtView.setText(data.get(position).getTitle());
        holder.txtView2.setText(data.get(position).getDesc());
        holder.imgView.setImageResource(data.get(position).getImgname());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getContext()== data.get(position)) {

                }
            }
        });





    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
