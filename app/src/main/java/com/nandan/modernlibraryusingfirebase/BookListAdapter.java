package com.nandan.modernlibraryusingfirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<Model> data;

    public BookListAdapter(ArrayList<Model> data) {
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



    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
