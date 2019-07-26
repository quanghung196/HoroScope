package com.example.horoscope.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.horoscope.R;
import com.example.horoscope.model.Zodiac;

import java.util.List;

public class ZodiacPickerFeature_Adapter extends RecyclerView.Adapter<ZodiacPickerFeature_Adapter.ViewHolder> {

    private List<Zodiac> zodiacs;
    private Context context;
    private int identify;

    public ZodiacPickerFeature_Adapter(List<Zodiac> zodiacs, Context context,int identify) {
        this.zodiacs = zodiacs;
        this.context = context;
        this.identify = identify;
    }

    @NonNull
    @Override
    public ZodiacPickerFeature_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_zodiac_two, parent, false);
        ZodiacPickerFeature_Adapter.ViewHolder viewHolder = new ZodiacPickerFeature_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ZodiacPickerFeature_Adapter.ViewHolder viewHolder, final int position) {
        final Zodiac zodiac = zodiacs.get(position);
        int image = zodiac.getZodiacImage();
        viewHolder.tvZodiacName.setText(zodiac.getZodiacName());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        if(identify == 1){
            viewHolder.linearBubble.setBackgroundResource(R.drawable.bubble);
        }else{
            viewHolder.linearBubble.setBackgroundResource(R.drawable.bubble_red);
        }
        Glide.with(context).asBitmap().load(image)
                .into(viewHolder.imgZodiacImage);

    }

    private void setUpSharePreference(String zodiac) {
        SharedPreferences myHighScore = context.getSharedPreferences("Zodiac", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myHighScore.edit();
        editor.putString("zodiac", zodiac);
        editor.commit();
    }

    @Override
    public int getItemCount() {
        return zodiacs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgZodiacImage;
        private TextView tvZodiacName;
        private LinearLayout linearBubble;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearBubble = itemView.findViewById(R.id.linearBubble);
            imgZodiacImage = itemView.findViewById(R.id.imgZodiacImage);
            tvZodiacName = itemView.findViewById(R.id.tvZodiacName);
        }
    }
}
