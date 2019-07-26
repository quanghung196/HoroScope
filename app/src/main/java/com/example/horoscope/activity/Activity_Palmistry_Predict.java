package com.example.horoscope.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.MainActivity;
import com.example.horoscope.R;
import com.example.horoscope.adapter.PalmistryQuestion_Adapter;
import com.example.horoscope.model.palmistry.PalmistryOption;
import com.example.horoscope.model.palmistry.PalmistryPredict;
import com.example.horoscope.model.palmistry.PalmistryQuestion;
import com.example.horoscope.ultil.AdmodManager;
import com.example.horoscope.ultil.ReadJson;
import com.example.horoscope.ultil.SharePrefManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Activity_Palmistry_Predict extends AppCompatActivity {

    private TextView tvPalmName;
    private int position;
    private int palmImage;

    private String palmName;
    private RecyclerView recyclerContainer;
    private LinearLayoutManager manager;
    private LinearLayout linearBottom;
    private Button btnNext;
    private ImageView imgBack;

    private int currentPosition = 0;
    private int questionSize;

    private List<PalmistryQuestion> questions;
    private List<PalmistryOption> options;
    private List<PalmistryOption> options2;

    private PalmistryQuestion_Adapter adapter;

    private String image, answer, text, outro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_palmistry);

        init();
        getData();
        setUpRecyclerView();
        if (position != 5 && position != 6 && position != 7) {
            getDataFromJsonFile("palm_print_en.json", position);
        } else {
            getDataFromJsonFile("palm_shape_en.json", position - 5);
        }
        setAdapter();
        setUpBtnNext();
    }

    private void setAdapter() {
        adapter = new PalmistryQuestion_Adapter(questions, this, options2, outro, palmImage, btnNext);
        recyclerContainer.setAdapter(adapter);
    }

    private void init() {
        imgBack = findViewById(R.id.imgBack);
        linearBottom = findViewById(R.id.linearBottom);
        btnNext = findViewById(R.id.btnNext);
        tvPalmName = findViewById(R.id.tvPalmName);
        recyclerContainer = findViewById(R.id.recyclerContainer);
        options2 = new ArrayList<>();
    }

    private void setUpBtnNext() {
        btnNext.setText("Continue(" + (currentPosition + 1) + "/" + questions.size() + ")");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPosition < questions.size() - 1) {
                    currentPosition++;
                    btnNext.setText("Continue(" + (currentPosition + 1) + "/" + questions.size() + ")");
                    recyclerContainer.scrollToPosition(currentPosition);
                } else {
                    if (questions.get(questions.size() - 1) != null) {
                        AdmodManager.forceEventShowAds();
                        questions.add(null);
                        currentPosition++;
                        recyclerContainer.scrollToPosition(currentPosition);
                        btnNext.setVisibility(View.GONE);
                        linearBottom.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void setUpRecyclerView() {
        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        recyclerContainer.setHasFixedSize(true);
        recyclerContainer.setLayoutManager(manager);
    }

    private void getData() {
        Bundle bundle = getIntent().getBundleExtra("Palmistry");
        position = bundle.getInt("position");
        palmImage = bundle.getInt("palmImage");
        palmName = bundle.getString("palmName");
        tvPalmName.setText(palmName);
    }

    public void imgBack(View view) {
        if (currentPosition == questionSize) {
            questions.remove(currentPosition);
            adapter.notifyItemChanged(currentPosition);
            btnNext.setVisibility(View.VISIBLE);
            linearBottom.setVisibility(View.GONE);
        }
        currentPosition--;
        btnNext.setText("Continue(" + (currentPosition + 1) + "/" + questions.size() + ")");
        if (currentPosition < 0) {
            finish();
        }
        adapter.notifyDataSetChanged();
        recyclerContainer.scrollToPosition(currentPosition);
    }

    private void getDataFromJsonFile(String fileName, int position) {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        try {
            PalmistryPredict[] predict = gson.fromJson(ReadJson.readText(this, fileName), PalmistryPredict[].class);
            outro = predict[position].getOutro();
            questions = predict[position].getQuestions();
            questionSize = questions.size();
            for (int i = 0; i < questions.size(); i++) {
                setQuestion(i, questions.get(i).getQuestion());
                options = questions.get(i).getOptions();
                for (int j = 0; j < options.size(); j++) {
                    image = options.get(j).getImg();
                    answer = options.get(j).getAnswer();
                    text = options.get(j).getText();
                    options2.add(new PalmistryOption(image, text, answer, i, false));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        clearQuestion();
        clearAnswer();
    }

    private void clearQuestion() {
        SharedPreferences preferences = getSharedPreferences("Question", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    private void clearAnswer() {
        SharedPreferences preferences1 = getSharedPreferences("Answer", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = preferences1.edit();
        editor1.clear();
        editor1.commit();
    }

    private void setQuestion(int position, String question) {
        SharedPreferences myQuestion = getSharedPreferences("Question", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myQuestion.edit();
        editor.putString("" + position, question);
        editor.commit();
    }

    public void btnDone(View view) {
        SharePrefManager.putPostion(getApplicationContext(),Activity_Palmistry_Predict.this, MainActivity.class);
        finish();
    }

    public void btnTryAgain(View view) {
        Intent intent = new Intent(Activity_Palmistry_Predict.this, Activity_Palmistry.class);
        startActivity(intent);
        finish();
    }
}
