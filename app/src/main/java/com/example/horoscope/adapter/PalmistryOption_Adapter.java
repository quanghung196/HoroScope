package com.example.horoscope.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.horoscope.R;
import com.example.horoscope.model.palmistry.PalmistryOption;

import java.util.List;

public class PalmistryOption_Adapter extends RecyclerView.Adapter<PalmistryOption_Adapter.ViewHolder> {

    private List<PalmistryOption> options;
    private Context context;
    private int itemPostion;
    private Button btnNext;

    public PalmistryOption_Adapter(List<PalmistryOption> options, Context context, int itemPostion, Button btnNext) {
        this.options = options;
        this.context = context;
        this.itemPostion = itemPostion;
        this.btnNext = btnNext;
    }

    @NonNull
    @Override
    public PalmistryOption_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_palm_option, parent, false);
        PalmistryOption_Adapter.ViewHolder viewHolder = new PalmistryOption_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PalmistryOption_Adapter.ViewHolder viewHolder, final int position) {
        int test = 0;
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).isChecked()) {
                test += 1;
            }
        }
        if (test == 0) {
            btnNext.setEnabled(false);
            btnNext.setBackgroundResource(R.drawable.custom_btn_next);
        } else {
            btnNext.setEnabled(true);
            btnNext.setBackgroundResource(R.drawable.custom_btn_next_active);
        }
        final PalmistryOption option = options.get(position);
        viewHolder.tvContent.setText(option.getText());
        if (option.getImg() != null) {
            Glide.with(context).asBitmap().load(option.getImg())
                    .into(viewHolder.imgImage);
        }
        viewHolder.linearMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option.getAnswer() != null) {
                    setUpSharePreference(itemPostion, option.getAnswer());
                } else {
                    setUpSharePreference(itemPostion, "");
                }
                options.get(position).setChecked(true);
                for (int i = 0; i < options.size(); i++) {
                    if(i != position){
                        options.get(i).setChecked(false);
                    }
                }
                notifyDataSetChanged();
                btnNext.setEnabled(true);
                btnNext.setBackgroundResource(R.drawable.custom_btn_next_active);
            }
        });
        viewHolder.rbtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option.getAnswer() != null) {
                    setUpSharePreference(itemPostion, option.getAnswer());
                } else {
                    setUpSharePreference(itemPostion, "");
                }
                options.get(position).setChecked(true);
                for (int i = 0; i < options.size(); i++) {
                    if(i != position){
                        options.get(i).setChecked(false);
                    }
                }
                notifyDataSetChanged();
                btnNext.setEnabled(true);
                btnNext.setBackgroundResource(R.drawable.custom_btn_next_active);
            }
        });
        viewHolder.rbtnCheck.setChecked(option.isChecked());
    }

    private void setUpSharePreference(int position, String answer) {
        SharedPreferences myAnsert = context.getSharedPreferences("Answer", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myAnsert.edit();
        editor.putString("" + position, answer);
        editor.commit();
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContent;
        private RadioButton rbtnCheck;
        private ImageView imgImage;
        private LinearLayout linearMain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearMain = itemView.findViewById(R.id.linearMain);
            tvContent = itemView.findViewById(R.id.tvContent);
            rbtnCheck = itemView.findViewById(R.id.rbtnCheck);
            imgImage = itemView.findViewById(R.id.imgImage);
        }
    }
}