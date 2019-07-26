package com.example.horoscope.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.horoscope.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class Quotes_Adapter extends RecyclerView.Adapter<Quotes_Adapter.ViewHolder> {

    private List<String> images;
    private Context context;

    public Quotes_Adapter(List<String> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public Quotes_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quote, parent, false);
        Quotes_Adapter.ViewHolder viewHolder = new Quotes_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Quotes_Adapter.ViewHolder viewHolder, final int position) {
        String image = images.get(position);

        Glide.with(context)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load(image)
                .into(viewHolder.imgQuotes);

    }
    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgQuotes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgQuotes = itemView.findViewById(R.id.imgQuotes);
        }
    }
}