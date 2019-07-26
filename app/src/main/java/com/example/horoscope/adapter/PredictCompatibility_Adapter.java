package com.example.horoscope.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.R;
import com.example.horoscope.model.HoroScope;

import java.util.List;

public class PredictCompatibility_Adapter extends RecyclerView.Adapter<PredictCompatibility_Adapter.ViewHolder> {

    private List<HoroScope> horoScopes;
    private Context context;

    public PredictCompatibility_Adapter(List<HoroScope> horoScopes, Context context) {
        this.horoScopes = horoScopes;
        this.context = context;
    }

    @NonNull
    @Override
    public PredictCompatibility_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_predict_two, parent, false);
        PredictCompatibility_Adapter.ViewHolder viewHolder = new PredictCompatibility_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PredictCompatibility_Adapter.ViewHolder viewHolder, final int position) {
        final HoroScope horoScope = horoScopes.get(position);

        if(horoScope.getTitle()!=null){
            viewHolder.tvTitle.setText(horoScope.getTitle());
        }
        viewHolder.tvPerCent.setText(horoScope.getPercent() + "%");
        viewHolder.progressBar.setProgress(horoScope.getPercent());
        viewHolder.tvPredict.setText(horoScope.getHoroscope());
    }

    @Override
    public int getItemCount() {
        return horoScopes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvPerCent, tvPredict;
        private ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            progressBar = itemView.findViewById(R.id.progressBar);
            tvPerCent = itemView.findViewById(R.id.tvPercent);
            tvPredict = itemView.findViewById(R.id.tvPredict);
        }
    }
}
