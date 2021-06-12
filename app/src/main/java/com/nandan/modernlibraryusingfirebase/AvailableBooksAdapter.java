package com.nandan.modernlibraryusingfirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AvailableBooksAdapter extends RecyclerView.Adapter<AvailableBooksAdapter.ViewHolder> {

    private ArrayList<Contact> contacts = new ArrayList<>();
    public AvailableBooksAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtBookName.setText(contacts.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContact(ArrayList<Contact> contact) {
        this.contacts = contact;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtBookName;
        private ImageView bookIcon;
        private TextView txtAuthor;
        private TextView txtAvailabitilty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBookName = itemView.findViewById(R.id.booktitle);
            txtAuthor = itemView.findViewById(R.id.author);
            txtAvailabitilty = itemView.findViewById(R.id.availability);

        }


    }
}
