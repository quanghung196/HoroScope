package com.example.horoscope.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.horoscope.R;
import com.example.horoscope.model.palmistry.PalmistryOption;
import com.example.horoscope.model.palmistry.PalmistryQuestion;

import java.util.ArrayList;
import java.util.List;

public class PalmistryQuestion_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_QUESTION = 0;
    private final int VIEW_TYPE_ANSWER = 1;

    private List<PalmistryQuestion> questions;
    private List<PalmistryOption> options;
    private List<PalmistryOption> options2;
    private Context context;
    private String outro;
    private int palmImage;
    private Button btnNext;

    public PalmistryQuestion_Adapter(List<PalmistryQuestion> questions, Context context, List<PalmistryOption> options, String outro, int palmImage, Button btnNext) {
        this.questions = questions;
        this.options = options;
        this.context = context;
        this.outro = outro;
        this.palmImage = palmImage;
        this.btnNext = btnNext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPE_QUESTION) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_palm_question, viewGroup, false);
            return new QuestionViewHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_palm_answer, viewGroup, false);
            return new AnswerViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof QuestionViewHolder) {
            questionView((QuestionViewHolder) viewHolder, position);
        } else if (viewHolder instanceof AnswerViewHolder) {
            answerView((AnswerViewHolder) viewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.size();
    }

    public int getItemViewType(int position) {
        return questions.get(position) == null ? VIEW_TYPE_ANSWER : VIEW_TYPE_QUESTION;
    }

    private String getAnswer(int position) {
        SharedPreferences getAnswer = context.getSharedPreferences("Answer", Context.MODE_PRIVATE);
        return getAnswer.getString("" + position, "");
    }

    private void answerView(final AnswerViewHolder viewHolder, final int position) {
        viewHolder.tvOutro.setText(outro);

        Glide.with(context).asBitmap().load(palmImage)
                .into(viewHolder.imgImage);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < questions.size(); i++) {
            String answer = getAnswer(i);
            if (answer != "") {
                sb.append(answer);
                sb.append("\n");
                sb.append("\n");
            }
        }
        viewHolder.tvAnswer.setText(sb.toString());
    }

    private void questionView(final QuestionViewHolder viewHolder, final int position) {
        viewHolder.tvQuestion.setText(getQuestion(position));
        options2 = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getPosition() == position) {
                options2.add(options.get(i));
            }
        }
        for (int i = options2.size() - 1; i >= 0; i--) {
            if (options2.get(i).getPosition() != position) {
                options2.remove(i);
            }
        }
        viewHolder.adapter = new PalmistryOption_Adapter(options2, context, position, btnNext);
        viewHolder.recyclerOption.setAdapter(viewHolder.adapter);
    }

    private String getQuestion(int position) {
        SharedPreferences getQuestion = context.getSharedPreferences("Question", Context.MODE_PRIVATE);
        return getQuestion.getString("" + position, "");
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerOption;
        private TextView tvQuestion;
        private GridLayoutManager manager;
        private PalmistryOption_Adapter adapter;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            recyclerOption = itemView.findViewById(R.id.recyclerOption);

            setUpRecyclerView();
        }

        private void setUpRecyclerView() {
            manager = new GridLayoutManager(context, 1);
            recyclerOption.setHasFixedSize(true);
            recyclerOption.setLayoutManager(manager);
        }
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgImage;
        private TextView tvOutro, tvAnswer;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.imgImage);
            tvOutro = itemView.findViewById(R.id.tvOutro);
            tvAnswer = itemView.findViewById(R.id.tvAnswer);
        }
    }
}
