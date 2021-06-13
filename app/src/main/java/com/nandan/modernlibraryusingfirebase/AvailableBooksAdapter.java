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
    private Context context;

    private ArrayList<Contact> contacts = new ArrayList<>();
    public AvailableBooksAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_book_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtBookName.setText(contacts.get(position).getTitle());
        holder.txtAuthor.setText(contacts.get(position).getAuthor());
        holder.txtAvailabitilty.setText(contacts.get(position).getAvailability().toString());
        holder.parent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, contacts.get(position).getTitle() + " Selected", Toast.LENGTH_SHORT).show();
            }
       });
        Glide.with(context)
                .asBitmap()
                .load(contacts.get(position).getImageUrl())
                .into(holder.bookIcon);

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
