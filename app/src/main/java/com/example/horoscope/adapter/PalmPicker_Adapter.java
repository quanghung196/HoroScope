package com.example.horoscope.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.horoscope.R;
import com.example.horoscope.model.palmistry.Palmistry;

import java.util.List;

public class PalmPicker_Adapter extends RecyclerView.Adapter<PalmPicker_Adapter.ViewHolder> {

    private List<Palmistry> palmistries;
    private Context context;

    public PalmPicker_Adapter(List<Palmistry> palmistries, Context context) {
        this.palmistries = palmistries;
        this.context = context;
    }

    @NonNull
    @Override
    public PalmPicker_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_palmistry, parent, false);
        PalmPicker_Adapter.ViewHolder viewHolder = new PalmPicker_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PalmPicker_Adapter.ViewHolder viewHolder, final int position) {
        final Palmistry palmistry = palmistries.get(position);

        Glide.with(context).asBitmap().load(palmistry.getPalmImage())
                .into(viewHolder.imgPalm);
        viewHolder.tvPalm.setText(palmistry.getPalmTitle());
    }

    @Override
    public int getItemCount() {
        return palmistries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPalm;
        private TextView tvPalm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPalm = itemView.findViewById(R.id.imgPalm);
            tvPalm = itemView.findViewById(R.id.tvPalm);
        }
    }
}
