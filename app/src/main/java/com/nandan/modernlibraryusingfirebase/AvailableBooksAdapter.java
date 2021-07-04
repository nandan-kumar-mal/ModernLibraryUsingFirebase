package com.nandan.modernlibraryusingfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AvailableBooksAdapter extends RecyclerView.Adapter<AvailableBooksAdapter.ViewHolder> {
    Context context;

    ArrayList<Contact> contacts ;
    public AvailableBooksAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_book_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.txtBookName.setText(contact.getTitle());
        holder.txtAuthor.setText(contact.getAuthor());

        holder.parent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, contact.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
            }
       });
        Glide.with(holder.bookIcon.getContext())
                .load(contact.getIcon())
                .into(holder.bookIcon);

}

    @Override
    public int getItemCount() {
        return contacts.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtBookName;
        private ImageView bookIcon;
        private TextView txtAuthor;
       private TextView txtAvailabitilty;
        private CardView parent2;
        

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBookName = itemView.findViewById(R.id.booktitle);
            txtAuthor = itemView.findViewById(R.id.author);
            txtAvailabitilty = itemView.findViewById(R.id.availability);
            parent2 = itemView.findViewById(R.id.parent2);
            bookIcon = itemView.findViewById(R.id.bookImg);

        }


    }
}
