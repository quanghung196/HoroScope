package com.example.horoscope.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.R;
import com.example.horoscope.model.HoroScope;

import java.util.List;

public class Predict_Adapter extends RecyclerView.Adapter<Predict_Adapter.ViewHolder> {

    private List<HoroScope> horoScopes;
    private Context context;
    private int positionnn;

    public Predict_Adapter(List<HoroScope> horoScopes, Context context, int positionnn) {
        this.horoScopes = horoScopes;
        this.context = context;
        this.positionnn = positionnn;
    }

    @NonNull
    @Override
    public Predict_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_predict, parent, false);
        Predict_Adapter.ViewHolder viewHolder = new Predict_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Predict_Adapter.ViewHolder viewHolder, final int position) {
        final HoroScope horoScope = horoScopes.get(position);
        if (positionnn == 0) {
            viewHolder.tvDateTime.setText(horoScope.getTitle());
        } else if (positionnn == 1) {
            viewHolder.tvDateTime.setText(horoScope.getDate());
        } else if (positionnn == 2) {
            viewHolder.tvDateTime.setText(horoScope.getWeek());
        } else if (positionnn == 3) {
            viewHolder.tvDateTime.setText(horoScope.getMonth());
        } else if (positionnn == 4) {
            viewHolder.tvDateTime.setText(horoScope.getYear());
        }

        viewHolder.tvPredict.setText(horoScope.getHoroscope());
    }

    @Override
    public int getItemCount() {
        return horoScopes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDateTime, tvPredict;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvPredict = itemView.findViewById(R.id.tvPredict);
        }
    }
}
