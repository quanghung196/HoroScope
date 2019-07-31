package com.example.horoscope.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Card_Adapter extends RecyclerView.Adapter<Card_Adapter.ViewHolder> {

    private Context context;
    private List<Integer> integers;

    public Card_Adapter(Context context, List<Integer> integers) {
        this.context = context;
        this.integers = integers;
    }

    @NonNull
    @Override
    public Card_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_card, parent, false);
        Card_Adapter.ViewHolder viewHolder = new Card_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Card_Adapter.ViewHolder viewHolder, final int position) {

        viewHolder.imgCardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return integers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCardBack;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCardBack = itemView.findViewById(R.id.imgCardBack);
        }
    }
}
