package com.example.horoscope.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.R;
import com.example.horoscope.model.quiz.QuizCategory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Quiz_Adapter extends RecyclerView.Adapter<Quiz_Adapter.ViewHolder> {

    private List<QuizCategory> categories;
    private Context context;

    public Quiz_Adapter(List<QuizCategory> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public Quiz_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz_category, parent, false);
        Quiz_Adapter.ViewHolder viewHolder = new Quiz_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Quiz_Adapter.ViewHolder viewHolder, final int position) {
        QuizCategory category = categories.get(position);

        String quizTitle = category.getQuizName().replaceAll("-", " ");
        StringBuilder sb = new StringBuilder();
        String[] s = quizTitle.split(" ");
        for (int i = 0; i < s.length; i++) {
            s[i] = s[i].replaceFirst("[a-z]{1}", s[i].substring(0, 1).toUpperCase());
            sb.append(s[i]);
            if (i < s.length - 1) {
                sb.append(" ");
            }
        }

        viewHolder.tvTitle.setText(sb + "?");
        Picasso.with(context)
                .load(category.getImageUrl())
                .fit()
                .centerCrop()
                .into(viewHolder.imgTitle);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView imgTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgTitle = itemView.findViewById(R.id.imgTitle);
        }
    }
}
