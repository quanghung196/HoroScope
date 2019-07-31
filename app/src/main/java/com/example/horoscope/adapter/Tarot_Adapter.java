package com.example.horoscope.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.horoscope.R;
import com.example.horoscope.model.tarot.TarotCard;

import java.util.List;

public class Tarot_Adapter extends RecyclerView.Adapter<Tarot_Adapter.ViewHolder> {

    private Context context;
    private List<TarotCard> tarotCards;

    public Tarot_Adapter(Context context, List<TarotCard> tarotCards) {
        this.context = context;
        this.tarotCards = tarotCards;
    }

    @NonNull
    @Override
    public Tarot_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tarot, parent, false);
        Tarot_Adapter.ViewHolder viewHolder = new Tarot_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Tarot_Adapter.ViewHolder viewHolder, final int position) {
        TarotCard tarotCard = tarotCards.get(position);

        Glide.with(context)
                .load(tarotCard.getTarotImage())
                .into(viewHolder.imgTarot);
    }

    @Override
    public int getItemCount() {
        return tarotCards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgTarot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTarot = itemView.findViewById(R.id.imgTarot);
        }
    }
}