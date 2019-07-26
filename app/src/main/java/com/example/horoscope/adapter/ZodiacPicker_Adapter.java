package com.example.horoscope.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.horoscope.MainActivity;
import com.example.horoscope.R;
import com.example.horoscope.model.Zodiac;

import java.util.List;

public class ZodiacPicker_Adapter extends RecyclerView.Adapter<ZodiacPicker_Adapter.ViewHolder> {

    private List<Zodiac> zodiacs;
    private Context context;

    public ZodiacPicker_Adapter(List<Zodiac> zodiacs, Context context) {
        this.zodiacs = zodiacs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_zodiac, parent, false);
        ZodiacPicker_Adapter.ViewHolder viewHolder = new ZodiacPicker_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Zodiac zodiac = zodiacs.get(position);
        int image = zodiac.getZodiacImage();
        viewHolder.tvZodiacName.setText(zodiac.getZodiacName());
        viewHolder.tvZodiacDate.setText(zodiac.getZodiacDate());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels * 25 / 100;
        int height = width * 3 / 2;

        Glide.with(context).asBitmap().load(image)
                .apply(new RequestOptions().override(width, height))
                .into(viewHolder.imgZodiacImage);
    }

    @Override
    public int getItemCount() {
        return zodiacs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgZodiacImage;
        private TextView tvZodiacName, tvZodiacDate;
        private LinearLayout linearRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearRow = itemView.findViewById(R.id.linearRow);
            imgZodiacImage = itemView.findViewById(R.id.imgZodiacImage);
            tvZodiacName = itemView.findViewById(R.id.tvZodiacName);
            tvZodiacDate = itemView.findViewById(R.id.tvZodiacDate);
        }
    }
}
