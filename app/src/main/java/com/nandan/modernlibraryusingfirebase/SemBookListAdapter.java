package com.nandan.modernlibraryusingfirebase;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class SemBookListAdapter extends RecyclerView.Adapter<SemBookListAdapter.ViewHolder>  {
    ArrayList<Model> data;
    Context context;



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgView;
        private TextView txtView , txtView2;
        private CardView parent;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgView);
            txtView = itemView.findViewById(R.id.txtView);
            txtView2 = itemView.findViewById(R.id.txtView2);
            parent = itemView.findViewById(R.id.parent);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getLayoutPosition());
        }
        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener = ic;
        }
    }

    public SemBookListAdapter(ArrayList<Model> data , Context c) {
        this.data = data;
        this.context = c;
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
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (data.get(position).getTitle().equals("Semester I")) {
                    Intent sem1 = new Intent(context, Semester_I_books.class);
                    context.startActivity(sem1);
                } else if (data.get(position).getTitle().equals("Semester II")) {
                    Intent sem2 = new Intent(context, Semester_II_books.class);
                    context.startActivity(sem2);
                } else if (data.get(position).getTitle().equals("Semester III")) {
                    Intent sem3 = new Intent(context, Semester_III_books.class);
                    context.startActivity(sem3);
                } else if (data.get(position).getTitle().equals("Semester IV")) {
                    Intent sem4 = new Intent(context, Semester_IV_books.class);
                    context.startActivity(sem4);
                } else if (data.get(position).getTitle().equals("Semester V")) {
                    Intent sem5 = new Intent(context, Semester_V_books.class);
                    context.startActivity(sem5);
                } else if (data.get(position).getTitle().equals("Semester VI")) {
                    Intent sem6 = new Intent(context, Semester_VI_books.class);
                    context.startActivity(sem6);
                } else if (data.get(position).getTitle().equals("Miscellaneous")) {
                    Intent misc = new Intent(context, Misc_Books.class);
                    context.startActivity(misc);
                } else if (data.get(position).getTitle().equals("Programming Languages")) {
                    Intent i1 = new Intent(context, Programming_books.class);
                    context.startActivity(i1);


                } else if (data.get(position).getTitle().equals("Database Management System")) {
                    Intent i2 = new Intent(context, Dbms_books.class);
                    context.startActivity(i2);

                } else if (data.get(position).getTitle().equals("Algorithms and Data Structures")) {
                    Intent i3 = new Intent(context, DataStructure_books.class);
                    context.startActivity(i3);
                }
                else if(data.get(position).getTitle().equals("Computer Architecture and Organisation")) {
                    Intent i4 = new Intent(context, c_architecture_books.class);
                    context.startActivity(i4);
                }else if(data.get(position).getTitle().equals("Discrete Mathematics")){
                    Intent i5 = new Intent(context, Discrete_Maths_books.class);
                    context.startActivity(i5);
                }else if(data.get(position).getTitle().equals("Operating System")){
                    Intent i6 = new Intent(context, OS_books.class);
                    context.startActivity(i6);
                }else if(data.get(position).getTitle().equals("Computer Networks")){
                    Intent i7 = new Intent(context, Networks_books.class);
                    context.startActivity(i7);
                }else if(data.get(position).getTitle().equals("System Design")){
                    Intent i8 = new Intent(context, System_Design_books.class);
                    context.startActivity(i8);
                }

            }
        });





    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
