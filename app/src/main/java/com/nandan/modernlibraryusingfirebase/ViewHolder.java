package com.nandan.modernlibraryusingfirebase;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imgView;
    TextView txtView , txtView2;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imgView = itemView.findViewById(R.id.imgView);
        txtView = itemView.findViewById(R.id.txtView);
        txtView2 = itemView.findViewById(R.id.txtView2);


    }
}
