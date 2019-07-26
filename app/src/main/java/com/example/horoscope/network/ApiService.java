package com.example.horoscope.network;

import com.example.horoscope.model.HoroScope;
import com.example.horoscope.model.quiz.Quiz;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/horoscope/{time}/{zodiac}")
    Call<HoroScope> getHoroScopeByZodiacAndTime(@Path("time") String time,@Path("zodiac") String zodiac);

    @GET("/matrix/horoscope/quiz/question")
    Call<Quiz> getQuizCategory(
            @Query("catalog") String catalog/*,
            @Query("need_brief_info") String need*/);
}
