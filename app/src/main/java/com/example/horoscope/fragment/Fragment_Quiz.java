package com.example.horoscope.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscope.R;
import com.example.horoscope.adapter.Quiz_Adapter;
import com.example.horoscope.model.quiz.QuizCategory;
import com.example.horoscope.model.quiz.Quiz;
import com.example.horoscope.network.ApiService;
import com.example.horoscope.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Quiz extends Fragment {

    private static String MCC = "452";
    private static String CODE = "VN";
    private static String INFO = "true";
    private static String LOCATE = "vi-vn";

    private static final String POSITION = "position";
    private static final String TITLE = "title";
    private int position;
    private String title;
    private Context context;
    private String apiParam;
    private List<QuizCategory> quizCategories;
    private Quiz quiz;
    private RecyclerView recyclerQuiz;
    private Quiz_Adapter adapter;

    private ApiService api;

    public Fragment_Quiz() {
    }

    public static Fragment_Quiz newInstance(int position, String title) {
        Fragment_Quiz fragment = new Fragment_Quiz();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
            title = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        setParamByPosition();
        init(view);
        setUpRecyclerView();
        load(apiParam);
        return view;
    }

    private void init(View view) {
        quiz = new Quiz();
        recyclerQuiz = view.findViewById(R.id.recyclerQuiz);
        quizCategories = new ArrayList<>();
        api = RetrofitClient.createService(ApiService.class, getActivity(), "http://ap.ime.cootek.com/");
    }

    private void setUpRecyclerView(){
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        recyclerQuiz.setHasFixedSize(true);
        recyclerQuiz.setLayoutManager(manager);
    }

    private void setAdapter(){
        adapter = new Quiz_Adapter(quizCategories, context);
        recyclerQuiz.setAdapter(adapter);
    }

    private void setParamByPosition() {
        if (position == 1) {
            apiParam = "Quizz " + title;
        } else {
            apiParam = title + " Quizz";
        }
    }

    private void load(String apiParam) {
        Call<Quiz> call = api.getQuizCategory(apiParam );
        call.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                if (response.isSuccessful()) {
                    quiz = response.body();
                } else {
                    Log.e("lala", "onResponse " + response.code());
                }
                quizCategories = quiz.getData();
                setAdapter();
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                Log.e("lala", " onFailure " + t.getMessage());
            }
        });
    }
}
