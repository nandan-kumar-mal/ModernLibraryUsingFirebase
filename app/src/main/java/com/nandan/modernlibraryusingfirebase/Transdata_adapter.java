package com.nandan.modernlibraryusingfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Transdata_adapter extends RecyclerView.Adapter<Transdata_adapter.Transdata_ViewHolder> {

    Context context;
    ArrayList<Transdata> list;

    public Transdata_adapter(Context context, ArrayList<Transdata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public Transdata_ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.transaction_row,parent,false);
        return new Transdata_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Transdata_adapter.Transdata_ViewHolder holder, int position) {
        Transdata transdata = list.get(position);
        holder.name.setText(transdata.getName());
        holder.roll.setText(transdata.getRoll());
        holder.sem.setText(transdata.getSem());
        holder.book.setText(transdata.getBook());
        holder.date.setText(transdata.getDate());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Transdata_ViewHolder extends RecyclerView.ViewHolder{

        TextView name, roll, book, sem, date;

      public Transdata_ViewHolder(@NonNull @NotNull View itemView) {
          super(itemView);

          name = itemView.findViewById(R.id.borr_name);
          roll = itemView.findViewById(R.id.bor_roll);
          book = itemView.findViewById(R.id.borr_book);
          sem = itemView.findViewById(R.id.borr_sem);
          date = itemView.findViewById(R.id.bor_date);
      }
  }

}
